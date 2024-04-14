package org.oj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.oj.annotation.Permission;
import org.oj.dto.task.*;
import org.oj.entity.Task;
import org.oj.service.*;
import org.oj.util.*;
import org.oj.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目表 前端控制器
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class TaskController {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private TaskService taskService;

    /**
     * 查询
     *
     * @param requestBody RequestBody
     * @return JsonRes
     */
    @PostMapping("/list")
    public JsonRes list(@RequestBody String requestBody) throws Exception {
        QueryWrapper<Task> wrapper = new QueryWrapper<>();

        // 查询
        Map<String, JsonNode> query = DataListUtil.getQuery(requestBody);
        DataListUtil.query(Task.class, wrapper, query);

        // 排序
        Sort sort = DataListUtil.getSort(requestBody);
        if (sort != null) {
            wrapper.orderBy(true, sort.getAsc(), sort.getProperty());
        } else {
            // TODO 指定排序
            // wrapper.orderBy(true, true升序/false降序, "字段名");
        }

        // 分页
        Page<Task> page = DataListUtil.getPage(requestBody);

        HashMap<?, ?> results = taskService.list(wrapper, page);
        return JsonRes.success(results);
    }

    /**
     * 添加
     *
     * @param requestBody RequestBody
     * @return JsonRes
     */
    @PostMapping("/create")
    public JsonRes create(@RequestBody String requestBody) throws Exception {
        TaskForCreateDto dto = objectMapper.readValue(requestBody, TaskForCreateDto.class);
        taskService.create(dto);
        return JsonRes.createSuccess();
    }

    /**
     * 详情
     *
     * @param id 主键
     * @return JsonRes
     */
    @GetMapping("/{id}/detail")
    public JsonRes detail(@PathVariable("id") String id) throws Exception {
        return JsonRes.success(taskService.detail(id));
    }

    /**
     * 更新
     *
     * @param requestBody RequestBody
     * @return JsonRes
     */
    @PutMapping("/update")
    public JsonRes update(@RequestBody String requestBody) throws Exception {
        TaskForUpdateDto dto = objectMapper.readValue(requestBody, TaskForUpdateDto.class);
        taskService.update(dto);
        return JsonRes.updateSuccess();
    }

    /**
     * 删除、批量删除
     *
     * @param requestBody RequestBody
     * @return JsonRes
     */
    @DeleteMapping("/delete")
    public JsonRes delete(@RequestBody String requestBody) throws Exception {
        List<String> ids = objectMapper.readValue(requestBody, new TypeReference<List<String>>() {
        });
        taskService.delete(ids);
        return JsonRes.deleteSuccess();
    }

}
