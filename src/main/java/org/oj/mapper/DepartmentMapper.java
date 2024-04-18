package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * Department关联查询
     *
     * @param wrapper Wrapper<Department>
     * @return Department
     */
    Department selectWithAssociation(@Param("ew") Wrapper<Department> wrapper);

    /**
     * Department关联分页查询
     *
     * @param page    Page<Department>
     * @param wrapper Wrapper<Department>
     * @return Page<Department>
     */
    Page<Department> selectWithAssociation(Page<Department> page, @Param("ew") Wrapper<Department> wrapper);
}
