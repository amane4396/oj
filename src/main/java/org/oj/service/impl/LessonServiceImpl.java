package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.lesson.*;
import org.oj.entity.Lesson;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.LessonMapper;
import org.oj.mapstruct.LessonConvert;
import org.oj.service.LessonService;
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
 * 课程表 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

    @Resource
    private LessonService lessonService;

    @Override
    public Lesson selectWithAssociation(Wrapper<Lesson> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<Lesson> selectWithAssociation(Page<Lesson> page, Wrapper<Lesson> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<Lesson> wrapper, Page<Lesson> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<Lesson> pageData = lessonService.page(page, wrapper);
        // 关联查询
        // Page<Lesson> pageData = lessonService.selectWithAssociation(page, wrapper);

        List<LessonForListDto> records = LessonConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(LessonForCreateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        Lesson data = LessonConvert.INSTANCE.mapByCreateDto(dto);

        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        lessonService.save(data);
        // endregion
    }

    @Override
    public LessonForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        Lesson data = lessonService.getById(id);
        // 关联
        // Lesson data = lessonService.selectWithAssociation(new QueryWrapper<Lesson>().eq("lesson.id", id));

        return LessonConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(LessonForUpdateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        Lesson data = LessonConvert.INSTANCE.mapByUpdateDto(dto);
        Lesson source = lessonService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        // region 数据处理、入库
        lessonService.updateById(data);
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
            Lesson data = lessonService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }

        }
        // endregion

        if (ids.size() > 0) {
            lessonService.removeByIds(ids);
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
