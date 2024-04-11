package org.oj.mapstruct;

import org.oj.dto.task.TaskForCreateDto;
import org.oj.dto.task.TaskForDetailDto;
import org.oj.dto.task.TaskForListDto;
import org.oj.dto.task.TaskForUpdateDto;
import org.oj.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Task转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskConvert {
    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    /**
     * Task转TaskForListDto
     *
     * @param task Task
     * @return TaskForListDto
     */
    TaskForListDto mapToListDto(Task task);

    /**
     * List<Task>转List<TaskForListDto>
     *
     * @param entities List<Task>
     * @return List<TaskForListDto>
     */
    List<TaskForListDto> mapToList(List<Task> entities);

    /**
     * TaskForCreateDto转Task
     *
     * @param dto TaskForCreateDto
     * @return Task
     */
    Task mapByCreateDto(TaskForCreateDto dto);

    /**
     * TaskForDetailDto转Task
     *
     * @param task Task
     * @return TaskForDetailDto
     */
    TaskForDetailDto mapToDetailDto(Task task);

    /**
     * TaskForUpdateDto转Task
     *
     * @param dto TaskForUpdateDto
     * @return Task
     */
    Task mapByUpdateDto(TaskForUpdateDto dto);
}
