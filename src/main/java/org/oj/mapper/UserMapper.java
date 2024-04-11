package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户表	 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * User关联查询
     *
     * @param wrapper Wrapper<User>
     * @return User
     */
    User selectWithAssociation(@Param("ew") Wrapper<User> wrapper);

    /**
     * User关联分页查询
     *
     * @param page    Page<User>
     * @param wrapper Wrapper<User>
     * @return Page<User>
     */
    Page<User> selectWithAssociation(Page<User> page, @Param("ew") Wrapper<User> wrapper);
}
