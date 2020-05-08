package com.wh.edu.eduservice.service;

import com.wh.edu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    //根据课程id删除课程描述
    void deleteDescriptionBycourseId(String id);
}
