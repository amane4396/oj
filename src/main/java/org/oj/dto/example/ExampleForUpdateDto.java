package org.oj.dto.example;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 编辑使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class ExampleForUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 输入样例
     */
    private String input;

    /**
     * 答案样例
     */
    private String result;

    /**
     * 题号
     */
    private String taskId;
}
