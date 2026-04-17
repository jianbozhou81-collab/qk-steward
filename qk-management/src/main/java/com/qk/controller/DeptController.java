package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    /*
    新增部门
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        //日志
        log.info("新增部门:{}",dept);
        //1.调用service层
        deptService.add(dept);
        //2.响应回去数据
        return Result.success();
    }
    /*
    查询部门
     */
    @GetMapping
    public Result findDept(String name, Integer status, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        //日志
        log.info("查询部门:{},{},{},{}",name,status,page,pageSize);
        //1.调用service层
        PageResult<Dept> p = deptService.findDept(name,status,page,pageSize);
        //2.响应数据
        return Result.success(p);
    }
    /*
    修改部门:根据id查询部门先显示部门name和status
     */
    @GetMapping("/{id}")
    public Result findDeptById(@PathVariable Integer id){
        //日志
        log.info("修改部门:{}",id);
        //1.调用service层
        Dept deptById = deptService.findDeptById(id);
        //2.响应数据
        return Result.success(deptById);
    }
    /*
    修改部门:修改回显的部门信息以达成修改
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        //日志
        log.info("修改部门:{}",dept);
        //1.调用service层
        deptService.updateDept(dept);
        //2.响应数据
        return Result.success();
    }
    /*
    删除部门
     */
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id){
        //日志
        log.info("删除部门:{}",id);
        //1.调用service层
        deptService.deleteDept(id);
        //2.响应数据
        return Result.success();
    }
    /*
    查询所有所有的正常部门的数据(服务于新增用户的操作)
     */
    @GetMapping("/list")
    public Result findAllNormalDept(){
        //1.调用service层
        List<Dept> list = deptService.findAllNormalDept();
        //2.响应数据
        return Result.success(list);
    }
}
