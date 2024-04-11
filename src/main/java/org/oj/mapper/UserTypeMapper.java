package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.UserType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户类型表	 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface UserTypeMapper extends BaseMapper<UserType> {
    /**
     * UserType关联查询
     *
     * @param wrapper Wrapper<UserType>
     * @return UserType
     */
    UserType selectWithAssociation(@Param("ew") Wrapper<UserType> wrapper);

    /**
     * UserType关联分页查询
     *
     * @param page    Page<UserType>
     * @param wrapper Wrapper<UserType>
     * @return Page<UserType>
     */
    Page<UserType> selectWithAssociation(Page<UserType> page, @Param("ew") Wrapper<UserType> wrapper);
}
