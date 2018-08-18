package com.yt.comment.service;

import com.yt.comment.dto.ActionDto;

/**
 * Description:
 *
 * @author:Tong
 */
public interface ActionService {

    /**
     * 新增动作
     * @param dto
     * @return
     */
    boolean add(ActionDto dto);

    /**
     * 删除动作
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 修改动作
     * @param dto
     * @return
     */
    boolean modify(ActionDto dto);

    /**
     * 根据id获取动作
     * @param id
     * @return
     */
    ActionDto getById(Long id);
}
