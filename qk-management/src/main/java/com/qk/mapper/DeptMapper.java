package com.qk.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    新增部门
     */
    @Insert("insert into dept(name,status,create_time,update_time) values(#{name},#{status},#{createTime},#{updateTime})")
    void insert(Dept dept);
    /*
    查询部门
     */
    List<Dept> findDept(String name, Integer status);
    /*
    修改部门:根据id查询部门先显示部门name和status
     */
    @Select("select id, name, status, create_time, update_time from dept where id=#{id}")
    Dept findDeptById(Integer id);
    /*
        修改部门:修改回显的部门信息以达成修改
         */
    void updatedept(Dept dept);
    /*
    删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void deleteDept(Integer id);
    @Select("select id, name, status, create_time, update_time from dept where status = 1")
    List<Dept> findAllNormalDept();

}
