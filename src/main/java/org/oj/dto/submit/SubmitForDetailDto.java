package org.oj.dto.submit;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 详情、编辑使用
 * 后端 -> 前端
 *
 * @author XT
 * @create 2024-04-15
 * @update 2024-04-15
 */
@Data
public class SubmitForDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

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
     *
     */
    private Integer deleted;

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

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private LocalDateTime updateTime;

    // region 枚举名称、外键名称===========================================================

    // endregion =============================================================================================
}
