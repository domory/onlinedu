package com.wh.edu.eduservice.mapper;

import com.wh.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.edu.eduservice.entity.dto.courseInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wh
 * @since 2020-04-18
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    courseInfo getAllCourseInfoById(@Param("courseId") String id);
}
