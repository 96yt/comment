package com.yt.comment.mapper;

import com.yt.comment.entity.SysParam;

/**
 * Description:系统参数（同步任务系统参数）
 *
 * @author:Tong
 */
public interface SysParamMapper {

    /**
     * 根据KEY获取对应的系统参数值
     * @param key
     * @return
     */
    SysParam selectByKey(String key);

    /**
     * 根据KEY修改对应的系统参数值
     * @param sysParam
     * @return
     */
    int updateByKey(SysParam sysParam);
}
