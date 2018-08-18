package com.yt.comment.dto;

/**
 * Description:
 *
 * @author:Tong
 */
public class MenuForMoveDto {

    private Long dropNodeId;//被拖拽的节点的ID

    private Long targetNodeId;//移动到目标节点的ID

    private String moveType;//移动方式

    public static final String MOVE_TYPE_INNER = "inner";//移动到目标节点的子节点

    public static final String MOVE_TYPE_PREV = "prev";//移动到目标节点的前一个节点

    public static final String MOVE_TYPE_NEXT = "next";//移动到目标节点的后一个节点

    public Long getDropNodeId() {
        return dropNodeId;
    }

    public void setDropNodeId(Long dropNodeId) {
        this.dropNodeId = dropNodeId;
    }

    public Long getTargetNodeId() {
        return targetNodeId;
    }

    public void setTargetNodeId(Long targetNodeId) {
        this.targetNodeId = targetNodeId;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }


}
