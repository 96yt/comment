package com.yt.comment.service.impl;

import com.yt.comment.dto.echarts.Option;
import com.yt.comment.dto.echarts.Serie;
import com.yt.comment.mapper.ReportMapper;
import com.yt.comment.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class OrderReportServiceImpl implements OrderReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Option count() {
        Option option = new Option();
        //获取原料
        List<Map<String,String>> reportMaps = reportMapper.countOrder();
        //重新组装格式化原料，为后面拼装series做准备
        //类别+时间为key，数量为value 由类别+时间可以确定线上的唯一一个点
        Map<String,Long> countMap = new HashMap<>();
        //使用Set是为了legend的data不可重复，TreeSet是有一定的顺序
        //类别legend
        Set<String> legendSet = new TreeSet<>();
        for (Map<String,String> map : reportMaps) {
            //这步很重要，重新对数据库中的数据格式化一下，方便以后设置series中的data
            countMap.put(map.get("categoryName") + map.get("hour"),Long.valueOf(map.get("count")));
            legendSet.add(map.get("categoryName"));
        }
        //组装Option中的legend data
        option.getLegend().setData(new ArrayList<>(legendSet));
        //组装Option中的xAxis data
        List<String> hourList = new ArrayList<>();
        for (int i = 0 ; i <= 23 ; i ++) {
            hourList.add(String.format("02d",i));
        }
        option.getxAxis().setData(hourList);
        //组装Option中的series name data
        for (String categoryName : option.getLegend().getData()) {
            Serie serie = new Serie();
            option.getSeries().add(serie);
            serie.setName(categoryName);
            serie.setType(Serie.TYPE_NAME);
            for (String hourString : hourList) {
                Long hour = countMap.get(categoryName + hourString) == null ? 0L : countMap.get(categoryName + hourString);
                serie.getData().add(hour);
            }
        }
        return option;
    }
}
