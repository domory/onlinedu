package com.wh.edu.edustatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wh.edu.common.R;
import com.wh.edu.edustatic.client.Ucenter;
import com.wh.edu.edustatic.entity.StatisticsDaily;
import com.wh.edu.edustatic.mapper.StatisticsDailyMapper;
import com.wh.edu.edustatic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author wh
 * @since 2020-04-28
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    Ucenter ucenter;

    @Override
    public void getCountRegister(String day) {
        R r=ucenter.getRegisterCount(day);
        Integer count = (Integer) r.getData().get("count");

        //判断之前是否已经存在数据 如果存在直接删除
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
         baseMapper.delete(queryWrapper);


        int courseNum = RandomUtils.nextInt(10, 200);
        int loginNum = RandomUtils.nextInt(10, 200);
        int videoViewNum = RandomUtils.nextInt(10, 200);

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setCourseNum(courseNum);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setLoginNum(loginNum);
        statisticsDaily.setRegisterNum(count);
        statisticsDaily.setVideoViewNum(videoViewNum);
        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getStaticsData(String type, String begin, String end) {
        //搜索某段时间的数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("date_calculated",begin);
        queryWrapper.le("date_calculated",end);
        queryWrapper.select("date_calculated",type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(queryWrapper);

        //将数据转换成
        // 日期['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'] 数据[820, 932, 901, 934, 1290, 1330, 1320]

        //定义数据和日期数组
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> num = new ArrayList<>();

        for (StatisticsDaily statisticsDaily : statisticsDailies) {
                date.add(statisticsDaily.getDateCalculated());

                //判断选择的type的类型
                if(type.equals("login_num")){
                    num.add(statisticsDaily.getLoginNum());
                }else if(type.equals("register_num")){
                    num.add(statisticsDaily.getRegisterNum());
                }else if(type.equals("video_view_num")){
                    num.add(statisticsDaily.getVideoViewNum());
                }else if(type.equals("course_num")){
                    num.add(statisticsDaily.getCourseNum());
                }

        }

        Map<String, Object> map = new HashMap<>();
        map.put("date",date);
        map.put("data",num);

        return map;
    }
}
