package com.yt.comment.service.impl;

import com.yt.comment.constant.CommentStateConst;
import com.yt.comment.dto.CommentDto;
import com.yt.comment.dto.CommentForSubmitDto;
import com.yt.comment.dto.CommentListDto;
import com.yt.comment.entity.Business;
import com.yt.comment.entity.Comment;
import com.yt.comment.entity.Orders;
import com.yt.comment.entity.Page;
import com.yt.comment.mapper.CommentMapper;
import com.yt.comment.mapper.OrdersMapper;
import com.yt.comment.service.CommentService;
import com.yt.comment.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    @Transactional
    public boolean add(CommentForSubmitDto commentForSubmitDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForSubmitDto,comment);
        comment.setId(null);
        comment.setOrdersId(commentForSubmitDto.getId());
        comment.setCreateTime(new Date());
        //保存评论
        commentMapper.insert(comment);
        Orders orders = new Orders();
        orders.setId(commentForSubmitDto.getId());
        orders.setCommentState(CommentStateConst.HAS_COMMENT);
        ordersMapper.update(orders);//更新订单评论状态
        return true;
    }

    @Override
    public CommentListDto getListByBusinessId(Long businessId, Page page) {
        CommentListDto result = new CommentListDto();
        //组织查询条件
        Comment comment = new Comment();
        Orders orders = new Orders();
        Business business = new Business();

        //评论里包含了订单对象
        comment.setOrders(orders);
        //订单对象里包含了商户对象
        orders.setBusiness(business);
        //设置商户主键
        business.setId(businessId);
        //前端app页码从0开始计算，这里需要加1
        page.setCurrentPage(page.getCurrentPage() + 1);
        //设置分页条件
        comment.setPage(page);
        List<Comment> commentList = commentMapper.selectByPage(comment);
        //组织返回值
        List<CommentDto> data = new ArrayList<>();
        result.setData(data);

        for (Comment commentTemp : commentList) {
            CommentDto commentDto = new CommentDto();
            data.add(commentDto);
            BeanUtils.copyProperties(commentTemp,commentDto);
            //隐藏手机号中间四位
            String hidePhone = CommonUtil.hidePhone(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
            commentDto.setUsername(hidePhone);
        }
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        return result;
    }

    @Override
    public List<CommentDto> getListByCondition(CommentDto dto) {
        List<CommentDto> result = new ArrayList<>();

        Comment condition = new Comment();
        BeanUtils.copyProperties(dto,condition);

        List<Comment> commentList = commentMapper.selectByPage(condition);
        for (Comment comment : commentList) {
            CommentDto temp = new CommentDto();
            result.add(temp);
            BeanUtils.copyProperties(comment,temp);
        }
        return result;
    }
}
