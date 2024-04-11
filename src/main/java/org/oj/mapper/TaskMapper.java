package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 题目表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * Task关联查询
     *
     * @param wrapper Wrapper<Task>
     * @return Task
     */
    Task selectWithAssociation(@Param("ew") Wrapper<Task> wrapper);

    /**
     * Task关联分页查询
     *
     * @param page    Page<Task>
     * @param wrapper Wrapper<Task>
     * @return Page<Task>
     */
    Page<Task> selectWithAssociation(Page<Task> page, @Param("ew") Wrapper<Task> wrapper);
}
