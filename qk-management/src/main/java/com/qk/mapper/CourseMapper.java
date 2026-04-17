package com.qk.mapper;

import com.qk.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    /*
    课程列表查询
     */
    List<Course> findall(String name, Integer subject, Integer target);
    /*
    课程删除:根据id删除
     */
    @Delete("delete from course where id = #{id}")
    void delete(Integer id);
    /*
    新增课程
     */
    @Insert("insert into course(name,subject,price,target,description,create_time,update_time) values(#{name},#{subject},#{price},#{target},#{description},#{createTime},#{updateTime})")
    void addCourse(Course course);
    /*
    修改课程:修改回显的课程信息
     */
    @Select("select id, name, subject, price, target, description, create_time, update_time from course where id = #{id}")
    Course findCourseById(Integer id);
    /*
    修改课程:修改回显的课程信息以达成修改(根据页面显示消息,大部分东西都是必填的,所以不用动态填写就行了)
     */
    @Update("update course set name = #{name},subject = #{subject},price = #{price},target = #{target},description = #{description},update_time = #{updateTime} where id = #{id}")
    void updateCourse(Course course);
    /*
    查询所有课程
     */
    @Select("select id, name, subject, price, target, description, create_time, update_time from course")
    List<Course> findAllCourse();
    /*
    根据学科查询课程
     */
    @Select("select id, name, subject, price, target, description, create_time, update_time from course where subject = #{subject}")
    List<Course> findCourseBySubject(Integer subject);
}
