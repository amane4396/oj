package org.oj.dto.userType;

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
public class UserTypeForDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     * 类型:1)管理员 2) 老师 3）学生
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 逻辑删除
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
