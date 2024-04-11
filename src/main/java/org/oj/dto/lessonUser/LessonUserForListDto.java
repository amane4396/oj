package org.oj.dto.lessonUser;

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
public class LessonUserForListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     *
     */
    private String userId;

    /**
     *
     */
    private String lessonId;

    /**
     *
     */
    private Integer deleted;

    /**
     * 1.学生 2.老师
     */
    private Integer type;

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
