package org.oj.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端枚举类型下拉列表选项封装类
 *
 * @author DH
 * @create 2020-12-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectListEnumItem {

    /**
     * 值
     */
    private int key;
    /**
     * 显示文字
     */
    private String text;

}
