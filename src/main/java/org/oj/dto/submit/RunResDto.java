package org.oj.dto.submit;

import lombok.Data;
import lombok.ToString;

/**
 * 运行代码结果
 *
 * @author xt
 * @date 2024/4/15 15:36:57
 */
@Data
@ToString
public class RunResDto {

    /**
     * 运行结果
     */
    private String res;

    /**
     * 标准输出
     */
    private String stdout;

}
