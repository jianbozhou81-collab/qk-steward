package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Course;

import java.util.List;

public interface CourseService {
    /*
    课程列表查询
     */
    PageResult<Course> findall(String name, Integer subject, Integer target, Integer page, Integer pageSize);

    /*
    课程删除:根据id删除
     */
    void delete(Integer id);

    /*
    新增课程
     */
    void addCourse(Course course);
    /*
    修改课程:根据id查询课程先显示课程信息
     */
    Course findCourseById(Integer id);
    /*
    修改课程:修改回显的课程信息
     */
    void updateCourse(Course course);
    /*
    查询所有课程
     */
    List<Course> findAllCourse();
    /*
    根据学科查询课程
     */
    List<Course> findCourseBySubject(Integer subject);
}
