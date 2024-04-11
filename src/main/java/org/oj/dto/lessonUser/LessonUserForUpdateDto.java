package org.oj.dto.lessonUser;

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
public class LessonUserForUpdateDto implements Serializable {

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
     * 1.学生 2.老师
     */
    private Integer type;
}
