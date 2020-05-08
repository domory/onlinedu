package com.wh.edu.eduservice.controller;


import com.wh.edu.common.R;
import com.wh.edu.eduservice.client.VedioClient;
import com.wh.edu.eduservice.entity.EduVideo;
import com.wh.edu.eduservice.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService eduVideoService;


    //根据小结id进行查询信息
    @GetMapping("/getVedioInfoById/{id}")
    public R getVedioInfoById(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        return R.ok().data("item",eduVideo);
    }

    //根据id删除小结
    @DeleteMapping("/deleteVedio/{id}")
    public R deleteVedioById(@PathVariable String id){
        boolean b = eduVideoService.removeVedioId(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //修改小结
    @PostMapping("/updateVedio")
    public R updateVedio(@RequestBody EduVideo eduVideo){
        boolean b = eduVideoService.updateById(eduVideo);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }


    //添加小结
    @PostMapping("/addVedio")
    public R addVedio(@RequestBody EduVideo eduVideo){
        boolean b = eduVideoService.save(eduVideo);
        if(b){
            return R.ok();
        }else {
        return R.error();}
    }
}

