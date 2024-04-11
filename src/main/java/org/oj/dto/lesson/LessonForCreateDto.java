package org.oj.dto.lesson;

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
public class LessonForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程信息
     */
    private String infomation;
}
