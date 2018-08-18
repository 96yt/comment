package com.yt.comment.mapper;

import com.yt.comment.entity.Comment;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface CommentMapper {

    /**
     * 新增
     * @param comment
     * @return
     */
    int insert (Comment comment);

    /**
     * 根据查询条件分页查询评论列表
     * @param comment
     * @return
     */
    List<Comment> selectByPage(Comment comment);
}
