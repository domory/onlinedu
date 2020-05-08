package com.wh.edu.vedio.controller;


import com.wh.edu.common.R;
import com.wh.edu.vedio.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vedioservice")
@CrossOrigin
public class vedioController {

    @Autowired
    VideoService videoService;

    //删除多个阿里云视频
    @DeleteMapping("/deleteMoreVedio")
    public R deleteMoreVedio(@RequestParam("vedioList")List vediolist){

        boolean flag= videoService.deleteMoreVedio(vediolist);
        if(flag){
        return R.ok();}
        else {
            return R.error();
        }
    }


    @PostMapping("/add")
    public  R add(){
        return R.ok();
    }

    //根据视频源id删除阿里云视频
    @DeleteMapping("/deleteVedio/{vedioId}")
    public R deleteVedio(@PathVariable String vedioId ){
       boolean b= videoService.deleteVedioById(vedioId);
        if(b){
        return R.ok();}
        else {
            return R.error();
        }
    }


    //上传阿里云视频
    @PostMapping("/uploadvedio")
    public R uploadvedio(@RequestParam("file")MultipartFile file){
       String vedioId= videoService.uploadvedio(file);
        return R.ok().data("vedioId",vedioId);

    }


}
