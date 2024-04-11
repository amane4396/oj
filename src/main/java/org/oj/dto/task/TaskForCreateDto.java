package org.oj.dto.task;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class TaskForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目内容
     */
    private String contents;

    /**
     * 题目标题
     */
    private String title;
}
