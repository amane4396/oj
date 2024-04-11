package org.oj.dto.example;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 列表数据使用
 * 后端 -> 前端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class ExampleForListDto implements Serializable {

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
