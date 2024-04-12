package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.user.*;
import org.oj.entity.User;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.UserMapper;
import org.oj.mapstruct.UserConvert;
import org.oj.service.UserService;
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
 * 用户表	 服务实现类
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserService userService;

    @Override
    public User selectWithAssociation(Wrapper<User> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<User> selectWithAssociation(Page<User> page, Wrapper<User> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<User> wrapper, Page<User> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<User> pageData = userService.page(page, wrapper);
        // 关联查询
        // Page<User> pageData = userService.selectWithAssociation(page, wrapper);

        List<UserForListDto> records = UserConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(UserForCreateDto dto) throws Exception {
//        User operator = PermissionUtil.getCurrentUser();
        User data = UserConvert.INSTANCE.mapByCreateDto(dto);
        if (userService.count(new LambdaQueryWrapper<User>().eq(User::getMemberId, data.getMemberId())) > 0) {
            throw new ActiveException("该用户已存在！");
        }
        if(StringUtils.isEmpty(data.getMemberId())){
            throw new ActiveException("请输入正确的学工号！");
        }
        if(StringUtils.isEmpty(data.getName())){
            throw new ActiveException("请输入正确的姓名！");
        }
        if(StringUtils.isEmpty(data.getPassword())){
            throw new ActiveException("请输入正确格式的密码！");
        }

        data.setSalt(SecurityUtil.SALT);
        data.setPassword(SecurityUtil.encrypt("MD5", data.getPassword(), data.getSalt()));
        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        userService.save(data);
        // endregion
    }

    @Override
    public UserForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        User data = userService.getById(id);
        // 关联
        // User data = userService.selectWithAssociation(new QueryWrapper<User>().eq("user.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        return UserConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(UserForUpdateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        User data = UserConvert.INSTANCE.mapByUpdateDto(dto);
        User source = userService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion

        // region 数据处理、入库
        userService.updateById(data);
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
            User data = userService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }
        }

        if (ids.size() > 0) {
            userService.removeByIds(ids);
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
