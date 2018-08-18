package com.yt.comment.mapper;

import com.yt.comment.entity.Group;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface GroupMapper {

    /**
     * 新增
     * @param group
     * @return
     */
    int insert(Group group);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改
     * @param group
     * @return
     */
    int update(Group group);

    /**
     * 根据主键获取用户组实体
     * @param id
     * @return
     */
    Group selectById(Long id);

    /**
     * 根据查询条件查询用户组列表
     * @param group
     * @return
     */
    List<Group> select(Group group);

    /**
     * 根据主键获取用户对应的菜单列表
     * @param id
     * @return
     */
    Group selectMenuListById(Long id);
}
