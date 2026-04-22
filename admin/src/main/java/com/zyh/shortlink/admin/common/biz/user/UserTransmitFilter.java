package com.zyh.shortlink.admin.common.biz.user;

import cn.hutool.http.server.HttpServerRequest;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;


/**
 * author 邹宇航  @vision 1.0
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServerRequest httpServerRequest = (HttpServerRequest) request;
        String username=httpServerRequest.getHeader("username");
        if(StringUtils.isNotBlank(username)){
            String userId=httpServerRequest.getHeader("userId");
            String realName=httpServerRequest.getHeader("realName");
            UserInfoDTO userInfoDTO=new UserInfoDTO(userId,username,realName);
            UserContext.setUser(userInfoDTO);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            UserContext.removeUser();
        }
    }
}
