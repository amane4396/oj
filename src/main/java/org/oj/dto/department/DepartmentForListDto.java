package org.oj.dto.department;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 列表数据使用
 * 后端 -> 前端
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
@Data
public class DepartmentForListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 院系名称
     */
    private String name;

    /**
     *
     */
    private Integer deleted;

    /**
     * 院系代码
     */
    private String code;

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
