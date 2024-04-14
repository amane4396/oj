package org.oj.dto.task;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 编辑使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-14
 * @update 2024-04-14
 */
@Data
public class TaskForUpdateDto implements Serializable {

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
     * 模板内容
     */
    private String template;

    /**
     * 
     */
    private String hard;
}
