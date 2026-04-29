package com.qk.mapper;

import com.qk.dto.UserDto;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    /*
    查询用户
     */
    public List<User> findUsers(UserDto userDto);
    /*
    新增用户所有信息
     */
    @Insert("insert into user(username, password, name, phone, email, gender, status, dept_id, role_id, image, remark, create_time, update_time) values(#{username},#{password},#{name},#{phone},#{email},#{gender},#{status},#{deptId},#{roleId},#{image},#{remark},#{createTime},#{updateTime})")
    void addUser(User user);
    /*
    批量删除用户
     */
    void delete(List<Integer> ids);
    /*
    根据id查询用户用于数据回显
     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);
    /*
    修改用户
     */
    @Update("update user set username=#{username},password=#{password},name=#{name},phone=#{phone},email=#{email},gender=#{gender},status=#{status},dept_id=#{deptId},role_id=#{roleId},image=#{image},remark=#{remark},update_time=#{updateTime} where id=#{id}")
    void updateUser(User user);
    /*
    查询所有用户
     */
    @Select("select * from user")
    List<User> findAllUsers();
    /*
    根据角色查询用户
     */
    @Select("select * from user where role_id=(select id from role where label=#{roleLabel})")
    List<User> findUsersByRoleId(String roleLabel);
    /*
    根据部门查询用户
     */
    @Select("select * from user where dept_id=#{deptId}")
    List<User> findUsersByDeptId(Integer deptId);
    /*
    用户登录验证响应数据
     */
    @Select("select u.*,r.label role_Label from user u left join role r on u.role_id = r.id where username = #{username};")
    User findUserByUsername(String username);
}
