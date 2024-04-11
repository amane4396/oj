package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.tClass.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 班级表 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface TClassService extends IService<TClass>, ServiceWithAssociation<TClass> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<TClass>
     * @param page    Page<TClass>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<TClass> wrapper, Page<TClass> page) throws Exception;

    /**
     * 添加
     *
     * @param dto TClassForCreateDto
     * @throws Exception Exception
     */
    void create(TClassForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return TClassForDetailDto
     * @throws Exception Exception
     */
    TClassForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto TClassForUpdateDto
     * @throws Exception Exception
     */
    void update(TClassForUpdateDto dto) throws Exception;

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
    //  * @param wrapper QueryWrapper<TClass>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<TClass> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
