package org.oj.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端下拉列表选项封装类
 *
 * @author DH
 * @create 2020-12-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectListItem {

    /**
     * 值
     */
    private String key;
    /**
     * 显示文字
     */
    private String text;

}
