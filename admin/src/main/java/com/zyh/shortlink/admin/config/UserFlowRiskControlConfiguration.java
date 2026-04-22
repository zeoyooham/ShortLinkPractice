package com.zyh.shortlink.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "short-link.flow-limit")
public class UserFlowRiskControlConfiguration {

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 时间窗口
     */
    private String timeWindow;

    /**
     * 最大访问次数
     */
    private Long maxAccessCount;
}
