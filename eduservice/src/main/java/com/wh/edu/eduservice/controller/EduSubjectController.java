package com.wh.edu.eduservice.controller;


import com.wh.edu.common.R;
import com.wh.edu.eduservice.entity.EduSubject;
import com.wh.edu.eduservice.entity.dto.SubjectNestedVo;
import com.wh.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {


    @Autowired
    EduSubjectService subjectService;

    //添加二级分类
    @PostMapping("/addTwoNode")
    public R  addTwoNode(@RequestBody EduSubject eduSubject){
        boolean b = subjectService.addTwoNode(eduSubject);
        if(b){
            return  R.ok();
        }else{
            return R.error();
        }
    }


    //添加一级分类
    @PostMapping("/addOneNode")
    public R addOneNode(@RequestBody EduSubject eduSubject){
        boolean b = subjectService.addOneNode(eduSubject);
        if(b){
            return  R.ok();
        }else{
            return R.error().message("保存失败");
        }
    }


    //删除分类
    @DeleteMapping("/delete/{id}")
    public R deleteOneLevel(@PathVariable String id){
        boolean b = subjectService.deleteNode(id);
        if(b){
        return R.ok();
        }
        else {
            return R.error();
        }
    }

    //上传excel表格
    @PostMapping("/upload")
    public R uploadFile(@RequestBody MultipartFile file){

        List<String> msg = subjectService.uploadFile(file);
        if(msg.size()==0){
            return R.ok();
        }else {
            return R.error().data("msg",msg);
        }


    }


    //获得一级和对应的二级分类
    @GetMapping("")
    public R nestedList(){
        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();

        return R.ok().data("items", subjectNestedVoList);
    }

}

