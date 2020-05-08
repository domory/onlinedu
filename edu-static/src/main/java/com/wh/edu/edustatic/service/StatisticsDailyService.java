package com.wh.edu.edustatic.service;

import com.wh.edu.edustatic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void getCountRegister(String day);


    Map<String,Object> getStaticsData(String type, String begin, String end);
}
