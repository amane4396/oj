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
 * 提交记录 submit
 *
 * @author XT
 * @create 2024-04-13
 * @update 2024-04-13
 */
@Data
@TableName("submit")
public class Submit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField("id")
    private String id;

    /**
     * 学生id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 通过样例
     */
    @TableField("pass_num")
    private String passNum;

    /**
     * 1.accept	2.failed	3.overtime	
     */
    @TableField("state")
    private Integer state;

    /**
     * 
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 提交代码内容
     */
    @TableField("code")
    private String code;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 题号
     */
    @TableField("task_id")
    private String taskId;

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
