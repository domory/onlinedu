package com.wh.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.eduservice.eduConfig.EduException;
import com.wh.edu.eduservice.entity.*;
import com.wh.edu.eduservice.entity.conditionQuery.courseQuery;
import com.wh.edu.eduservice.entity.dto.courseInfo;
import com.wh.edu.eduservice.mapper.EduCourseMapper;
import com.wh.edu.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduTeacherService eduTeacherService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;


    @Override
    public courseInfo getAllCourseInfoById(String id) {
        courseInfo course =baseMapper.getAllCourseInfoById(id);
        return course;
    }



    //删除课程信息
    @Override
    public boolean deleteCourseInfo(String id) {
        //删除课程小结
        eduVideoService.deleteVedioBycourseId(id);

        //删除课程章节
        eduChapterService.deleteChapterBycourseId(id);

        //删除课程描述
         eduCourseDescriptionService.deleteDescriptionBycourseId(id);

        //删除课程信息
        int i = baseMapper.deleteById(id);
        if (i<=0){
            return false;
        }
        return true;
    }




    //条件查询课程信息
    @Override
    public IPage<EduCourse> getAllCourseInfo(Page<EduCourse> eduCoursePage, courseQuery coursequery) {
        if(coursequery == null){
            IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(eduCoursePage, null);
            return eduCourseIPage;
        }
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(coursequery.getTitle())){
            queryWrapper.like("title",coursequery.getTitle());
        }
        if(coursequery.getPrice() != null){
            queryWrapper.eq("price",coursequery.getPrice());
        }

        if(!StringUtils.isEmpty(coursequery.getName())){
            //根据教师名称查询对应的id
            QueryWrapper<EduTeacher> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("name",coursequery.getName());
            EduTeacher eduTeacher = eduTeacherService.getOne(queryWrapper1);
            if(eduTeacher!=null){
            queryWrapper.eq("teacher_id",eduTeacher.getId());}
            else {
                throw  new EduException(20001,"请输入教师名字全称");
            }
        }
        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(eduCoursePage, queryWrapper);

        return eduCourseIPage;
    }



    //根据课程id修改课程基础信息
    @Override
    public Boolean updateCourseInfoById(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
        return b;
    }




    //根据课程id查询课程信息
    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //查询课程基本信息
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null){
            throw  new EduException(20001,"课程信息不存在");
        }
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());
        return courseInfoForm;
    }




    //添加课程基本信息 涉及课程表和课程描述表
    @Override
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert ==0){
            throw  new EduException(20001,"课程信息添加失败！");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        String courseId=eduCourse.getId();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseId);
        boolean b = eduCourseDescriptionService.save(eduCourseDescription);
        if(b){
            return courseId ;
        }
        else {
            return null;
        }
    }


}
