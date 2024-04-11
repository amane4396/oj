package org.oj.dto.classTeacher;

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
public class ClassTeacherForListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 班级id
     */
    private String classId;

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
