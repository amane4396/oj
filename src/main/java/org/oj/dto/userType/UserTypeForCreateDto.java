package org.oj.dto.userType;

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
public class UserTypeForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型:1)管理员 2) 老师 3）学生
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String name;
}
