package org.oj.dto.department;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
@Data
public class DepartmentForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 院系名称
     */
    private String name;

    /**
     * 院系代码
     */
    private String code;
}
