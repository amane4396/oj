package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 关联查询接口
 *
 * @author XT
 * @date 2023-11-01
 */
public interface ServiceWithAssociation<T> {

    /**
     * 关联查询，单数据
     * @param wrapper Wrapper<T>
     * @return T
     */
    T selectWithAssociation(@Param("ew")Wrapper<T> wrapper);

    /**
     * 关联查询，分页数据
     * @param page Page<?>
     * @param wrapper Wrapper<T>
     * @return Page<T>
     */
    Page<T> selectWithAssociation(Page<T> page, @Param("ew")Wrapper<T> wrapper);
}
