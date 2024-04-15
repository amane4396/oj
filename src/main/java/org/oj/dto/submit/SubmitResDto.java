package org.oj.dto.submit;

import lombok.Data;
import lombok.ToString;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * 提交返回dto
 *
 * @author xt
 * @date 2024/4/15 13:06:31
 */
@Data
@ToString
public class SubmitResDto {

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 执行总时间
     */
    private String runTime;

    /**
     * 通过样例数
     */
    private String passNum;

    /**
     * 失败原因
     */
    private String reason;

}
