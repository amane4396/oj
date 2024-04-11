package org.oj.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.oj.vo.Sort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器中使用的公共方法
 *
 * @author DH
 * @create 2020-12-11
 * @create 2022-06-22
 */
@Slf4j
public class DataListUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String PAGE_PARAM_NAME = "page";
    private static final String QUERY_PARAM_NAME = "query";
    private static final String SORT_PARAM_NAME = "sort";
    private static final String SORT_PROPERTY_PARAM_NAME = "property";
    private static final String SORT_DIRECTION_PARAM_NAME = "direction";
    private static final int CURRENT_PAGE = 1;
    private static final int PAGE_SIZE = 20;

    /**
     * 封装排序数据
     *
     * @param requestBody String 前端提交的数据
     * @return Sort
     * @throws Exception 异常
     */
    public static Sort getSort(String requestBody) throws Exception {
        if (StringUtils.isEmpty(requestBody)) {
            return null;
        }
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return null;
        }
        String property = jsonNode.findPath(SORT_PARAM_NAME).findPath(SORT_PROPERTY_PARAM_NAME).asText(null);
        String direction = jsonNode.findPath(SORT_PARAM_NAME).findPath(SORT_DIRECTION_PARAM_NAME).asText(null);
        if (StringUtils.isEmpty(property) || StringUtils.isEmpty(direction)) {
            return null;
        }
        Sort sort = new Sort();
        sort.setProperty(property);
        sort.setAsc(direction);
        return sort;
    }

    /**
     * 封装翻页数据
     *
     * @param requestBody String 前端提交的数据
     * @return Page<T>
     * @throws Exception 异常
     */
    public static <T> Page<T> getPage(String requestBody) throws Exception {
        Page<T> page = new Page<>(CURRENT_PAGE, PAGE_SIZE);
        if (StringUtils.isEmpty(requestBody)) {
            return page;
        }
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return page;
        }
        if (!jsonNode.has(PAGE_PARAM_NAME)) {
            return page;
        }
        return objectMapper.convertValue(jsonNode.get("page"), new TypeReference<Page<T>>() {
        });
    }

    /**
     * 封装查询数据
     *
     * @param requestBody String 前端提交的数据
     * @return Map<String, ?>
     * @throws Exception 异常
     */
    public static Map<String, JsonNode> getQuery(String requestBody) throws Exception {
        if (StringUtils.isEmpty(requestBody)) {
            return null;
        }
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        if (jsonNode.isEmpty() || jsonNode.isNull()) {
            return null;
        }
        if (!jsonNode.has(QUERY_PARAM_NAME)) {
            return null;
        }
        Map<String, JsonNode> query = objectMapper.convertValue(objectMapper.readTree(requestBody).get(QUERY_PARAM_NAME), new TypeReference<HashMap<String, JsonNode>>() {
        });
        if (query != null) {
            query.values().removeIf(v -> v.isNull() || (v.isTextual() && StringUtils.isEmpty(v.asText())));
        }
        return query;
    }

    /**
     * 构建查询表达式
     *
     * @param wrapper QueryWrapper
     * @param query   Map<String, ?> 查询条件。若包含“.”，则除最后一段字段名之外，其余每段必须是entity的类名，camel或pascal
     * @param clazz   Class<T> 实体类型
     * @param <T>     实体泛型
     * @throws Exception 异常
     */
    public static <T> void query(Class<T> clazz, QueryWrapper<T> wrapper, Map<String, JsonNode> query) throws Exception {

        if (query == null || query.size() <= 0) {
            return;
        }
        String tableName = ReflectionUtil.pascalToUnderline(clazz);
        for (String key : query.keySet()) {
            JsonNode node = query.get(key);
            Type fieldType;
            String fieldName;
            if (!key.contains(".")) {
                fieldType = ReflectionUtil.getFieldType(clazz, ReflectionUtil.underlineToCamel(key));
                fieldName = tableName + "." + ReflectionUtil.pascalToUnderline(key);;
            } else {
                fieldType = ReflectionUtil.getEntityFieldType(ReflectionUtil.underlineToCamel(key));
                fieldName = ReflectionUtil.pascalToUnderline(key);
            }
            // 未检测到实体属性，跳过
            if (fieldType == null) {
                continue;
            }
            switch (node.getNodeType()) {
                case STRING:
                    if (fieldName.endsWith("Id")) {
                        wrapper.eq(fieldName, node.asText());
                    } else {
                        wrapper.like(fieldName, node.asText());
                    }
                    break;
                case NUMBER:
                case BOOLEAN:
                    wrapper.eq(fieldName, node.asText());
                    break;
                case ARRAY:
                    if (!node.isEmpty()) {
                        wrapper.in(fieldName, objectMapper.readValue(node.toPrettyString(), new TypeReference<List<String>>() {
                        }));
                    }
                    break;
                case OBJECT:
                    if (LocalDateTime.class.equals(fieldType)) {
                        String beginTime = node.findPath("begin").asText(null);
                        if (!StringUtils.isEmpty(beginTime)) {
                            wrapper.ge(fieldName, beginTime);
                        }
                        String endTime = node.findPath("end").asText(null);
                        if (!StringUtils.isEmpty(endTime)) {
                            wrapper.le(fieldName, endTime);
                        }
                    } else {
                        continue;
                    }
                    break;
                default:
            }
        }
    }

    /**
     * 获取列表数据
     *
     * @param service     service
     * @param currentPage 当前页码
     * @param pageSize    每页数据量
     * @param wrapper     QueryWrapper
     * @return HPage<T>
     */
    public static <T> Page<T> list(@NonNull IService<T> service, @Nullable Integer currentPage, @Nullable Integer pageSize, @Nullable QueryWrapper<T> wrapper) {
        int currentPageValue = currentPage == null ? CURRENT_PAGE : currentPage,
                pageSizeValue = pageSize == null ? PAGE_SIZE : pageSize;
        return list(service, new Page<>(currentPageValue, pageSizeValue), wrapper);
    }

    /**
     * 获取列表数据
     *
     * @param service service
     * @param page    Page对象
     * @param wrapper QueryWrapper
     * @return Page<T>
     */
    public static <T> Page<T> list(@NonNull IService<T> service, @NonNull Page<T> page, @Nullable QueryWrapper<T> wrapper) {
        return service.page(page, wrapper);
    }

    /**
     * 封装分页数据至列表数据
     *
     * @param page Page<?>
     * @return HashMap<String, Object>
     */
    public static <T> HashMap<String, Object> formatPageData(IPage<T> page) {
        HashMap<String, Object> data = new HashMap<>(2);
        data.put("total", page.getTotal());
        data.put("items", page.getRecords());
        return data;
    }

    /**
     * 封装分页数据至列表数据
     *
     * @param page Page<?>
     * @return HashMap<String, Object>
     */
    public static <T, V> HashMap<String, Object> formatPageData(IPage<T> page, List<V> records) {
        HashMap<String, Object> data = new HashMap<>(2);
        data.put("total", page.getTotal());
        data.put("items", records);
        return data;
    }
}
