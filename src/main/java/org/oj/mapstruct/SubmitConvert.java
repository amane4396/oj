package org.oj.mapstruct;

import org.oj.dto.submit.SubmitForCreateDto;
import org.oj.dto.submit.SubmitForDetailDto;
import org.oj.dto.submit.SubmitForListDto;
import org.oj.dto.submit.SubmitForUpdateDto;
import org.oj.entity.Submit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Submit转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubmitConvert {
    SubmitConvert INSTANCE = Mappers.getMapper(SubmitConvert.class);

    /**
     * Submit转SubmitForListDto
     *
     * @param submit Submit
     * @return SubmitForListDto
     */
    SubmitForListDto mapToListDto(Submit submit);

    /**
     * List<Submit>转List<SubmitForListDto>
     *
     * @param entities List<Submit>
     * @return List<SubmitForListDto>
     */
    List<SubmitForListDto> mapToList(List<Submit> entities);

    /**
     * SubmitForCreateDto转Submit
     *
     * @param dto SubmitForCreateDto
     * @return Submit
     */
    Submit mapByCreateDto(SubmitForCreateDto dto);

    /**
     * SubmitForDetailDto转Submit
     *
     * @param submit Submit
     * @return SubmitForDetailDto
     */
    SubmitForDetailDto mapToDetailDto(Submit submit);

    /**
     * SubmitForUpdateDto转Submit
     *
     * @param dto SubmitForUpdateDto
     * @return Submit
     */
    Submit mapByUpdateDto(SubmitForUpdateDto dto);
}
