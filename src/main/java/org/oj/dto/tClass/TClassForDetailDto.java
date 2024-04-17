package org.oj.dto.tClass;

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
public class TClassForDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     *
     */
    private Integer deleted;

    /**
     * 院系id
     */
    private String departmentId;

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
