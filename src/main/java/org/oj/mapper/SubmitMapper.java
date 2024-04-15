package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Submit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 提交记录 Mapper 接口
 *
 * @author XT
 * @create 2024-04-15
 * @update 2024-04-15
 */
public interface SubmitMapper extends BaseMapper<Submit> {
    /**
     * Submit关联查询
     *
     * @param wrapper Wrapper<Submit>
     * @return Submit
     */
    Submit selectWithAssociation(@Param("ew") Wrapper<Submit> wrapper);

    /**
     * Submit关联分页查询
     *
     * @param page    Page<Submit>
     * @param wrapper Wrapper<Submit>
     * @return Page<Submit>
     */
    Page<Submit> selectWithAssociation(Page<Submit> page, @Param("ew") Wrapper<Submit> wrapper);
}
