package com.yt.comment.service;

import com.yt.comment.dto.CommentDto;
import com.yt.comment.dto.CommentForSubmitDto;
import com.yt.comment.dto.CommentListDto;
import com.yt.comment.entity.Page;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface CommentService {

    /**
     * 保存评论
     * @param commentForSubmitDto 提交的评论Dto对象
     * @return
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);

    /**
     * 按分页条件，根据商户ID获取商户下的评论列表dto
     * @param businessId 商户id
     * @param page 分页对象
     * @return 评论列表dto
     */
    CommentListDto getListByBusinessId(Long businessId, Page page);

    /**
     * 根据查询条件获取评论列表
     * @param dto
     * @return
     */
    List<CommentDto> getListByCondition(CommentDto dto);
}
