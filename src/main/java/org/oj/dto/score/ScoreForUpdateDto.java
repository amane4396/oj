package org.oj.dto.score;

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
public class ScoreForUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String teacherId;

    /**
     * 
     */
    private String submitId;

    /**
     * 评价
     */
    private String remark;

    /**
     * 分数
     */
    private Integer score;
}
