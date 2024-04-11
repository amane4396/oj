package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.example.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 样例表 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ExampleService extends IService<Example>, ServiceWithAssociation<Example> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<Example>
     * @param page    Page<Example>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<Example> wrapper, Page<Example> page) throws Exception;

    /**
     * 添加
     *
     * @param dto ExampleForCreateDto
     * @throws Exception Exception
     */
    void create(ExampleForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return ExampleForDetailDto
     * @throws Exception Exception
     */
    ExampleForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto ExampleForUpdateDto
     * @throws Exception Exception
     */
    void update(ExampleForUpdateDto dto) throws Exception;

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
    //  * @param wrapper QueryWrapper<Example>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<Example> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
