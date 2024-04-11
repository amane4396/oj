package org.oj.dto.lesson;

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
public class LessonForListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程信息
     */
    private String infomation;

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
