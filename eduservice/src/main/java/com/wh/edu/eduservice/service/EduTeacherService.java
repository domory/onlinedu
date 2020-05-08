package com.wh.edu.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.edu.eduservice.entity.conditionQuery.teacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-10
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> selectByCondition(Page<EduTeacher> teacherPage, teacherQuery teacherquery);
}
