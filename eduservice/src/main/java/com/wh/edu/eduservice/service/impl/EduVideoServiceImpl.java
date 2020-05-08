package com.wh.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.edu.eduservice.client.VedioClient;
import com.wh.edu.eduservice.entity.EduVideo;
import com.wh.edu.eduservice.mapper.EduVideoMapper;
import com.wh.edu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    VedioClient vedioClient;

    //根据课程id删除小节
    @Override
    public void deleteVedioBycourseId(String id) {
        //先查出对应的小结 再查出视频id
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        queryWrapper.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(queryWrapper);
        List<String> vedioIdList=new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            vedioIdList.add(videoSourceId);
        }
        //调用vedio服务
        vedioClient.deleteMoreVedio(vedioIdList);


        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        baseMapper.delete(videoQueryWrapper);
    }

    @Override
    public boolean removeVedioId(String id) {
        //TODO 需要删除视频
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vedioClient.deleteAliYun(videoSourceId);
        }

        baseMapper.deleteById(id);
        return true;
    }
}
