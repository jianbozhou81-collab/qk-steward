package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Character;
import com.qk.service.CharacterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/roles")
@RestController
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    /*
    角色列表查询
     */
    @GetMapping
    public Result findRoles(String name, String label, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        //1.调用service层
        log.info("接收到数据name={},label={},page={},pageSize={}",name,label,page,pageSize);
        PageResult<Character> p = characterService.findRoles(name,label,page,pageSize);
        //2.响应数据
        return Result.success(p);

    }
    /*
    角色删除:根据id删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 1.调用service层
        characterService.delete(id);

        // 2.响应数据
        return Result.success();
    }
    /*
    新增角色
     */
    @PostMapping
    public Result addRole(@RequestBody Character character){
        // 日志
        log.info("新增角色:{}",character);
        // 1.调用service层
        characterService.addRole(character);
        // 2.响应数据
        return Result.success();
    }
    /*
    查询角色根据id(用于数据回显)
     */
    @GetMapping("/{id}")
    public Result findRoleById(@PathVariable Integer id){
        // 日志
        log.info("查询角色:{}",id);
        // 1.调用service层
        Character character = characterService.findRoleById(id);
        // 2.响应数据
        return Result.success(character);

    }
    /*
    修改角色
     */
    @PutMapping
    public Result updateRole(@RequestBody Character character){
        // 日志
        log.info("修改角色:{}",character);
        // 1.调用service层
        characterService.updateRole(character);
        // 2.响应数据
        return Result.success();
    }
    /*
    查询所有角色(服务于新增用户的操作)
     */
    @GetMapping("/list")
    public Result findAllRoles(){
        // 1.调用service层
        List<Character> list = characterService.findAllRoles();
        // 2.响应数据
        return Result.success(list);
    }




}
