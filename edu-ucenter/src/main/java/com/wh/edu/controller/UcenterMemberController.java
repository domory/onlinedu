package com.wh.edu.controller;


import com.wh.edu.common.R;
import com.wh.edu.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/eduucenter/ucentermember")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    UcenterMemberService ucenterMemberService;


    //查询某天注册人数
    @GetMapping("/registcount/{day}")
    public R getCountByDay(@PathVariable String day){
       int result= ucenterMemberService.getMemberCountByDay(day);
        return R.ok().data("count",result);
    }

}

