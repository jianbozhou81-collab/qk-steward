package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    查询用户列表:一般请求参数3-5个就行,超了最好用对象封装请求参数.与传送过来的json格式不同,不用@requestbody接收
    ,这个直接接收就行,不过的得保持属性名字完全一样
     */
    @GetMapping
    public Result findUsers(UserDto userDto){
        log.info("封装userDto,{}",userDto);
        //1.调用service层
        PageResult<User> p = userService.findUsers(userDto);
        //2.响应数据
        return Result.success(p);
    }
    /*
    新增用户
     */
    @PostMapping
    public Result addUser(@RequestBody User user){
        log.info("新增用户:{}",user);
        //1.调用service层
        userService.addUser(user);
        //2.响应数据
        return Result.success();
    }
    /*
    删除用户:单选就是只有一个id的list,多选就是多个id的list,直接就包含了两个删除功能无敌了
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除用户:{}",ids);
        //1.调用service层
        userService.delete(ids);

        //2.响应数据
        return Result.success();
    }
    /*
    修改用户:根据id查询用户来回显到修改表单中
     */
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Integer id){
        log.info("修改用户:{}",id);
        //1.调用service层
        User user = userService.findUserById(id);
        //2.响应数据
        return Result.success(user);
    }
    /*
    修改用户:修改回显数据
     */
    @PutMapping
    public Result updateUser(@RequestBody User user){
        log.info("修改用户:{}",user);
        //1.调用service层
        userService.updateUser(user);
        //2.响应数据
        return Result.success();
    }
    /*
    查询所有用户
     */
    @GetMapping("/list")
    public Result findAllUsers(){
        //1.调用service层
        List<User> list = userService.findAllUsers();
        //2.响应数据
        return Result.success(list);
    }
    /*
    根据"角色"查询用户
     */
    @GetMapping("/role/{roleLabel}")
    public Result findUsersByRoleId(@PathVariable String roleLabel){
        log.info("根据角色查询用户:{}",roleLabel);
        // 1.调用service层
        List<User> list = userService.findUsersByRoleId(roleLabel);
        // 2.响应数据
        return Result.success(list);

    }

    /*
    根据"部门"查询用户
     */
    @GetMapping("/dept/{deptId}")
    public Result findUsersByDeptId(@PathVariable Integer deptId){
        log.info("根据部门查询用户:{}",deptId);
        // 1.调用service层
        List<User> list = userService.findUsersByDeptId(deptId);
        // 2.响应数据
        return Result.success(list);

    }

}
