package com.wh.edu.eduservice.service;

import com.wh.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.edu.eduservice.entity.dto.chapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
public interface EduChapterService extends IService<EduChapter> {


    //根据课程id删除课程章节
    void deleteChapterBycourseId(String id);

    //查询章节 小结列表
    List<chapterDto> getChapterVedioByCourseId(String courseId);

    //根据id删除章节信息
    boolean deleteChapterById(String id);
}
