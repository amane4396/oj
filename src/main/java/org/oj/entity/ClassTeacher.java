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
 * 老师、班级关联表	 class_teacher
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
@TableName("class_teacher")
public class ClassTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField("id")
    private String id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 班级id
     */
    @TableField("class_id")
    private String classId;

    /**
     * 
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

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
