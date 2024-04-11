package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.TClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 班级表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface TClassMapper extends BaseMapper<TClass> {
    /**
     * TClass关联查询
     *
     * @param wrapper Wrapper<TClass>
     * @return TClass
     */
    TClass selectWithAssociation(@Param("ew") Wrapper<TClass> wrapper);

    /**
     * TClass关联分页查询
     *
     * @param page    Page<TClass>
     * @param wrapper Wrapper<TClass>
     * @return Page<TClass>
     */
    Page<TClass> selectWithAssociation(Page<TClass> page, @Param("ew") Wrapper<TClass> wrapper);
}
