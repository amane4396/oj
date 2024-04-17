package org.oj.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 列表工具类
 *
 * @author xt
 * @date 2024/4/15 17:12:07
 */
@Slf4j
public class ListUtil {

    public static List<?> toList(String s){
        List<String> strings = Arrays.asList(s.substring(0, s.length() - 1).split(","));
        Stream<?> stream = strings.stream();
        return null;

    }

}
