package com.yw.sca.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangwei
 * @date 2020-12-27 21:54
 */
@Configuration
public class FilterContextConfig {
    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        // 默认情况下，Sentinel Web Filter收敛所有URL入口的Context
        // 解释：例如 /list 和 /all 这两个接口对 qpsFlowRule 资源的访问，对于整个系统是区分不开的
        // 这里设置关闭URL入口收敛功能，这样就可以区分
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }
}
