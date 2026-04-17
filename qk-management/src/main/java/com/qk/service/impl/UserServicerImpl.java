package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServicerImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    /*
    查询用户
     */
    @Override
    public PageResult<User> findUsers(UserDto userDto) {
        //1.分页操作(给下一个查询操作添加分页操作)
        PageHelper.startPage(userDto.getPage(),userDto.getPageSize());
        //2.调用mapper层查询
        List<User> list = userMapper.findUsers(userDto);
        //3.解析数据
        Page<User> pageData = (Page<User>) list;
        return new PageResult<>(pageData.getTotal(),pageData.getResult());

    }
    /*
    新增用户
     */
    @Override
    public void addUser(User user) {
        //1.补全创建时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        //2.设置默认密码(并进行密码加密)
        String password =user.getUsername() + "123";
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        //3.调用mapper层
        userMapper.addUser(user);
    }
    /*
    批量删除用户
     */
    @Override
    public void delete(List<Integer> ids) {
        userMapper.delete(ids);

    }
    /*
    修改用户数据回显
     */
    @Override
    public User findUserById(Integer id) {
        // 1.调用mapper层
        return userMapper.findUserById(id);
    }
    /*
    修改用户:修改回显数据
     */
    @Override
    public void updateUser(User user) {
        // 1.补全用户数据
        user.setUpdateTime(LocalDateTime.now());
        // 2.调用mapper层
        userMapper.updateUser(user);
    }
    /*
    查询所有用户
     */
    @Override
    public List<User> findAllUsers() {
        // 1.调用mapper层
        return userMapper.findAllUsers();

    }
    /*
    根据用户名查询用户
     */
    @Override
    public List<User> findUsersByRoleId(String roleLabel) {
        // 1.调用mapper层
        return userMapper.findUsersByRoleId(roleLabel);
    }
    /*
    根据部门id查询用户
     */
    @Override
    public List<User> findUsersByDeptId(Integer deptId) {
        return userMapper.findUsersByDeptId(deptId);

    }

}
