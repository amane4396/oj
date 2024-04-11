package org.oj.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListItem;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 下拉列表选项工具
 * @author DH
 * @create 2021-10-20
 */
public class SelectListUtil {

    /**
     * 获取并封装下拉列表数据
     *
     * @param service service
     * @param clazz   clazz
     * @param <T>     T
     * @return List<SelectListItem>
     * @throws Exception custom
     */
    public static <T> List<SelectListItem> getSelectList(@NonNull IService<T> service, @NonNull Class<T> clazz) throws Exception {

        return getSelectList(service, clazz, null, null, null, null, null);
    }

    /**
     * 获取并封装下拉列表数据
     *
     * @param service service
     * @param clazz   clazz
     * @param wrapper wrapper
     * @param <T>     T
     * @return List<SelectListItem>
     * @throws Exception custom
     */
    public static <T> List<SelectListItem> getSelectList(@NonNull IService<T> service, @NonNull Class<T> clazz, @NonNull QueryWrapper<T> wrapper) throws Exception {

        return getSelectList(service, clazz, wrapper, null, null, null, null);
    }

    /**
     * 获取并封装下拉列表数据
     *
     * @param service                service
     * @param clazz                  clazz
     * @param wrapper                wrapper
     * @param getKeyFieldMethodName  getKeyFieldMethodName
     * @param getTextFieldMethodName getTextFieldMethodName
     * @param <T>                    T
     * @return List<SelectListItem>
     * @throws Exception custom
     */
    public static <T> List<SelectListItem> getSelectList(@NonNull IService<T> service, @NonNull Class<T> clazz, @Nullable QueryWrapper<T> wrapper, @Nullable String getKeyFieldMethodName, @Nullable String getTextFieldMethodName) throws Exception {
        return getSelectList(service, clazz, wrapper, getKeyFieldMethodName, getTextFieldMethodName, null, null);
    }

    /**
     * 获取并封装下拉列表数据
     *
     * @param service       service
     * @param clazz         clazz
     * @param wrapper       wrapper
     * @param sortFieldName sortFieldName
     * @param isAscSort     isAscSort
     * @param <T>           T
     * @return List<SelectListItem>
     * @throws Exception custom
     */
    public static <T> List<SelectListItem> getSelectList(@NonNull IService<T> service, @NonNull Class<T> clazz, @Nullable QueryWrapper<T> wrapper, @Nullable String sortFieldName, @Nullable Boolean isAscSort) throws Exception {
        return getSelectList(service, clazz, wrapper, null, null, sortFieldName, isAscSort);
    }

    /**
     * 获取并封装下拉列表数据
     *
     * @param service                service
     * @param clazz                  clazz
     * @param wrapper                wrapper
     * @param getKeyFieldMethodName  getKeyFieldMethodName
     * @param getTextFieldMethodName getTextFieldMethodName
     * @param sortFieldName          sortFieldName
     * @param isAscSort              isAscSort
     * @param <T>                    T
     * @return List<SelectListItem>
     * @throws Exception custom
     */
    public static <T> List<SelectListItem> getSelectList(@NonNull IService<T> service, @NonNull Class<T> clazz, @Nullable QueryWrapper<T> wrapper, @Nullable String getKeyFieldMethodName, @Nullable String getTextFieldMethodName, @Nullable String sortFieldName, @Nullable Boolean isAscSort) throws Exception {
        String className = clazz.getSimpleName();
        String classNameLowerCamel = className.substring(0, 1).toLowerCase() + className.substring(1);
        // 缺省值处理
        wrapper = Optional.ofNullable(wrapper).orElseGet(QueryWrapper::new);
        isAscSort = Optional.ofNullable(isAscSort).orElse(true);
        if (sortFieldName != null) {
            if (ReflectionUtil.getField(clazz, sortFieldName) == null) {
                throw new Exception("[" + className + "]中不存在[" + sortFieldName + "]字段");
            }
        } else {
            sortFieldName = "sort";
            if (ReflectionUtil.getField(clazz, sortFieldName) == null) {
                sortFieldName = "name";
                if (ReflectionUtil.getField(clazz, sortFieldName) == null) {
                    sortFieldName = classNameLowerCamel + "Name";
                    if (ReflectionUtil.getField(clazz, sortFieldName) == null) {
                        throw new Exception("[" + className + "]中不存在合适的缺省排序字段");
                    }
                }
            }
        }
        Method keyMethod, textMethod;
        if (getKeyFieldMethodName != null) {
            keyMethod = ReflectionUtil.getDeclaredMethod(clazz, getKeyFieldMethodName, null);
            if (keyMethod == null) {
                throw new Exception("[" + className + "]中不存在[" + getKeyFieldMethodName + "]字段");
            }
        } else {
            keyMethod = ReflectionUtil.getDeclaredMethod(clazz, "getId", null);
            if (keyMethod == null) {
                keyMethod = ReflectionUtil.getDeclaredMethod(clazz, "get" + className + "Id", null);
                if (keyMethod == null) {
                    throw new Exception("[" + className + "]中不存在合适的作为下拉选项值的缺省字段");
                }
            }
        }
        if (getTextFieldMethodName != null) {
            textMethod = ReflectionUtil.getDeclaredMethod(clazz, getTextFieldMethodName, null);
            if (textMethod == null) {
                throw new Exception("[" + className + "]中不存在[" + getTextFieldMethodName + "]字段");
            }
        } else {
            textMethod = ReflectionUtil.getDeclaredMethod(clazz, "getSelectText", null);
            if (textMethod == null) {
                textMethod = ReflectionUtil.getDeclaredMethod(clazz, "getName", null);
                if (textMethod == null) {
                    textMethod = ReflectionUtil.getDeclaredMethod(clazz, "get" + className + "Name", null);
                    if (textMethod == null) {
                        throw new Exception("[" + className + "]中不存在合适的作为下拉选项显示文字的缺省字段");
                    }
                }
            }
        }

        wrapper.orderBy(true, isAscSort, sortFieldName);
        List<T> entities = service.list(wrapper);
        List<SelectListItem> selectList = new ArrayList<>();
        for (T entity : entities) {
            Object keyValue = ReflectionUtil.invokeMethod(entity, keyMethod.getName(), null, null);
            Object textValue = ReflectionUtil.invokeMethod(entity, textMethod.getName(), null, null);
            if (keyValue == null || textValue == null) {
                throw new Exception("属性值获取错误：" + className + "(" + getKeyFieldMethodName + "、" + getTextFieldMethodName + ")");
            }
            selectList.add(new SelectListItem(keyValue.toString(), textValue.toString()));
        }
        return selectList;
    }
}
