package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.classTeacher.*;
import org.oj.entity.ClassTeacher;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.ClassTeacherMapper;
import org.oj.mapstruct.ClassTeacherConvert;
import org.oj.service.ClassTeacherService;
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
 * 老师、班级关联表	 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class ClassTeacherServiceImpl extends ServiceImpl<ClassTeacherMapper, ClassTeacher> implements ClassTeacherService {

    @Resource
    private ClassTeacherService classTeacherService;

    @Override
    public ClassTeacher selectWithAssociation(Wrapper<ClassTeacher> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<ClassTeacher> selectWithAssociation(Page<ClassTeacher> page, Wrapper<ClassTeacher> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<ClassTeacher> wrapper, Page<ClassTeacher> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<ClassTeacher> pageData = classTeacherService.page(page, wrapper);
        // 关联查询
        // Page<ClassTeacher> pageData = classTeacherService.selectWithAssociation(page, wrapper);

        List<ClassTeacherForListDto> records = ClassTeacherConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(ClassTeacherForCreateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        ClassTeacher data = ClassTeacherConvert.INSTANCE.mapByCreateDto(dto);


        // region 外键有效性检测
        // TODO
        // if (xxxxService.getById(data.xxxxid) == null) {
        //     throw new ActiveException(ResCode.BAD, "请选择xxxxx");
        // }
        // endregion

        // region 数据重复检测
        // xxxx不允许相同
        // TODO
        // if (classTeacherService.count(new LambdaQueryWrapper<ClassTeacher>().eq(ClassTeacher::getName, data.getName())) > 0) {
        //     throw new ActiveException("已存在相同xxxxx");
        // }
        // endregion

        // region 其他检测
        // endregion

        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        classTeacherService.save(data);
        // endregion
    }

    @Override
    public ClassTeacherForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        ClassTeacher data = classTeacherService.getById(id);
        // 关联
        // ClassTeacher data = classTeacherService.selectWithAssociation(new QueryWrapper<ClassTeacher>().eq("classTeacher.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        return ClassTeacherConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(ClassTeacherForUpdateDto dto) throws Exception {
        ClassTeacher data = ClassTeacherConvert.INSTANCE.mapByUpdateDto(dto);
        ClassTeacher source = classTeacherService.getById(data.getId());

        // region 数据处理、入库
        classTeacherService.updateById(data);
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
            ClassTeacher data = classTeacherService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }



            // 关联数据检测
            // TODO
            // if (xxxxService.count(new LambdaQueryWrapper<Xxxx>().eq(Xxxx::getXxxxId, id)) > 0) {
            //     associatedCount++;
            //     ids.remove(i--);
            //     continue;
            // }

        }
        // endregion

        // region 删除数据
        // 关联数据删除
        // TODO

        if (ids.size() > 0) {
            classTeacherService.removeByIds(ids);
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

    // @Override
    // public void importData(MultipartRequest multipartRequest) throws Exception {
    //     // TODO
    // }

    // @Override
    // public void exportData(QueryWrapper<ClassTeacher> wrapper) throws Exception {
    //     // TODO
    // }

    // @Override
    // public List<SelectListItem> selectList() throws Exception {
    //     // TODO 补充排序字段，或根据传递的参数筛选数据
    //     List<SelectListItem> options = classTeacherService.list(new QueryWrapper<ClassTeacher>().orderByAsc("xxxx")).stream().map(item -> {
    //         SelectListItem option = new SelectListItem();
    //         option.setKey(item.getId());
    //         option.setText(item.getXxxx());
    //         return option;
    //     }).collect(Collectors.toList());
    //     return options;
    // }
}
