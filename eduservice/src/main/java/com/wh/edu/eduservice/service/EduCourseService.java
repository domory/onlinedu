package com.wh.edu.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.eduservice.entity.CourseInfoForm;
import com.wh.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.edu.eduservice.entity.conditionQuery.courseQuery;
import com.wh.edu.eduservice.entity.dto.courseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    Boolean updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<EduCourse> getAllCourseInfo(Page<EduCourse> eduCoursePage, courseQuery courseInfoForm);

    boolean deleteCourseInfo(String id);

    courseInfo getAllCourseInfoById(String id);
}
