package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Character;

import java.util.List;

public interface CharacterService {
    /*
    角色列表查询
     */
    PageResult<Character> findRoles(String name, String label, Integer page, Integer pageSize);
    /*
    角色删除:根据id删除
     */
    void delete(Integer id);
    /*
    新增角色
     */
    void addRole(Character character);
    /*
    根据id查询角色
     */
    Character findRoleById(Integer id);
    /*
    修改角色
     */
    void updateRole(Character character);
    /*
    查询所有角色
     */
    List<Character> findAllRoles();

}
