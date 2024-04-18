package org.oj.mapstruct;

import org.oj.dto.department.*;
import org.oj.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Department转换
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentConvert {
    DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

    /**
     * Department转DepartmentForListDto
     *
     * @param department Department
     * @return DepartmentForListDto
     */
    DepartmentForListDto mapToListDto(Department department);

    /**
     * List<Department>转List<DepartmentForListDto>
     *
     * @param entities List<Department>
     * @return List<DepartmentForListDto>
     */
    List<DepartmentForListDto> mapToList(List<Department> entities);

    /**
     * DepartmentForCreateDto转Department
     *
     * @param dto DepartmentForCreateDto
     * @return Department
     */
    Department mapByCreateDto(DepartmentForCreateDto dto);

    /**
     * DepartmentForDetailDto转Department
     *
     * @param department Department
     * @return DepartmentForDetailDto
     */
    DepartmentForDetailDto mapToDetailDto(Department department);

    /**
     * DepartmentForUpdateDto转Department
     *
     * @param dto DepartmentForUpdateDto
     * @return Department
     */
    Department mapByUpdateDto(DepartmentForUpdateDto dto);
}
