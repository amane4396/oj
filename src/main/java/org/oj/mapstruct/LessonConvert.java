package org.oj.mapstruct;

import org.oj.dto.lesson.LessonForCreateDto;
import org.oj.dto.lesson.LessonForDetailDto;
import org.oj.dto.lesson.LessonForListDto;
import org.oj.dto.lesson.LessonForUpdateDto;
import org.oj.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Lesson转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonConvert {
    LessonConvert INSTANCE = Mappers.getMapper(LessonConvert.class);

    /**
     * Lesson转LessonForListDto
     *
     * @param lesson Lesson
     * @return LessonForListDto
     */
    LessonForListDto mapToListDto(Lesson lesson);

    /**
     * List<Lesson>转List<LessonForListDto>
     *
     * @param entities List<Lesson>
     * @return List<LessonForListDto>
     */
    List<LessonForListDto> mapToList(List<Lesson> entities);

    /**
     * LessonForCreateDto转Lesson
     *
     * @param dto LessonForCreateDto
     * @return Lesson
     */
    Lesson mapByCreateDto(LessonForCreateDto dto);

    /**
     * LessonForDetailDto转Lesson
     *
     * @param lesson Lesson
     * @return LessonForDetailDto
     */
    LessonForDetailDto mapToDetailDto(Lesson lesson);

    /**
     * LessonForUpdateDto转Lesson
     *
     * @param dto LessonForUpdateDto
     * @return Lesson
     */
    Lesson mapByUpdateDto(LessonForUpdateDto dto);
}
