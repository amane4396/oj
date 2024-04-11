package org.oj.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.oj.constant.*;

/**
 * 评分表 score
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
@TableName("score")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField("id")
    private String id;

    /**
     * 
     */
    @TableField("teacher_id")
    private String teacherId;

    /**
     * 
     */
    @TableField("submit_id")
    private String submitId;

    /**
     * 
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 评价
     */
    @TableField("remark")
    private String remark;

    /**
     * 分数
     */
    @TableField("score")
    private Integer score;

    /**
     * 
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // region 自定义属性及其getter、setter，自定义方法===========================================================

    // /**
    //  * 下拉列表项显示文字
    //  *
    //  * @return String
    //  */
    // public String getSelectText() {
    //     return name;
    // }

    // endregion =============================================================================================
}
