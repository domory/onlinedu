package com.wh.edu.eduservice.service.impl;

import com.wh.edu.eduservice.entity.EduCourseDescription;
import com.wh.edu.eduservice.mapper.EduCourseDescriptionMapper;
import com.wh.edu.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {




    //根据课程id删除课程描述
    @Override
    public void deleteDescriptionBycourseId(String id) {
        baseMapper.deleteById(id);
    }
}
