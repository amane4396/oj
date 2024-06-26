package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.score.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 评分表 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface ScoreService extends IService<Score>, ServiceWithAssociation<Score> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<Score>
     * @param page    Page<Score>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<Score> wrapper, Page<Score> page) throws Exception;

    /**
     * 添加
     *
     * @param dto ScoreForCreateDto
     * @throws Exception Exception
     */
    void create(ScoreForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return ScoreForDetailDto
     * @throws Exception Exception
     */
    ScoreForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto ScoreForUpdateDto
     * @throws Exception Exception
     */
    void update(ScoreForUpdateDto dto) throws Exception;

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
    //  * @param wrapper QueryWrapper<Score>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<Score> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
