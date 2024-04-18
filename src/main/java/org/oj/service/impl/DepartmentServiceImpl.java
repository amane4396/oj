package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.department.*;
import org.oj.entity.Department;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.DepartmentMapper;
import org.oj.mapstruct.DepartmentConvert;
import org.oj.service.DepartmentService;
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
 *  服务实现类
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Resource
    private DepartmentService departmentService;

    @Override
    public Department selectWithAssociation(Wrapper<Department> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<Department> selectWithAssociation(Page<Department> page, Wrapper<Department> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<Department> wrapper, Page<Department> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<Department> pageData = departmentService.page(page, wrapper);
        // 关联查询
        // Page<Department> pageData = departmentService.selectWithAssociation(page, wrapper);

        List<DepartmentForListDto> records = DepartmentConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(DepartmentForCreateDto dto) throws Exception {
        Department data = DepartmentConvert.INSTANCE.mapByCreateDto(dto);
        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        departmentService.save(data);
        // endregion
    }

    @Override
    public DepartmentForDetailDto detail(String id) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联
        Department data = departmentService.getById(id);
        // 关联
        // Department data = departmentService.selectWithAssociation(new QueryWrapper<Department>().eq("department.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion
        return DepartmentConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(DepartmentForUpdateDto dto) throws Exception {
        Department data = DepartmentConvert.INSTANCE.mapByUpdateDto(dto);
        Department source = departmentService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        // region 数据处理、入库
        departmentService.updateById(data);
        // endregion
    }

    @Override
    public void delete(List<String> ids) throws Exception {

        int associatedCount = 0;
        int notFoundCount = 0;
        int oldSize = ids.size();

        // region 数据遍历检测
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            // 存在检测
            Department data = departmentService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }
        }
        if (ids.size() > 0) {
            departmentService.removeByIds(ids);
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
