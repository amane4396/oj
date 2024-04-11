package org.oj.mapstruct;

import org.oj.dto.example.ExampleForCreateDto;
import org.oj.dto.example.ExampleForDetailDto;
import org.oj.dto.example.ExampleForListDto;
import org.oj.dto.example.ExampleForUpdateDto;
import org.oj.entity.Example;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Example转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExampleConvert {
    ExampleConvert INSTANCE = Mappers.getMapper(ExampleConvert.class);

    /**
     * Example转ExampleForListDto
     *
     * @param example Example
     * @return ExampleForListDto
     */
    ExampleForListDto mapToListDto(Example example);

    /**
     * List<Example>转List<ExampleForListDto>
     *
     * @param entities List<Example>
     * @return List<ExampleForListDto>
     */
    List<ExampleForListDto> mapToList(List<Example> entities);

    /**
     * ExampleForCreateDto转Example
     *
     * @param dto ExampleForCreateDto
     * @return Example
     */
    Example mapByCreateDto(ExampleForCreateDto dto);

    /**
     * ExampleForDetailDto转Example
     *
     * @param example Example
     * @return ExampleForDetailDto
     */
    ExampleForDetailDto mapToDetailDto(Example example);

    /**
     * ExampleForUpdateDto转Example
     *
     * @param dto ExampleForUpdateDto
     * @return Example
     */
    Example mapByUpdateDto(ExampleForUpdateDto dto);
}
