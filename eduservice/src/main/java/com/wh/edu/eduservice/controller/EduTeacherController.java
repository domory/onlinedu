package com.wh.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.edu.common.R;
import com.wh.edu.eduservice.entity.EduTeacher;
import com.wh.edu.eduservice.entity.conditionQuery.teacherQuery;
import com.wh.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    EduTeacherService eduTeacherService;


    //模拟登录
    @PostMapping("/login")
    public  R login(){
       return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","管理员").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //根据条件查询并分页
    @PostMapping("/condition/{page}/{size}")
    public R selectTeacerByCondition(@PathVariable Integer page,
                                     @PathVariable Integer size,
                                     @RequestBody(required = false) teacherQuery teacherquery ){

        Page<EduTeacher> teacherPage = new Page<>(page,size);


        IPage<EduTeacher> page1 = eduTeacherService.selectByCondition(teacherPage,teacherquery);
        long total = page1.getTotal();
        List<EduTeacher> records = page1.getRecords();
        return R.ok().data("total",total).data("items",records);
    }



    //分页查询老师
    @GetMapping("/pageList/{page}/{size}")
    public R selectByPage(@PathVariable Integer page,@PathVariable Integer size){
        //总页数+总记录数
        Page<EduTeacher> teacherPage = new Page<>(page,size);

        //条件
      //  QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
     //   queryWrapper.gt("gmt_create","2019-02-22 23:35:00");  //需要和数据库的字段一致
        //查询
        IPage<EduTeacher> page1 = eduTeacherService.page(teacherPage, null);

        List<EduTeacher> records = page1.getRecords();
        long total = page1.getTotal();
        return R.ok().data("total",total).data("itmes",records);
    }


    //查询全部老师
    @GetMapping("/findAllTeacher")
    public R selectTeachersByCondition(){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        List<EduTeacher>  eduTeachers=eduTeacherService.list(null);
        return R.ok().data("item",eduTeachers);
    }

    //根据id查询老师
    @GetMapping("/getTeacher/{id}")
    public  R selectTeacherById(@PathVariable String id){
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);

        EduTeacher eduTeacher = eduTeacherService.getOne(queryWrapper);
      //  EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("item",eduTeacher);
    }


    //添加老师 从表单中插入
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save==true){
        return R.ok();}
        else {
            return R.error();
        }
    }

    //删除老师
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b==true){
            return  R.ok();
        }else {
            return R.error();
        }

    }

    //修改教师信息
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b==true){
            return  R.ok();
        }else {
            return R.error();
        }
    }
}

