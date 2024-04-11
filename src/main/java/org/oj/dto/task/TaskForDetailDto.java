package org.oj.dto.task;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 详情、编辑使用
 * 后端 -> 前端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class TaskForDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 题目内容
     */
    private String contents;

    /**
     * 题目标题
     */
    private String title;

    /**
     *
     */
    private Integer deleted;

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
