package com.yt.comment.service;

import com.yt.comment.dto.GroupDto;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface GroupService {

    /**
     * 新增用户组
     * @param groupDto
     * @return
     */
    boolean add(GroupDto groupDto);

    /**
     * 删除用户组
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 修改用户组
     * @param groupDto
     * @return
     */
    boolean modify(GroupDto groupDto);

    /**
     * 根据主键获取用户组
     * @param id
     * @return
     */
    GroupDto getById(Long id);

    /**
     * 获取用户组列表
     * @return
     */
    List<GroupDto> getList();

    /**
     * 通过id获取菜单列表
     * @param id
     * @return
     */
    GroupDto getMenuList(Long id);

    /**
     * 为用户组分配可以访问的菜单
     * @param groupDto
     * @return
     */
    boolean assignMenu(GroupDto groupDto);

    /**
     * 根据主键获取用户组（包括用户组对应可以访问的菜单和动作）
     * @param id
     * @return
     */
    GroupDto getByIdWithMenuAction(Long id);
}
