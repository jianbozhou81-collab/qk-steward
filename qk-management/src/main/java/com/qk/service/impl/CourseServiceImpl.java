package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Course;
import com.qk.mapper.CourseMapper;
import com.qk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;
    /*
    课程列表查询
     */
    @Override
    public PageResult<Course> findall(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        //1.第三方插件封装分页数据
        PageHelper.startPage(page,pageSize);
        //2.调用mapper层
        List<Course> list = courseMapper.findall(name,subject,target);
        //3.解析 数据
        Page<Course> p = (Page<Course>) list;
        //4.响应数据
        return new PageResult<>(p.getTotal(),p.getResult());
    }
    /*
    删除课程
     */
    @Override
    public void delete(Integer id) {
        //1.调用mapper层
        courseMapper.delete(id);
    }
    /*
    新增课程
     */
    @Override
    public void addCourse(Course course) {
        //1.补全数据
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层
        courseMapper.addCourse(course);

    }
    /*
    根据id查询课程,回显信息
     */
    @Override
    public Course findCourseById(Integer id) {
        // 1.调用mapper层
        Course course = courseMapper.findCourseById(id);
        // 2.返回结果
        return course;
    }
    /*
    修改课程
     */
    @Override
    public void updateCourse(Course course) {
        //1.补全数据
        course.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层
        courseMapper.updateCourse(course);
    }
    /*
    查询所有课程
     */
    @Override
    public List<Course> findAllCourse() {
        // 1.调用mapper层
        List<Course> list = courseMapper.findAllCourse();
        return list;
    }
    /*
    根据学科查询课程
     */
    @Override
    public List<Course> findCourseBySubject(Integer subject) {
        List<Course> list = courseMapper.findCourseBySubject(subject);
        return list;
    }
}
