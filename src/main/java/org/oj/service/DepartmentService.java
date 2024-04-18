package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.department.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 *  服务类
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
public interface DepartmentService extends IService<Department>, ServiceWithAssociation<Department> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<Department>
     * @param page    Page<Department>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<Department> wrapper, Page<Department> page) throws Exception;

    /**
     * 添加
     *
     * @param dto DepartmentForCreateDto
     * @throws Exception Exception
     */
    void create(DepartmentForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return DepartmentForDetailDto
     * @throws Exception Exception
     */
    DepartmentForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto DepartmentForUpdateDto
     * @throws Exception Exception
     */
    void update(DepartmentForUpdateDto dto) throws Exception;

    /**
     * 删除
     *
     * @param ids List<String>
     * @throws Exception Exception
     */
    void delete(List<String> ids) throws Exception;

}
