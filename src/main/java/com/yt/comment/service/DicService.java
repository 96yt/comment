package com.yt.comment.service;

import com.yt.comment.entity.Dic;

import java.util.List;

/**
 * Description:提供字典服务提供列表
 *
 * @author:Tong
 */
public interface DicService {

    /**
     * 根据类型获取字典表列表
     * @param type
     * @return
     */
    List<Dic> getListByType(String type);
}
