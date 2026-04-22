package com.zyh.shortlink.admin.config;

import com.zyh.shortlink.admin.common.biz.user.UserFlowRiskControlFilter;
import com.zyh.shortlink.admin.common.biz.user.UserTransmitFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * author 邹宇航  @vision 1.0
 */
public class UserConfiguration {

    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(){
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(0);
        return registration;
    }

    @Bean
    @ConditionalOnProperty(name = "short-link.flow-limit.enable",havingValue = "true")
    public  FilterRegistrationBean<UserFlowRiskControlFilter> globalUserFlowRiskControlFilter(
            StringRedisTemplate stringRedisTemplate,
            UserFlowRiskControlConfiguration userFlowRiskControlConfiguration
    ){
        FilterRegistrationBean<UserFlowRiskControlFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserFlowRiskControlFilter(stringRedisTemplate, userFlowRiskControlConfiguration));
        registration.addUrlPatterns("/*");
        registration.setOrder(10);
        return registration;
    }

}
