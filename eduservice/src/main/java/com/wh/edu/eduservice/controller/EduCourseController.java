package com.wh.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.common.R;
import com.wh.edu.eduservice.entity.CourseInfoForm;
import com.wh.edu.eduservice.entity.EduCourse;
import com.wh.edu.eduservice.entity.EduCourseDescription;
import com.wh.edu.eduservice.entity.EduTeacher;
import com.wh.edu.eduservice.entity.conditionQuery.courseQuery;
import com.wh.edu.eduservice.entity.dto.courseInfo;
import com.wh.edu.eduservice.service.EduCourseDescriptionService;
import com.wh.edu.eduservice.service.EduCourseService;
import com.wh.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/eduservice/eduCourse")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduCourseDescriptionService courseDescriptionService;
    @Autowired
    EduTeacherService eduTeacherService;

    //发布课程
    @PostMapping("/updateCourseStatus/{id}")
    public R updateCourseStatus(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Nomal");
        boolean b = eduCourseService.updateById(eduCourse);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }


    //根据课程id获得所有课程相关信息 包括讲师 分类
    @GetMapping("/getAllCourseInfoById/{id}")
    public R geAllCourseInfoById(@PathVariable String id){
       courseInfo course=eduCourseService.getAllCourseInfoById(id);
        return R.ok().data("item",course);
    }


    //删除课程基本信息
    @DeleteMapping("/deleteCourseInfo/{id}")
    public R deleteCourseInfo(@PathVariable String id){

       boolean flag = eduCourseService.deleteCourseInfo(id);

       if(flag){
        return R.ok();
       }
        else {
           return R.error();
       }
    }


    //分页查询课程信息
    @PostMapping("/courseList/{currentPage}/{size}")
    public R getAllCourseInfo(@PathVariable Integer currentPage,
                              @PathVariable Integer size,
                              @RequestBody(required = false)courseQuery coursequery){
        Page<CourseInfoForm> courseInfoFormPage= new Page<>(currentPage,size);


        Page<EduCourse> eduCoursePage= new Page<>(currentPage,size);
        IPage<EduCourse> eduCourseIPage =eduCourseService.getAllCourseInfo(eduCoursePage,coursequery);
        List<EduCourse> records = eduCourseIPage.getRecords();
        long total = eduCourseIPage.getTotal();

        //以CourseInfoForm的形式显示
        ArrayList<CourseInfoForm> courseInfoForms = new ArrayList<>();

        for (EduCourse record : records) {
            CourseInfoForm courseInfoForm = new CourseInfoForm();
            BeanUtils.copyProperties(record,courseInfoForm);
            //拿到课程描述
            EduCourseDescription courseDescription = courseDescriptionService.getById(record.getId());
            courseInfoForm.setDescription(courseDescription.getDescription());
            //添加讲师名称
            EduTeacher eduTeacher = eduTeacherService.getById(record.getTeacherId());
            courseInfoForm.setTeacherName(eduTeacher.getName());
            courseInfoForms.add(courseInfoForm);
            courseInfoFormPage.setRecords(courseInfoForms);
        }

        courseInfoFormPage.setTotal(eduCourseIPage.getTotal());

        return R.ok().data("total",total).data("items",courseInfoFormPage.getRecords());
    }


    //根据课程id修改课程基础信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfoById(@RequestBody CourseInfoForm courseInfoForm){
        Boolean b =eduCourseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    //根据课程id查询课程信息
    @GetMapping("/getCourseInfo/{id}")
    public R getCourseInfoById(@PathVariable String id){

        CourseInfoForm courseInfoForm =eduCourseService.getCourseInfoById(id);
        return R.ok().data("courseInfo",courseInfoForm);
    }


    //添加课程的基本信息
    @PostMapping("/addCourseInfo")
    private R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String  courseId =eduCourseService.addCourseInfo(courseInfoForm);
            return  R.ok().data("courseId",courseId);

    }
}

