package org.oj.mapstruct;

import org.oj.dto.UserInfoDto;
import org.oj.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * User转换
 *
 * @author XT
 * @create 2024-06-14
 */
@Mapper
public interface AccountConvert {
    AccountConvert INSTANCE = Mappers.getMapper(AccountConvert.class);

    /**
     * Entity转DTO
     *
     * @param user  User
     * @param token token
     * @return AccountDto
     */
    @Mapping(source = "user.memberId", target = "userName")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.userType.name", target = "roleTypeName")
    UserInfoDto map(User user, String token);
}
