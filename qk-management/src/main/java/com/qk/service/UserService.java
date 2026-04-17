package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;

import java.util.List;

public interface UserService {
    /*
    查询用户
     */
    PageResult<User> findUsers(UserDto userDto);
    /*
    新增用户
     */
    void addUser(User user);

    /*
    批量删除用户
     */
    void delete(List<Integer> ids);
    /*
    修改用户:数据回显
     */
    User findUserById(Integer id);
    /*
    修改用户:修改回显数据
     */
    void updateUser(User user);
    /*
    查询所有用户
     */
    List<User> findAllUsers();

    /*
    根据角色查询用户(多表查询)
     */
    List<User> findUsersByRoleId(String roleLabel);
    /*
    根据部门id查询用户
     */
    List<User> findUsersByDeptId(Integer deptId);

}
