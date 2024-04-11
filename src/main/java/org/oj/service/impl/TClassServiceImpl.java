package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.tClass.*;
import org.oj.entity.TClass;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.TClassMapper;
import org.oj.mapstruct.TClassConvert;
import org.oj.service.TClassService;
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
 * 班级表 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class TClassServiceImpl extends ServiceImpl<TClassMapper, TClass> implements TClassService {

    @Resource
    private TClassService tClassService;

    @Override
    public TClass selectWithAssociation(Wrapper<TClass> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<TClass> selectWithAssociation(Page<TClass> page, Wrapper<TClass> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<TClass> wrapper, Page<TClass> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<TClass> pageData = tClassService.page(page, wrapper);
        // 关联查询
        // Page<TClass> pageData = tClassService.selectWithAssociation(page, wrapper);

        List<TClassForListDto> records = TClassConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(TClassForCreateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        TClass data = TClassConvert.INSTANCE.mapByCreateDto(dto);

        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        tClassService.save(data);
        // endregion
    }

    @Override
    public TClassForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        TClass data = tClassService.getById(id);
        // 关联
        // TClass data = tClassService.selectWithAssociation(new QueryWrapper<TClass>().eq("tClass.id", id));

        return TClassConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(TClassForUpdateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        TClass data = TClassConvert.INSTANCE.mapByUpdateDto(dto);
        TClass source = tClassService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        // region 数据处理、入库
        tClassService.updateById(data);
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
            TClass data = tClassService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }
        }
        if (ids.size() > 0) {
            tClassService.removeByIds(ids);
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
