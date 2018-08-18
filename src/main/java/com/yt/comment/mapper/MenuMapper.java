package com.yt.comment.mapper;

import com.yt.comment.entity.Menu;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface MenuMapper {

    /**
     * 新增
     * @param menu
     * @return
     */
    int insert(Menu menu);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改
     * @param menu
     * @return
     */
    int update(Menu menu);

    /**
     * 修改指定菜单下所有子菜单的排序数字，修改为原排序数字+1
     * @param parentId
     * @return
     */
    int updateOrderNumByParentId(Long parentId);

    /**
     * 修改指定菜单下后面的兄弟节点（包括指定菜单本身）的排序数字，修改为原排序数字+1
     * @param menu
     * @return
     */
    int updateOrderNumByMenuInclude(Menu menu);

    /**
     * 修改指定菜单下后面的兄弟节点（不包括指定菜单本身）的排序数字，修改为原排序数字+1
     * @param menu
     * @return
     */
    int updateOrderNumByMenu(Menu menu);

    /**
     * 根据主键获取菜单实体
     * @param id
     * @return
     */
    Menu selectById(Long id);

    /**
     * 根据查询条件查询菜单列表（仅本菜单表查询）
     * @param menu
     * @return
     */
    List<Menu> select(Menu menu);

    /**
     *
     * @param menu
     * @return
     */
    List<Menu> selectZTree(Menu menu);

    /**
     * 根据查询条件查询菜单列表（关联动作表，结果集里包含了动作列表）
     * @param menu
     * @return
     */
    List<Menu> selectWithAction(Menu menu);
}
