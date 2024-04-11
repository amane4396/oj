package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.lessonUser.*;
import org.oj.entity.LessonUser;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.LessonUserMapper;
import org.oj.mapstruct.LessonUserConvert;
import org.oj.service.LessonUserService;
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
 * 老师/学生课程关联表 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class LessonUserServiceImpl extends ServiceImpl<LessonUserMapper, LessonUser> implements LessonUserService {

    @Resource
    private LessonUserService lessonUserService;

    @Override
    public LessonUser selectWithAssociation(Wrapper<LessonUser> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<LessonUser> selectWithAssociation(Page<LessonUser> page, Wrapper<LessonUser> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<LessonUser> wrapper, Page<LessonUser> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<LessonUser> pageData = lessonUserService.page(page, wrapper);
        // 关联查询
        // Page<LessonUser> pageData = lessonUserService.selectWithAssociation(page, wrapper);

        List<LessonUserForListDto> records = LessonUserConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(LessonUserForCreateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        LessonUser data = LessonUserConvert.INSTANCE.mapByCreateDto(dto);

        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        lessonUserService.save(data);
        // endregion
    }

    @Override
    public LessonUserForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        LessonUser data = lessonUserService.getById(id);
        // 关联
        // LessonUser data = lessonUserService.selectWithAssociation(new QueryWrapper<LessonUser>().eq("lessonUser.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        return LessonUserConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(LessonUserForUpdateDto dto) throws Exception {
        LessonUser data = LessonUserConvert.INSTANCE.mapByUpdateDto(dto);
        LessonUser source = lessonUserService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        // region 数据处理、入库
        lessonUserService.updateById(data);
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
            LessonUser data = lessonUserService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }

        }
        // endregion
        if (ids.size() > 0) {
            lessonUserService.removeByIds(ids);
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
