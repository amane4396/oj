package org.oj.mapstruct;

import org.oj.dto.tClass.TClassForCreateDto;
import org.oj.dto.tClass.TClassForDetailDto;
import org.oj.dto.tClass.TClassForListDto;
import org.oj.dto.tClass.TClassForUpdateDto;
import org.oj.entity.TClass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * TClass转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TClassConvert {
    TClassConvert INSTANCE = Mappers.getMapper(TClassConvert.class);

    /**
     * TClass转TClassForListDto
     *
     * @param tClass TClass
     * @return TClassForListDto
     */
    TClassForListDto mapToListDto(TClass tClass);

    /**
     * List<TClass>转List<TClassForListDto>
     *
     * @param entities List<TClass>
     * @return List<TClassForListDto>
     */
    List<TClassForListDto> mapToList(List<TClass> entities);

    /**
     * TClassForCreateDto转TClass
     *
     * @param dto TClassForCreateDto
     * @return TClass
     */
    TClass mapByCreateDto(TClassForCreateDto dto);

    /**
     * TClassForDetailDto转TClass
     *
     * @param tClass TClass
     * @return TClassForDetailDto
     */
    TClassForDetailDto mapToDetailDto(TClass tClass);

    /**
     * TClassForUpdateDto转TClass
     *
     * @param dto TClassForUpdateDto
     * @return TClass
     */
    TClass mapByUpdateDto(TClassForUpdateDto dto);
}
