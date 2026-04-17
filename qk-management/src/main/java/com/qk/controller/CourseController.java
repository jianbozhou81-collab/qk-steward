package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;
import com.qk.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    /*
    课程列表查询
     */
    @GetMapping
    public Result findall(String name, Integer subject, Integer target, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("接收到数据name={},subject={},target={},page={},pageSize={}",name,subject,target,page,pageSize);
        // 1.调用service层
        PageResult<Course> p = courseService.findall(name,subject,target,page,pageSize);
        // 2.响应数据
        return Result.success(p);
    }
    /*
    课程删除:根据id删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 1.调用service层
        courseService.delete(id);
        // 2.响应数据
        return Result.success();
    }
    /*
    新增课程
     */
    @PostMapping
    public Result addCourse(@RequestBody Course course){
        // 1.调用service层
        courseService.addCourse(course);
        // 2.响应数据
        return Result.success();
    }
    /*
    修改课程:根据id查询课程先显示课程
     */
    @GetMapping("/{id}")
    public Result findCourseById(@PathVariable Integer id){
        // 1.调用service层
        Course course = courseService.findCourseById(id);
        // 2.响应数据
        return Result.success(course);
    }
    /*
    修改课程
     */
    @PutMapping
    public Result updateCourse(@RequestBody Course course){
        // 1.调用service层
        courseService.updateCourse(course);
        // 2.响应数据
        return Result.success();
    }
    /*
    查询所有课程
     */
    @GetMapping("/list")
    public Result findAllCourse(){
        // 1.调用service层
        List<Course> list = courseService.findAllCourse();
        // 2.响应数据
        return Result.success(list);
    }
    /*
    根据学科查询课程
     */
    @GetMapping("/subject/{subject}")
    public Result findCourseBySubject(@PathVariable Integer subject){
        // 1.调用service层
        List<Course> list = courseService.findCourseBySubject(subject);
        // 2.响应数据
        return Result.success(list);
    }

}
