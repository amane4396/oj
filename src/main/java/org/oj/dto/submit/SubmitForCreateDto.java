package org.oj.dto.submit;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-15
 * @update 2024-04-15
 */
@Data
public class SubmitForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private String userId;

    /**
     * 通过样例
     */
    private String passNum;

    /**
     * 1.accept	2.failed	3.overtime	
     */
    private Integer state;

    /**
     * 提交代码内容
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 运行时间
     */
    private String runTime;

    /**
     * 题号
     */
    private String taskId;
}
