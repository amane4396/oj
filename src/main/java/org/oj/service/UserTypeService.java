package org.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.userType.*;
import org.oj.entity.*;

import java.util.HashMap;
import java.util.List;

/**
 * 用户类型表	 服务类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
public interface UserTypeService extends IService<UserType>, ServiceWithAssociation<UserType> {

    /**
     * 数据列表
     *
     * @param wrapper QueryWrapper<UserType>
     * @param page    Page<UserType>
     * @return HashMap<?, ?>
     * @throws Exception Exception
     */
    HashMap<?, ?> list(QueryWrapper<UserType> wrapper, Page<UserType> page) throws Exception;

    /**
     * 添加
     *
     * @param dto UserTypeForCreateDto
     * @throws Exception Exception
     */
    void create(UserTypeForCreateDto dto) throws Exception;

    /**
     * 详情
     *
     * @param id String
     * @return UserTypeForDetailDto
     * @throws Exception Exception
     */
    UserTypeForDetailDto detail(String id) throws Exception;

    /**
     * 更新
     *
     * @param dto UserTypeForUpdateDto
     * @throws Exception Exception
     */
    void update(UserTypeForUpdateDto dto) throws Exception;

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
    //  * @param wrapper QueryWrapper<UserType>
    //  * @throws Exception Exception
    //  */
    // public void exportData(QueryWrapper<UserType> wrapper) throws Exception;

    // /**
    //  * 获取下拉列表框封装数据
    //  *
    //  * @return List<SelectListItem>
    //  * @throws Exception Exception
    //  */
    // List<SelectListItem> selectList() throws Exception;
}
