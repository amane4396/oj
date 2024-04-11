package org.oj.mapstruct;

import org.oj.dto.classTeacher.ClassTeacherForCreateDto;
import org.oj.dto.classTeacher.ClassTeacherForDetailDto;
import org.oj.dto.classTeacher.ClassTeacherForListDto;
import org.oj.dto.classTeacher.ClassTeacherForUpdateDto;
import org.oj.entity.ClassTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ClassTeacher转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassTeacherConvert {
    ClassTeacherConvert INSTANCE = Mappers.getMapper(ClassTeacherConvert.class);

    /**
     * ClassTeacher转ClassTeacherForListDto
     *
     * @param classTeacher ClassTeacher
     * @return ClassTeacherForListDto
     */
    ClassTeacherForListDto mapToListDto(ClassTeacher classTeacher);

    /**
     * List<ClassTeacher>转List<ClassTeacherForListDto>
     *
     * @param entities List<ClassTeacher>
     * @return List<ClassTeacherForListDto>
     */
    List<ClassTeacherForListDto> mapToList(List<ClassTeacher> entities);

    /**
     * ClassTeacherForCreateDto转ClassTeacher
     *
     * @param dto ClassTeacherForCreateDto
     * @return ClassTeacher
     */
    ClassTeacher mapByCreateDto(ClassTeacherForCreateDto dto);

    /**
     * ClassTeacherForDetailDto转ClassTeacher
     *
     * @param classTeacher ClassTeacher
     * @return ClassTeacherForDetailDto
     */
    ClassTeacherForDetailDto mapToDetailDto(ClassTeacher classTeacher);

    /**
     * ClassTeacherForUpdateDto转ClassTeacher
     *
     * @param dto ClassTeacherForUpdateDto
     * @return ClassTeacher
     */
    ClassTeacher mapByUpdateDto(ClassTeacherForUpdateDto dto);
}
