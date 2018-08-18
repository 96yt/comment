package com.yt.comment.service.impl;

import com.yt.comment.entity.Dic;
import com.yt.comment.mapper.DicMapper;
import com.yt.comment.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicMapper dicMapper;

    @Override
    public List<Dic> getListByType(String type) {
        Dic dic = new Dic();
        dic.setType(type);
        return dicMapper.select(dic);
    }
}
