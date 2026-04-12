package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Character;
import com.qk.mapper.CharacterMapper;
import com.qk.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {


    @Autowired
    private CharacterMapper characterMapper;
    /*
    角色列表查询
     */
    @Override
    public PageResult<Character> findRoles(String name, String label, Integer page, Integer pageSize) {
        // 1.第三方插件辅助分页(会使得下一项查询的操作自动进行分页)
        PageHelper.startPage(page,pageSize);
        // 2.调用mapper
        List<Character> list = characterMapper.findRoles(name,label);
        // 3.解析结果
        Page<Character> pageData = (Page<Character>) list;
        return new PageResult<>(pageData.getTotal(),pageData.getResult());
    }
    /*
    角色删除:根据id删除
     */
    @Override
    public void delete(Integer id) {
        // 1.调用mapper
        characterMapper.delete(id);
    }
    /*
    新增角色
     */
    @Override
    public void addRole(Character character) {
        // 1.补全创建时间
        character.setCreateTime(LocalDateTime.now());
        character.setUpdateTime(LocalDateTime.now());
        // 2.调用mapper层
        characterMapper.addRole(character);
    }
    /*
    查询角色
     */
    @Override
    public Character findRoleById(Integer id) {
        // 1.调用mapper
        Character character = characterMapper.findRoleById(id);
        // 2.返回结果
        return character;
    }
    /*
    修改角色:修改回显的部门信息以达成修改
     */
    @Override
    public void updateRole(Character character) {
        // 2.调用mapper层
        characterMapper.updateRole(character);
    }
    /*
    查询所有角色数据
     */
    @Override
    public List<Character> findAllRoles() {
        // 1.调用mapper
        List<Character> list = characterMapper.findAllRoles();
        return list;
    }
}
