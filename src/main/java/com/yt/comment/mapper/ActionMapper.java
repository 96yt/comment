package com.yt.comment.mapper;

import com.yt.comment.entity.Action;

/**
 * Description:
 *
 * @author:Tong
 */
public interface ActionMapper {

    /**
     * 根据菜单id删除动作
     * @param menuId 菜单Id
     * @return 影响行数
     */
    int deleteByMenuId(Long menuId);

    /**
     * 根据主键删除动作
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 新增
     * @param action
     * @return
     */
    int insert(Action action);

    /**
     * 修改
     * @param action
     * @return
     */
    int update(Action action);

    /**
     * 根据主键获取动作实体
     * @param id
     * @return
     */
    Action selectById(Long id);
}
