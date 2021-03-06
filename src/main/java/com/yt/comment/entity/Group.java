package com.yt.comment.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Description:用户组
 *
 * @author:Tong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {

    private Long id;
    private String name;
    private List<Menu> menuList;
    private List<Action> actionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }
}
