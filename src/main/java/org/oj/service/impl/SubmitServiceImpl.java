package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.oj.base.SelectListEnumItem;
import org.oj.base.SelectListItem;
import org.oj.constant.*;
import org.oj.dto.submit.*;
import org.oj.entity.Example;
import org.oj.entity.Submit;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapper.SubmitMapper;
import org.oj.mapstruct.SubmitConvert;
import org.oj.service.ExampleService;
import org.oj.service.SubmitService;
import org.oj.service.UserService;
import org.oj.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.oj.vo.Question;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 提交记录 服务实现类
 *
 * @author XT
 * @create 2024-04-13
 * @update 2024-04-13
 */
@Service
public class SubmitServiceImpl extends ServiceImpl<SubmitMapper, Submit> implements SubmitService {

    @Resource
    private SubmitService submitService;

    @Resource
    private UserService userService;

    @Resource
    private ExampleService exampleService;

    @Override
    public Submit selectWithAssociation(Wrapper<Submit> wrapper) {
        return baseMapper.selectWithAssociation(wrapper);
    }

    @Override
    public Page<Submit> selectWithAssociation(Page<Submit> page, Wrapper<Submit> wrapper) {
        return baseMapper.selectWithAssociation(page, wrapper);
    }

    @Override
    public HashMap<?, ?> list(QueryWrapper<Submit> wrapper, Page<Submit> page) throws Exception {
        // TODO 关联与非关联选其一
        // 非关联查询
        Page<Submit> pageData = submitService.page(page, wrapper);
        // 关联查询
        // Page<Submit> pageData = submitService.selectWithAssociation(page, wrapper);

        List<SubmitForListDto> records = SubmitConvert.INSTANCE.mapToList(pageData.getRecords());
        return DataListUtil.formatPageData(pageData, records);
    }

    @Override
    public void create(SubmitForCreateDto dto) throws Exception {
        Submit data = SubmitConvert.INSTANCE.mapByCreateDto(dto);

        // region 数据处理、入库
        data.setId(UuidUtil.generate());
        submitService.save(data);
        // endregion
    }

    @Override
    public SubmitForDetailDto detail(String id) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        // TODO 关联与非关联选其一
        // 非关联
        Submit data = submitService.getById(id);
        // 关联
        // Submit data = submitService.selectWithAssociation(new QueryWrapper<Submit>().eq("submit.id", id));

        // region 存在检测
        if (data == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion
        return SubmitConvert.INSTANCE.mapToDetailDto(data);
    }

    @Override
    public void update(SubmitForUpdateDto dto) throws Exception {
        User operator = PermissionUtil.getCurrentUser();
        Submit data = SubmitConvert.INSTANCE.mapByUpdateDto(dto);
        Submit source = submitService.getById(data.getId());

        // region 存在检测
        if (source == null) {
            throw new ActiveException(ResCode.NOT_FOUND);
        }
        // endregion
        // region 数据处理、入库
        submitService.updateById(data);
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
            Submit data = submitService.getById(id);
            if (data == null) {
                notFoundCount++;
                ids.remove(i--);
                continue;
            }
        }
        // endregion

        // region 删除数据
        // 关联数据删除
        // TODO

        if (ids.size() > 0) {
            submitService.removeByIds(ids);
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

    @Override
    public JsonRes submit(SubmitForCreateDto dto) throws Exception {
//        User user = PermissionUtil.getCurrentUser();
        User user = userService.getById(dto.getUserId());
        if(user == null){
            throw new ActiveException("非法提交！");
        }

        Question question = new Question();
        question.setCode(dto.getCode());
        question.setStdin("");
        // 编译代码
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Task task = new Task();
        SubmitResDto resDto = new SubmitResDto();
        try {
            task.compile(question);
        } catch (Exception e) {
            resDto.setReason(e.getMessage());
            resDto.setMsg("编译错误");
            resDto.setRunTime("ERR");
            resDto.setPassNum("0");
            dto.setPassNum("0");
            dto.setRemark("编译错误");
            dto.setState(2);
            submitService.create(dto);
            return JsonRes.success(resDto);
        }
        // 加载类获取方法反射
        Class<?> cls = customClassLoader.loadClassFromFile("./tmp/Solution.class");
        LambdaQueryWrapper<Example> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Example::getTaskId, dto.getTaskId());
        Method solution = cls.getMethod("solution", String.class);
        List<Example> examples = exampleService.list(lambdaQueryWrapper);
        int count = examples.size();
        int passed = 0;
        long begin = System.currentTimeMillis();
        for(Example example : examples){
            String str =  solution.invoke(cls, example.getInput()).toString();
            if(str.equals(example.getResult())){
                passed++;
            }else{
                resDto.setReason(example.getInput());
            }
        }
        long end = System.currentTimeMillis();
        if(passed == count){
            dto.setState(1);
            dto.setRemark("通过");
            resDto.setMsg("ACCEPT");
        }else{
            dto.setState(2);
            dto.setRemark("样例未全部通过");
            resDto.setMsg("FAILED");
        }
        dto.setPassNum(passed + "/" + count);

        dto.setRunTime(String.valueOf(end - begin));
        submitService.create(dto);
        resDto.setRunTime((end - begin) + "ms");
        resDto.setPassNum(passed + "/" + count);
        return JsonRes.success(resDto);
    }

    @Override
    public JsonRes testCode(RunCodeDto dto) throws ActiveException {
        User user = userService.getById(dto.getUserId());
        if(user == null){
            throw new ActiveException("非法提交！");
        }

        Question question = new Question();
        question.setCode(dto.getCode());
        question.setStdin("");
        RunResDto resDto = new RunResDto();
        // 编译代码
        Task task = new Task();
        try {
            task.compile(question);
        } catch (Exception e) {
            resDto.setRes("编译错误:\n" + e.getMessage());
            resDto.setStdout("");
            return JsonRes.success(resDto);
        }


        try {
            resDto.setStdout(task.run());
        } catch (Exception e) {
            resDto.setRes("运行失败:\n" + e.getMessage());
            resDto.setStdout("");
            return JsonRes.success(resDto);
        }
        resDto.setRes("运行成功");
        return JsonRes.success(resDto);
    }
}
