package com.qk.mapper;

import com.qk.entity.Character;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CharacterMapper {
    /*
    角色列表查询
     */
    public List<Character> findRoles(String name, String label);
    /*
    角色删除:根据id删除
     */
    @Delete("delete from role where id = #{id}")
    void delete(Integer id);
    /*
    角色添加
     */
    @Insert("insert into role (name, label, remark, create_time, update_time) values (#{name},#{label},#{remark},#{createTime},#{updateTime})")
    void addRole(Character character);
    /*
    查询角色
     */
    @Select("select id, name, label, remark, create_time, update_time from role where id = #{id}")
    Character findRoleById(Integer id);
    /*
    角色修改
     */
    void updateRole(Character character);

    /*
    查询所有角色
     */
    @Select("select id, name, label, remark, create_time, update_time from role")
    List<Character> findAllRoles();

}
