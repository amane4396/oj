package org.oj.dto.classTeacher;

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
public class ClassTeacherForUpdateDto implements Serializable {

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
}
