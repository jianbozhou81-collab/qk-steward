package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;


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

}
