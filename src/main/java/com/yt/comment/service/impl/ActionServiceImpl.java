package com.yt.comment.service.impl;

import com.yt.comment.dto.ActionDto;
import com.yt.comment.entity.Action;
import com.yt.comment.mapper.ActionMapper;
import com.yt.comment.service.ActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionMapper actionMapper;

    @Override
    public boolean add(ActionDto dto) {
        return actionMapper.insert(dto) == 1;
    }

    @Override
    public boolean remove(Long id) {
        return actionMapper.deleteById(id) == 1;
    }

    @Override
    public boolean modify(ActionDto dto) {
        Action action = new Action();
        BeanUtils.copyProperties(dto,action);
        return actionMapper.update(action) == 1;
    }

    @Override
    public ActionDto getById(Long id) {
        ActionDto result = new ActionDto();
        Action action = actionMapper.selectById(id);
        BeanUtils.copyProperties(action,result);
        return result;
    }
}
