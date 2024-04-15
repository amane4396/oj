package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.submit.*;
import org.oj.entity.*;
import org.oj.util.JsonRes;

import java.util.HashMap;
import java.util.List;

/**
 * 提交记录 服务类
 *
 * @author XT
 * @create 2024-04-13
 * @update 2024-04-13
 */
public interface SubmitService extends IService<Submit>, ServiceWithAssociation<Submit> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<Submit>
     * @param page    Page<Submit>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<Submit> wrapper, Page<Submit> page) throws Exception;

    /**
     * 添加
     *
     * @param dto SubmitForCreateDto
     * @throws Exception Exception
     */
    void create(SubmitForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return SubmitForDetailDto
     * @throws Exception Exception
     */
    SubmitForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto SubmitForUpdateDto
     * @throws Exception Exception
     */
    void update(SubmitForUpdateDto dto) throws Exception;

    /**
     * 删除
     *
     * @param ids List<String>
     * @throws Exception Exception
     */
    void delete(List<String> ids) throws Exception;

    /**
     * 检测并且提交代码
     *
     * @param dto Sub
     */
    JsonRes submit(SubmitForCreateDto dto) throws Exception;

    /**
     * 测试代码
     *
     * @param runCodeDto RunCodeDto
     * @throws Exception Exp
     * @return JsonRes res
     */
    JsonRes testCode(RunCodeDto runCodeDto) throws Exception;
}
