package com.wh.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.edu.eduservice.eduConfig.EduException;
import com.wh.edu.eduservice.entity.EduChapter;
import com.wh.edu.eduservice.entity.EduVideo;
import com.wh.edu.eduservice.entity.dto.chapterDto;
import com.wh.edu.eduservice.mapper.EduChapterMapper;
import com.wh.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wh.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    //根据id删除章节信息
    @Override
    public boolean deleteChapterById(String id) {
        //判断章节下是否有小结
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",id);
        int count = eduVideoService.count(videoQueryWrapper);

        if(count>0){
            //有小结
            throw  new EduException(20001,"先删除该章节下的小结");
        }
        //没有小结
        int i = baseMapper.deleteById(id);

        return i>0;
    }

    //根据课程id删除课程章节
    @Override
    public void deleteChapterBycourseId(String id) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",id);
        baseMapper.delete(eduChapterQueryWrapper);
    }

    //根据课程id查询章节 小结列表
    @Override
    public List<chapterDto> getChapterVedioByCourseId(String courseId) {

        //查询章节列表
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterQueryWrapper);

        //查询小结列表
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoQueryWrapper);

        List<chapterDto> chapterDtos=new ArrayList<>();
        //将章节信息复制到章节dto中
        for (EduChapter eduChapter : eduChapters) {
            chapterDto chapterDto = new chapterDto();
            BeanUtils.copyProperties(eduChapter,chapterDto);

            //存储小结信息
            List<EduVideo> videos=new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                if(eduChapter.getId().equals(eduVideo.getChapterId())){
                    videos.add(eduVideo);
                }
            }
            //在章节dto中存储小结信息
            chapterDto.setChildren(videos);
            chapterDtos.add(chapterDto);
        }

        return chapterDtos;
    }


}
