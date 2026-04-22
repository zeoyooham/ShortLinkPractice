package com.zyh.shortlink.project.common.biz.user;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * author 邹宇航  @vision 1.0
 */
@Component
public class UserTransmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) throws Exception {
        String username = request.getHeader("username");
        if (StrUtil.isNotBlank(username)) {
            String userId = request.getHeader("userId");
            String realName = request.getHeader("realName");
            UserInfoDTO userInfoDTO = new UserInfoDTO(userId, username, realName);
            UserContext.setUser(userInfoDTO);
        }
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler, Exception exception) throws Exception {
        UserContext.removeUser();
    }
}
