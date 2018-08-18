package com.yt.comment.mapper;

import com.yt.comment.entity.GroupMenu;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface GroupMenuMapper {

    /**
     * 根据用户组主键，删除用户组与菜单之间的关联关系
     * @param groupId
     * @return
     */
    int deleteByGroupId(Long groupId);

    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<GroupMenu> list);
}
