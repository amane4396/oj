package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.classTeacher.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 老师、班级关联表	 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ClassTeacherService extends IService<ClassTeacher>, ServiceWithAssociation<ClassTeacher> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<ClassTeacher>
     * @param page    Page<ClassTeacher>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<ClassTeacher> wrapper, Page<ClassTeacher> page) throws Exception;

    /**
     * 添加
     *
     * @param dto ClassTeacherForCreateDto
     * @throws Exception Exception
     */
    void create(ClassTeacherForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return ClassTeacherForDetailDto
     * @throws Exception Exception
     */
    ClassTeacherForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto ClassTeacherForUpdateDto
     * @throws Exception Exception
     */
    void update(ClassTeacherForUpdateDto dto) throws Exception;

    /**
     * 删除
     *
     * @param ids List<String>
     * @throws Exception Exception
     */
    void delete(List<String> ids) throws Exception;

    // /**
    //  * 导入
    //  *
    //  * @param multipartRequest MultipartRequest
    //  * @throws Exception Exception
    //  */
    // void importData(MultipartRequest multipartRequest) throws Exception;

    // /**
    //  * 导出
    //  *
    //  * @param wrapper QueryWrapper<ClassTeacher>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<ClassTeacher> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
