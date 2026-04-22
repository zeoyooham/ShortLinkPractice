package com.zyh.shortlink.admin.common.biz.user;


import cn.hutool.json.JSONString;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.zyh.shortlink.admin.config.UserFlowRiskControlConfiguration;
import com.zyh.shortlink.admin.convention.errorcode.BaseErrorCode;
import com.zyh.shortlink.admin.convention.exception.ClientException;
import com.zyh.shortlink.admin.convention.result.Results;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


/**
 * author 邹宇航  @vision 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class UserFlowRiskControlFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private final UserFlowRiskControlConfiguration userFlowRiskControlConfiguration;

    private static final String USER_FLOW_RISK_CONTROL_LUA="/Lua/user_flow_risk_control.lua";

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(USER_FLOW_RISK_CONTROL_LUA)));
        redisScript.setResultType(Long.class);
        String username= Optional.ofNullable(UserContext.getUsername()).orElse("other");
        Long result;
        try{
            result=stringRedisTemplate.execute(redisScript, Lists.newArrayList(username), userFlowRiskControlConfiguration.getTimeWindow(), userFlowRiskControlConfiguration.getMaxAccessCount());
        }catch (Throwable ex){
            log.error("执行的Lua脚本出错",ex);
            returnJson((HttpServletResponse)response, JSON.toJSONString(Results.failure(new ClientException(BaseErrorCode.FLOW_LIMIT_ERROR))));
            return;
        }
        if(result==0||result>=userFlowRiskControlConfiguration.getMaxAccessCount()){
            returnJson((HttpServletResponse)response, JSON.toJSONString(Results.failure(new ClientException(BaseErrorCode.FLOW_LIMIT_ERROR))));
        }
        chain.doFilter(request, response);
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
          response.setCharacterEncoding("UTF-8");
          response.setContentType("text/html; charset=utf-8");
          try(PrintWriter writer=response.getWriter()){
              writer.print(json);
          }
    }
}
