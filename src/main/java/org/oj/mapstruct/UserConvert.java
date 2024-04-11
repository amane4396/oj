package org.oj.mapstruct;

import org.oj.dto.user.UserForCreateDto;
import org.oj.dto.user.UserForDetailDto;
import org.oj.dto.user.UserForListDto;
import org.oj.dto.user.UserForUpdateDto;
import org.oj.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * User转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * User转UserForListDto
     *
     * @param user User
     * @return UserForListDto
     */
    UserForListDto mapToListDto(User user);

    /**
     * List<User>转List<UserForListDto>
     *
     * @param entities List<User>
     * @return List<UserForListDto>
     */
    List<UserForListDto> mapToList(List<User> entities);

    /**
     * UserForCreateDto转User
     *
     * @param dto UserForCreateDto
     * @return User
     */
    User mapByCreateDto(UserForCreateDto dto);

    /**
     * UserForDetailDto转User
     *
     * @param user User
     * @return UserForDetailDto
     */
    UserForDetailDto mapToDetailDto(User user);

    /**
     * UserForUpdateDto转User
     *
     * @param dto UserForUpdateDto
     * @return User
     */
    User mapByUpdateDto(UserForUpdateDto dto);
}
