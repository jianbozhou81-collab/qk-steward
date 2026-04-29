package com.qk;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {

    /*
    令牌生成
     */
    @Test
    public void createJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "wangxue");
        claims.put("password", "123456");
        String jwt = Jwts.builder()
                .setClaims(claims)//添加自定义的数据信息
                .signWith(SignatureAlgorithm.HS256, "wangxue")//签名算法,密钥
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//设置过期时间
                .compact();//compact()压缩生成令牌
        System.out.println(jwt);
    }


    /*
    令牌解析
    令牌解析报错原因:修改了令牌中的任意字符 or 令牌过期了

     */

    @Test
    public void parseJWT() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoxLCJleHAiOjE3Nzc0MzQxNDEsInVzZXJuYW1lIjoid2FuZ3h1ZSJ9.hAyPEINRSJQq28SVQor4IPhoUT60dSsyARw1buF_mf0";
        Claims wangxue = Jwts.parser()
                .setSigningKey("wangxue")//指定解析密钥
                .parseClaimsJws(jwt)//指定要解析令牌
                .getBody();//获取令牌中的数据

        System.out.println(wangxue);
    }
}
