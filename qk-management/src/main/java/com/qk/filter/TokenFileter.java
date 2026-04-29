package com.qk.filter;
import com.qk.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
/*@WebFilter(urlPatterns = "/*")*///表示截取所有类型的请求
public class TokenFileter implements Filter {//init 和 destroy是默认标签可以不重写
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将请求头和响应头转化为HTTP协议对应的类型
        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        //获取请求
        String uri = httprequest.getRequestURI();
        //判断是否为登录请求
        if (uri.contains("/login")){
            //对登录请求放行,之后就是正常的访问登录请求
            chain.doFilter(request,response);
            return;
        }
        //获取请求头Token获取令牌
        String jwt = httprequest.getHeader("Token");
        //判断Token是否存在
        if (jwt == null || jwt.isEmpty()){
            //响应错误信息401
            log.info("请求token不存在");
            httpresponse.setStatus(401);
            return;
        }
        //然后再解析Token是否合法(也可能会出错)
        try {
            Claims claims = JwtUtils.parseToken(jwt);//这是jwtutils自带的解析jwt令牌的方法
        } catch (Exception e) {
            log.info("解析令牌失败");
            httpresponse.setStatus(401);
            return;
        }
        //放行
        chain.doFilter(request,response);
    }
}
