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
 * 老师/学生课程关联表 lesson_user
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
@TableName("lesson_user")
public class LessonUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField("id")
    private String id;

    /**
     * 
     */
    @TableField("user_id")
    private String userId;

    /**
     * 
     */
    @TableField("lesson_id")
    private String lessonId;

    /**
     * 
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 1.学生 2.老师
     */
    @TableField("type")
    private Integer type;

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
