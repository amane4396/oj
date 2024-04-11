package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.ClassTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 老师、班级关联表	 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ClassTeacherMapper extends BaseMapper<ClassTeacher> {
    /**
     * ClassTeacher关联查询
     *
     * @param wrapper Wrapper<ClassTeacher>
     * @return ClassTeacher
     */
    ClassTeacher selectWithAssociation(@Param("ew") Wrapper<ClassTeacher> wrapper);

    /**
     * ClassTeacher关联分页查询
     *
     * @param page    Page<ClassTeacher>
     * @param wrapper Wrapper<ClassTeacher>
     * @return Page<ClassTeacher>
     */
    Page<ClassTeacher> selectWithAssociation(Page<ClassTeacher> page, @Param("ew") Wrapper<ClassTeacher> wrapper);
}
