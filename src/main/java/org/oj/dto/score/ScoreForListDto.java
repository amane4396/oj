package org.oj.dto.score;

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
public class ScoreForListDto implements Serializable {

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
     *
     */
    private Integer deleted;

    /**
     * 评价
     */
    private String remark;

    /**
     * 分数
     */
    private Integer score;

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
