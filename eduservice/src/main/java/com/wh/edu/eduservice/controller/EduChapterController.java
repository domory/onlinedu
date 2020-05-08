package com.wh.edu.eduservice.controller;


import com.wh.edu.common.R;
import com.wh.edu.eduservice.entity.EduChapter;
import com.wh.edu.eduservice.entity.dto.chapterDto;
import com.wh.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;


    //根据章节id删除章节 和小结信息
    @DeleteMapping("/deleteChapter/{id}")
    public R deleteChapterById(@PathVariable String id){
       boolean b= eduChapterService.deleteChapterById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //修改章节信息
    @PostMapping("/updateChapter")
    public R updateChapterInfo(@RequestBody EduChapter eduChapter){
        boolean b = eduChapterService.updateById(eduChapter);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }

    //根据章节iD查询章节信息
    @GetMapping("/getChapterInfo/{id}")
    public R getChapterInfoById(@PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return R.ok().data("item",eduChapter);
    }



    //  添加章节
    @PostMapping("/addChapter")
    public  R addChapter(@RequestBody EduChapter eduChapter){

        boolean save = eduChapterService.save(eduChapter);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //查询章节 小结列表
    @GetMapping("/chapterVedioList/{courseId}")
    public R getChapterVedioByCourseId(@PathVariable String courseId){

      List<chapterDto> list= eduChapterService.getChapterVedioByCourseId(courseId);
        return R.ok().data("items",list);
    }

}

