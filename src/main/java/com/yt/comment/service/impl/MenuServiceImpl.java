package com.yt.comment.service.impl;

import com.yt.comment.dto.MenuDto;
import com.yt.comment.dto.MenuForMoveDto;
import com.yt.comment.dto.MenuForZtreeDto;
import com.yt.comment.entity.Action;
import com.yt.comment.entity.Menu;
import com.yt.comment.mapper.ActionMapper;
import com.yt.comment.mapper.MenuMapper;
import com.yt.comment.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ActionMapper actionMapper;

    @Override
    public boolean add(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        return menuMapper.insert(menu) == 1;
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        actionMapper.deleteById(id);
        return menuMapper.delete(id) == 1;
    }

    @Override
    public boolean modify(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        return menuMapper.update(menu) == 1;
    }

    @Override
    public MenuDto getById(Long id) {
        MenuDto result = new MenuDto();
        Menu menu = menuMapper.selectById(id);
        BeanUtils.copyProperties(menu,result);
        return result;
    }

    @Override
    public List<MenuDto> getList(MenuDto menuDto) {
        List<MenuDto> result = new ArrayList<>();
        Menu menuForSelect = new Menu();
        BeanUtils.copyProperties(menuDto,menuForSelect);
        List<Menu> menuList = menuMapper.select(menuForSelect);
        for (Menu menu : menuList) {
            MenuDto menuDtoTemp = new MenuDto();
            result.add(menuDtoTemp);
            BeanUtils.copyProperties(menu,menuDtoTemp);
        }
        return result;
    }

    @Override
    public List<MenuForZtreeDto> getZtreeList() {
        List<MenuForZtreeDto> result = new ArrayList<>();
        Menu menuForSelect = new Menu();
        List<Menu> menuList = menuMapper.selectWithAction(menuForSelect);

        for (Menu menu : menuList) {
            MenuForZtreeDto menuForZtreeDto = new MenuForZtreeDto();
            result.add(menuForZtreeDto);
            BeanUtils.copyProperties(menu,menuForZtreeDto);
            menuForZtreeDto.setOpen(false);
            menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU + menu.getId());
            menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + menu.getParentId());
            List<Action> actionList = menu.getActionList();
            for (Action action : actionList) {
                menuForZtreeDto = new MenuForZtreeDto();
                result.add(menuForZtreeDto);
                menuForZtreeDto.setId(action.getId());
                menuForZtreeDto.setParentId(action.getMenuId());
                menuForZtreeDto.setName(action.getName());
                menuForZtreeDto.setOpen(false);
                menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION + action.getId());
                menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + action.getMenuId());
            }
        }
        return result;
    }

    @Override
    @Transactional
    public boolean order(MenuForMoveDto menuForMoveDto) {

        //如果移动到目标节点，成为目标节点的子节点
        if (MenuForMoveDto.MOVE_TYPE_INNER.equals(menuForMoveDto.getMoveType())) {
            //先将目标节点下所有的子节点排序数字+1（让出最前面的位置）
            menuMapper.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
            //将移动到节点排序数字改为1，成为目标节点下最前面的子节点
            //无论是多么复杂的逻辑，这就是最终目的，修改菜单表中的parent_id和order_num
            Menu menuForUpdate = new Menu();
            menuForUpdate.setId(menuForMoveDto.getDropNodeId());
            menuForUpdate.setParentId(menuForMoveDto.getTargetNodeId());
            menuForUpdate.setOrderNum(1);
            menuMapper.update(menuForUpdate);
        } else {
            //获取目标节点的排序数字
            Menu targetMenu = menuMapper.selectById(menuForMoveDto.getTargetNodeId());
            if (targetMenu != null) {
                //如果移动到目标节点的前一个节点
                if (MenuForMoveDto.MOVE_TYPE_PREV.equals(menuForMoveDto.getMoveType())) {
                    //将目标节点的目标节点后面的兄弟节点的排序数字+1
                    //留出一个空位
                    Menu menuForUpdateOrder = new Menu();
                    menuForUpdateOrder.setParentId(targetMenu.getParentId());
                    menuForUpdateOrder.setOrderNum(targetMenu.getOrderNum());
                    menuMapper.updateOrderNumByMenuInclude(menuForUpdateOrder);
                    //将移动的节点的排序数字更新为目标节点的原排序数字(排序到目标节点的前一个节点位置）
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(targetMenu.getParentId());
                    menuForUpdate.setOrderNum(targetMenu.getOrderNum());
                    menuMapper.update(menuForUpdate);
                } else if(MenuForMoveDto.MOVE_TYPE_NEXT.equals(menuForMoveDto.getMoveType())) {
                    //如果移动到目标节点的后一个节点
                    //将目标节点后面的兄弟节点的排序数字+1（留出一个空位，也就是原本目标节点后面一个节点的位置）
                    Menu menuForUpdateOrder = new Menu();
                    menuForUpdateOrder.setParentId(targetMenu.getParentId());
                    menuForUpdateOrder.setOrderNum(targetMenu.getOrderNum());
                    menuMapper.updateOrderNumByMenu(menuForUpdateOrder);
                    //将移动的节点的排序数字更新为目标节点的原排序数字+1（拍到目标节点的后一个节点位置）
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(targetMenu.getParentId());
                    menuForUpdate.setOrderNum(targetMenu.getOrderNum() + 1);
                    menuMapper.update(menuForUpdate);
                } else {
                    return false;//移动方式不可识别
                }
            } else {
                return false;//目标节点已不存在
            }
        }
        return true;
    }
}
