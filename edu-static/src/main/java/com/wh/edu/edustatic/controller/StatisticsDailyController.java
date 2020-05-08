package com.wh.edu.edustatic.controller;


import com.wh.edu.common.R;
import com.wh.edu.edustatic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/edustatic/statisticsDaily")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService statisticsDailyService;

    @GetMapping("/getRegisterCount/{day}")
    public R getCountRegister(@PathVariable String day){
        statisticsDailyService.getCountRegister(day);
        return R.ok();

    }


    //获取某段时间的注册或观看数据
    @GetMapping("/statics/{type}/{begin}/{end}")
    public R getStaticsData(@PathVariable String type,
                            @PathVariable String begin,
                            @PathVariable String end){

        Map<String,Object> map =statisticsDailyService.getStaticsData(type,begin,end);

        return R.ok().data("mapData",map);
    }
}

