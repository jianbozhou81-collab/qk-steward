package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.User;
import com.qk.service.UserService;
import com.qk.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /*
    用户登录验证
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // 1.调用service层
        LoginResultVo loginResultVo = userService.login(user);
        // 2.响应数据
        return Result.success(loginResultVo);
    }
}
