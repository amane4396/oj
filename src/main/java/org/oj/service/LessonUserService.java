package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.lessonUser.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 老师/学生课程关联表 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface LessonUserService extends IService<LessonUser>, ServiceWithAssociation<LessonUser> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<LessonUser>
     * @param page    Page<LessonUser>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<LessonUser> wrapper, Page<LessonUser> page) throws Exception;

    /**
     * 添加
     *
     * @param dto LessonUserForCreateDto
     * @throws Exception Exception
     */
    void create(LessonUserForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return LessonUserForDetailDto
     * @throws Exception Exception
     */
    LessonUserForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto LessonUserForUpdateDto
     * @throws Exception Exception
     */
    void update(LessonUserForUpdateDto dto) throws Exception;

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
    //  * @param wrapper QueryWrapper<LessonUser>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<LessonUser> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
