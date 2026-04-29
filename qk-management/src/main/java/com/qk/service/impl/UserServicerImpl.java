package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.exception.BuisnessException;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import com.qk.utils.JwtUtils;
import com.qk.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        //查看一下时间的格式
        System.out.println(user.getCreateTime());
        System.out.println(user.getUpdateTime());

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
    /*
    用户登录验证
     */
    @Override
    public LoginResultVo login(User user) {
        // 1.调用mapper层获取对应用户
        User u = userMapper.findUserByUsername(user.getUsername());

        // 2.判断用户是否存在
        if (u == null){
            log.info("用户不存在");
            throw new BuisnessException("用户名或密码错误");
        }

        // 4.验证密码(密码是加密的)
        String _password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (!u.getPassword().equals(_password)){
            log.info("密码错误");
            throw new BuisnessException("用户名或密码错误");
        }

        // 5.判断状态是否正常
        if (u.getStatus().equals(0)){
            log.info("用户状态异常");
            throw new BuisnessException("用户已经停用");
        }
        // 6.响应封装结果
        // 封装
        //创建令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",u.getId());
        claims.put("username",u.getUsername());
        String token = JwtUtils.generateToken(claims);
        LoginResultVo loginResultVo = new LoginResultVo(u.getId(),u.getUsername(),u.getName(),u.getImage(),u.getRoleLabel(),token);
        return loginResultVo;
    }

}
