package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.dto.task.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 题目表 服务类
 *
 * @author XT
 * @create 2024-04-14
 * @update 2024-04-14
 */
public interface TaskService extends IService<Task>, ServiceWithAssociation<Task> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<Task>
     * @param page    Page<Task>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<Task> wrapper, Page<Task> page) throws Exception;

    /**
     * 添加
     *
     * @param dto TaskForCreateDto
     * @throws Exception Exception
     */
    void create(TaskForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return TaskForDetailDto
     * @throws Exception Exception
     */
    TaskForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto TaskForUpdateDto
     * @throws Exception Exception
     */
    void update(TaskForUpdateDto dto) throws Exception;

    /**
     * 删除
     *
     * @param ids List<String>
     * @throws Exception Exception
     */
    void delete(List<String> ids) throws Exception;

    // /**
    //  * 导入
    //  *
    //  * @param multipartRequest MultipartRequest
    //  * @throws Exception Exception
    //  */
    // void importData(MultipartRequest multipartRequest) throws Exception;

    // /**
    //  * 导出
    //  *
    //  * @param wrapper QueryWrapper<Task>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<Task> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
