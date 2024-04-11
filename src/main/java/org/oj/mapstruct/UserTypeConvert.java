package org.oj.mapstruct;

import org.oj.dto.userType.UserTypeForCreateDto;
import org.oj.dto.userType.UserTypeForDetailDto;
import org.oj.dto.userType.UserTypeForListDto;
import org.oj.dto.userType.UserTypeForUpdateDto;
import org.oj.entity.UserType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * UserType转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTypeConvert {
    UserTypeConvert INSTANCE = Mappers.getMapper(UserTypeConvert.class);

    /**
     * UserType转UserTypeForListDto
     *
     * @param userType UserType
     * @return UserTypeForListDto
     */
    UserTypeForListDto mapToListDto(UserType userType);

    /**
     * List<UserType>转List<UserTypeForListDto>
     *
     * @param entities List<UserType>
     * @return List<UserTypeForListDto>
     */
    List<UserTypeForListDto> mapToList(List<UserType> entities);

    /**
     * UserTypeForCreateDto转UserType
     *
     * @param dto UserTypeForCreateDto
     * @return UserType
     */
    UserType mapByCreateDto(UserTypeForCreateDto dto);

    /**
     * UserTypeForDetailDto转UserType
     *
     * @param userType UserType
     * @return UserTypeForDetailDto
     */
    UserTypeForDetailDto mapToDetailDto(UserType userType);

    /**
     * UserTypeForUpdateDto转UserType
     *
     * @param dto UserTypeForUpdateDto
     * @return UserType
     */
    UserType mapByUpdateDto(UserTypeForUpdateDto dto);
}
