package org.oj.global;

import org.oj.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.HashMap;

/**
 * 全局内存维护数据
 *
 * @author DH
 * @create 2020-12-15
 */
@Component
@Slf4j
public class Global {

    public static HashMap<String, User> tokens = new HashMap<>();

}
