package com.yt.comment.task;

import com.yt.comment.constant.SysParamKeyConst;
import com.yt.comment.entity.SysParam;
import com.yt.comment.mapper.BusinessMapper;
import com.yt.comment.mapper.SysParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:商户相关的的定时任务
 *
 * @author:Tong
 */
@Component("BusinessTask")
public class BusinessTask {

    private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private SysParamMapper sysParamMapper;

    /**
     * 同步商户总销量和总价格
     */
    @Transactional
    public void synNumberAndPrice() {

        logger.info("同步商户总价格和总销量开始");
        //获取系统最后一步统计时间
        SysParam sysParam = sysParamMapper.selectByKey(SysParamKeyConst.LAST_SYNC_NUMBER_TIME);
        Map<String,Date> timestamp = new HashMap<>();
        Date startTime = sysParam.getParamValue();
        //确定本次统计的最后时间
        Date endTime = new Date();
        timestamp.put("startTime",startTime);
        timestamp.put("endTime",endTime);
        //进行商户总价格和销量统计
        businessMapper.updateNumberAndPrice(timestamp);
        //更新系统最新统计时间
        SysParam sysParamForUpdate = new SysParam();
        sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_NUMBER_TIME);
        sysParamForUpdate.setParamValue(endTime);
        sysParamMapper.updateByKey(sysParamForUpdate);
        logger.info("同步商户总价格和总销量结束");
    }

    /**
     * 同步商户总星数和总评论数
     */
    @Transactional
    public void synStarAndComment() {

        logger.info("同步商户总星数和总评论数开始");
        //先获取上次同步的时间（最后同步时间）
        SysParam sysParam = sysParamMapper.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        Map<String,Date> timestampMap = new HashMap<>();
        Date startTime = sysParam.getParamValue();
        //插入时间标杆
        //以当前系统做为同步的截止时间，同时也作为下次同步的起始时间
        Date endTime = new Date();
        timestampMap.put("startTime",startTime);
        timestampMap.put("endTime",endTime);
        //按这样起始时间-结束时间同步；商户对应的【星星总数】、【评论总次数】
        //如果起始时间为NULL，那说明是第一次同步，需要历史数据全部同步，一直同步到当前系统时间为止
        businessMapper.updateStarAndComment(timestampMap);
        //将当前时间这个系统时间更新到系统参数表中，做为下次同步的起始时间
        SysParam sysParamForUpdate = new SysParam();
        sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        sysParamForUpdate.setParamValue(endTime);
        sysParamMapper.updateByKey(sysParamForUpdate);
        logger.info("同步商户总星数和总评论数结束");

    }
}
