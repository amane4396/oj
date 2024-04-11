package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Example;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 样例表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ExampleMapper extends BaseMapper<Example> {
    /**
     * Example关联查询
     *
     * @param wrapper Wrapper<Example>
     * @return Example
     */
    Example selectWithAssociation(@Param("ew") Wrapper<Example> wrapper);

    /**
     * Example关联分页查询
     *
     * @param page    Page<Example>
     * @param wrapper Wrapper<Example>
     * @return Page<Example>
     */
    Page<Example> selectWithAssociation(Page<Example> page, @Param("ew") Wrapper<Example> wrapper);
}
