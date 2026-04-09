package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Dept;
import com.qk.mapper.DeptMapper;
import com.qk.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptMapper deptMapper;
    /*
    新增部门
     */
    @Override
    public void add(Dept dept) {
        //1.补全dept中的数据(id,可以不用,因为是自增的)
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper
        deptMapper.insert(dept);
    }
    /*
    查询部门
     */
    @Override
    public PageResult<Dept> findDept(String name, Integer status, Integer page, Integer pageSize) {
        //1.第三方插件辅助分页(会使得下一项查询的操作自动进行分页)
        PageHelper.startPage(page,pageSize);
        //2.调用mapper
        List<Dept> list = deptMapper.findDept(name,status);
        //3.解析结果
        Page<Dept> pageData = (Page<Dept>) list;
        //4.封装结果并返回
        return new PageResult<>(pageData.getTotal(),pageData.getResult());
    }
    /*
    修改部门:根据id查询部门先显示部门name和status
     */
    @Override
    public Dept findDeptById(Integer id) {
        //1.调用mapper
        return deptMapper.findDeptById(id);
    }
    /*
    修改部门
     */
    @Override
    public void updateDept(Dept dept) {
        //1.补全updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper
        deptMapper.updatedept(dept);
    }
    /*
    删除部门
     */
    @Override
    public void deleteDept(Integer id) {
        //1.调用mapper
        deptMapper.deleteDept(id);
    }
}
