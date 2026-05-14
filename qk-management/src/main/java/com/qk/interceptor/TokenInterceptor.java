package com.qk.interceptor;

import com.qk.utils.CurrentUserHoler;
import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    //目标资源方法(controller)运行之前运行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String url = request.getRequestURI();
        //判断是否为登录请求
        if (url.contains("/login")) {
            log.info("登录请求");
            return true;
        }
        //获取请求头Token
        String jwt = request.getHeader("Token");
        //判断Token是否存在
        if (jwt == null || jwt.isEmpty()) {
            log.info("请求头Token不存在");
            response.setStatus(401);
            return false;
        }
        //解析Token是否合法
        try {
            Claims claims = JwtUtils.parseToken(jwt);
            Integer id = claims.get("id", Integer.class);
            CurrentUserHoler.setCurrentUser(id);
            log.info("当前用户id已经存入ThreadId:{}", id);
        } catch (Exception e) {
            response.setStatus(401);
            log.info("解析Token失败");
            return false;
        }
        //放行
        return true;
    }
    //目标资源方法(controller)运行之后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //释放资源
        CurrentUserHoler.removeCurrentUser();
    }
}
