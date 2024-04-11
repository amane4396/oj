package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.LessonUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 老师/学生课程关联表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface LessonUserMapper extends BaseMapper<LessonUser> {
    /**
     * LessonUser关联查询
     *
     * @param wrapper Wrapper<LessonUser>
     * @return LessonUser
     */
    LessonUser selectWithAssociation(@Param("ew") Wrapper<LessonUser> wrapper);

    /**
     * LessonUser关联分页查询
     *
     * @param page    Page<LessonUser>
     * @param wrapper Wrapper<LessonUser>
     * @return Page<LessonUser>
     */
    Page<LessonUser> selectWithAssociation(Page<LessonUser> page, @Param("ew") Wrapper<LessonUser> wrapper);
}
