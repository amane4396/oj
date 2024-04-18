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
 *  department
 *
 * @author XT
 * @create 2024-04-18
 * @update 2024-04-18
 */
@Data
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField("id")
    private String id;

    /**
     * 院系名称
     */
    @TableField("name")
    private String name;

    /**
     * 
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 院系代码
     */
    @TableField("code")
    private String code;

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
