package com.wh.edu.eduservice.service;

import com.wh.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
public interface EduVideoService extends IService<EduVideo> {


    //根据课程id删除小节
    void deleteVedioBycourseId(String id);

    boolean removeVedioId(String id);
}
