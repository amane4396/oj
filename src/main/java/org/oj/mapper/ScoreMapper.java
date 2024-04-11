package org.oj.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.oj.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 评分表 Mapper 接口
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ScoreMapper extends BaseMapper<Score> {
    /**
     * Score关联查询
     *
     * @param wrapper Wrapper<Score>
     * @return Score
     */
    Score selectWithAssociation(@Param("ew") Wrapper<Score> wrapper);

    /**
     * Score关联分页查询
     *
     * @param page    Page<Score>
     * @param wrapper Wrapper<Score>
     * @return Page<Score>
     */
    Page<Score> selectWithAssociation(Page<Score> page, @Param("ew") Wrapper<Score> wrapper);
}
