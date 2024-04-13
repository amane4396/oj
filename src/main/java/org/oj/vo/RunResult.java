package org.oj.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 返回代码运行情况信息
 *
 * @author xt
 * @date 2024/4/13 19:10:25
 */
@Data
@ToString
public class RunResult {

    /**
     * 通过样例数量
     */
    private Integer passes;

    /**
     * 运行信息
     */
    private String remark;

    /**
     * 执行结果
     */
    private Integer state;

}
