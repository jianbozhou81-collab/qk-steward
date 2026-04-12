package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

import java.util.List;


public interface DeptService {
    /*
    新增部门
     */
    void add(Dept dept);//前面省略了public abstract

    /*
    查询部门
     */
    PageResult<Dept> findDept(String name, Integer status, Integer page, Integer pageSize);
    /*
    修改部门:根据id查询部门先显示部门name和status
     */
    Dept findDeptById(Integer id);
    /*
    修改部门:修改回显的部门信息以达成修改
     */
    void updateDept(Dept dept);
    /*
    删除部门
     */
    void deleteDept(Integer id);
    /*
       查询所有所有的正常部门的数据
        */
    List<Dept> findAllNormalDept();

}
