package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Lesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 课程表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface LessonMapper extends BaseMapper<Lesson> {
    /**
     * Lesson关联查询
     *
     * @param wrapper Wrapper<Lesson>
     * @return Lesson
     */
    Lesson selectWithAssociation(@Param("ew") Wrapper<Lesson> wrapper);

    /**
     * Lesson关联分页查询
     *
     * @param page    Page<Lesson>
     * @param wrapper Wrapper<Lesson>
     * @return Page<Lesson>
     */
    Page<Lesson> selectWithAssociation(Page<Lesson> page, @Param("ew") Wrapper<Lesson> wrapper);
}
