package com.wh.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.eduservice.entity.EduTeacher;
import com.wh.edu.eduservice.entity.conditionQuery.teacherQuery;
import com.wh.edu.eduservice.mapper.EduTeacherMapper;
import com.wh.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-10
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {



    @Override
    public IPage<EduTeacher> selectByCondition(Page<EduTeacher> teacherPage, teacherQuery teacherquery) {

        if(teacherquery==null){
            IPage<EduTeacher> teacherIPage = baseMapper.selectPage(teacherPage, null);
            return teacherIPage;
        }

        String name = teacherquery.getName();
        String level = teacherquery.getLevel();
        String begin = teacherquery.getBegin();
        String end = teacherquery.getEnd();

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){

            queryWrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }

        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        IPage<EduTeacher> teacherIPage = baseMapper.selectPage(teacherPage, queryWrapper);

        return teacherIPage;
    }
}
