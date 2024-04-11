package org.oj.mapstruct;

import org.oj.dto.lessonUser.LessonUserForCreateDto;
import org.oj.dto.lessonUser.LessonUserForDetailDto;
import org.oj.dto.lessonUser.LessonUserForListDto;
import org.oj.dto.lessonUser.LessonUserForUpdateDto;
import org.oj.entity.LessonUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * LessonUser转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonUserConvert {
    LessonUserConvert INSTANCE = Mappers.getMapper(LessonUserConvert.class);

    /**
     * LessonUser转LessonUserForListDto
     *
     * @param lessonUser LessonUser
     * @return LessonUserForListDto
     */
    LessonUserForListDto mapToListDto(LessonUser lessonUser);

    /**
     * List<LessonUser>转List<LessonUserForListDto>
     *
     * @param entities List<LessonUser>
     * @return List<LessonUserForListDto>
     */
    List<LessonUserForListDto> mapToList(List<LessonUser> entities);

    /**
     * LessonUserForCreateDto转LessonUser
     *
     * @param dto LessonUserForCreateDto
     * @return LessonUser
     */
    LessonUser mapByCreateDto(LessonUserForCreateDto dto);

    /**
     * LessonUserForDetailDto转LessonUser
     *
     * @param lessonUser LessonUser
     * @return LessonUserForDetailDto
     */
    LessonUserForDetailDto mapToDetailDto(LessonUser lessonUser);

    /**
     * LessonUserForUpdateDto转LessonUser
     *
     * @param dto LessonUserForUpdateDto
     * @return LessonUser
     */
    LessonUser mapByUpdateDto(LessonUserForUpdateDto dto);
}
