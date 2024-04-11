package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.score.*;
import org.oj.entity.Score;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.ScoreMapper;
import org.oj.mapstruct.ScoreConvert;
import org.oj.service.ScoreService;
import org.oj.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评分表 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Resource
    private ScoreService scoreService;

    @Override
    public Score selectWithAssociation(Wrapper<Score> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<Score> selectWithAssociation(Page<Score> page, Wrapper<Score> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<Score> wrapper, Page<Score> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<Score> pageData = scoreService.page(page, wrapper);
        // 关联查询
        // Page<Score> pageData = scoreService.selectWithAssociation(page, wrapper);

        List<ScoreForListDto> records = ScoreConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(ScoreForCreateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        Score data = ScoreConvert.INSTANCE.mapByCreateDto(dto);
        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        scoreService.save(data);
        // endregion
    }

    @Override
    public ScoreForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        Score data = scoreService.getById(id);
        // 关联
        // Score data = scoreService.selectWithAssociation(new QueryWrapper<Score>().eq("score.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        return ScoreConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(ScoreForUpdateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        Score data = ScoreConvert.INSTANCE.mapByUpdateDto(dto);
        Score source = scoreService.getById(data.getId());
        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }

        scoreService.updateById(data);
        // endregion
    }

    @Override
    public void delete(List<String> ids) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        int associatedCount = 0;
        int notFoundCount = 0;
        int oldSize = ids.size();
        // region 数据遍历检测
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            // 存在检测
            Score data = scoreService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }
        }

        if (ids.size() > 0) {
            scoreService.removeByIds(ids);
        }
        // endregion

        if (associatedCount > 0 && notFoundCount > 0) {
            // TODO
            throw new ActiveException(ResMsg.DELETE_DATA_NOT_FOUND_OR_ASSOCIATED + "xxxx");
        } else if (associatedCount == oldSize) {
            // TODO
            throw new ActiveException(ResMsg.ALL_DELETE_DATA_ASSOCIATED + "，xxxx");
        } else if (associatedCount > 0) {
            // TODO
            throw new ActiveException(ResMsg.PARTIAL_DELETE_DATA_ASSOCIATED + "，xxxx");
        } else if (notFoundCount == oldSize) {
            throw new ActiveException(ResMsg.ALL_DELETE_DATA_NOT_FOUND);
        } else if (notFoundCount > 0) {
            throw new ActiveException(ResMsg.PARTIAL_DELETE_DATA_NOT_FOUND);
        }
    }

}
