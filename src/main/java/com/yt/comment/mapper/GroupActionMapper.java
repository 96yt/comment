package com.yt.comment.mapper;

import com.yt.comment.entity.GroupAction;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface GroupActionMapper {

    int deleteByGroupId(Long groupId);

    int insertBatch(List<GroupAction> list);
}
