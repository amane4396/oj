package org.oj.mapstruct;

import org.oj.dto.score.ScoreForCreateDto;
import org.oj.dto.score.ScoreForDetailDto;
import org.oj.dto.score.ScoreForListDto;
import org.oj.dto.score.ScoreForUpdateDto;
import org.oj.entity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Score转换
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreConvert {
    ScoreConvert INSTANCE = Mappers.getMapper(ScoreConvert.class);

    /**
     * Score转ScoreForListDto
     *
     * @param score Score
     * @return ScoreForListDto
     */
    ScoreForListDto mapToListDto(Score score);

    /**
     * List<Score>转List<ScoreForListDto>
     *
     * @param entities List<Score>
     * @return List<ScoreForListDto>
     */
    List<ScoreForListDto> mapToList(List<Score> entities);

    /**
     * ScoreForCreateDto转Score
     *
     * @param dto ScoreForCreateDto
     * @return Score
     */
    Score mapByCreateDto(ScoreForCreateDto dto);

    /**
     * ScoreForDetailDto转Score
     *
     * @param score Score
     * @return ScoreForDetailDto
     */
    ScoreForDetailDto mapToDetailDto(Score score);

    /**
     * ScoreForUpdateDto转Score
     *
     * @param dto ScoreForUpdateDto
     * @return Score
     */
    Score mapByUpdateDto(ScoreForUpdateDto dto);
}
