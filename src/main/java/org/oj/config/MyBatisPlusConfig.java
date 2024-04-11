package org.oj.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author XT
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("org.oj.mapper")
public class MyBatisPlusConfig {

    /**
     * MybatisPlus分页拦截器
     * @return 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 逻辑删除插件
     */
//    @Bean
//    public ISqlInjector logicSqlInjector() {
//        return new LogicSqlInjector();
//    }

}
