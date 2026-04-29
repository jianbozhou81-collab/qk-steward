package com.qk.interceptor;

import com.qk.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String url = request.getRequestURI();
        //判断是否为登录请求
        if (url.contains("/login")){
            log.info("登录请求");
            return true;
        }
        //获取请求头Token
        String jwt = request.getHeader("Token");
        //判断Token是否存在
        if (jwt == null || jwt.isEmpty()){
            log.info("请求头Token不存在");
            response.setStatus(401);
            return false;
        }
        //解析Token是否合法
        try {
            JwtUtils.parseToken(jwt);
        } catch (Exception e) {
            response.setStatus(401);
            log.info("解析Token失败");
            return false;
        }
        //放行
        return true;
    }
}
