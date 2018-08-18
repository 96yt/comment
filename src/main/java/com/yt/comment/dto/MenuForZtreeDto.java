package com.yt.comment.dto;

/**
 * Description:与zTree上节点对应的菜单Dto
 *
 * @author:Tong
 */
public class MenuForZtreeDto {

    private Long id;//这个节点在数据库里的主键值
    private Long parentId;//这个节点在数据库里的父节点主键值
    private String name;//树上显示的名称
    private boolean open;//zTree是否默认展开
    private String comboId;//用来表示zTree上父子节点关系的节点Id
    private String comboParentId;//用来表示zTree上父子节点关系的父节点Id

    public static final String PREFIX_MENU = "MENU_";//菜单表节点前缀

    public static final String PREFIX_ACTION = "ACTION_";//动作表节点前缀

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getComboParentId() {
        return comboParentId;
    }

    public void setComboParentId(String comboParentId) {
        this.comboParentId = comboParentId;
    }
}
