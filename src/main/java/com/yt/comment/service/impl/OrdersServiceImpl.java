package com.yt.comment.service.impl;

import com.yt.comment.constant.CommentStateConst;
import com.yt.comment.dto.OrdersDto;
import com.yt.comment.entity.Orders;
import com.yt.comment.mapper.OrdersMapper;
import com.yt.comment.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Value("${businessImage.url}")
    private String businessImageUrl;

    @Override
    public boolean add(OrdersDto ordersDto) {
        Orders orders = new Orders();BeanUtils.copyProperties(ordersDto,orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        orders.setCreateTime(new Date());
        ordersMapper.insert(orders);
        return true;
    }

    @Override
    public OrdersDto getById(Long id) {
        OrdersDto result = new OrdersDto();
        Orders orders = ordersMapper.selectById(id);
        BeanUtils.copyProperties(orders,result);
        return result;
    }

    @Override
    public List<OrdersDto> getListByMemberId(Long memberId) {
        List<OrdersDto> result = new ArrayList<>();
        Orders ordersForSelect = new Orders();
        ordersForSelect.setMemberId(memberId);
        List<Orders> ordersList = ordersMapper.select(ordersForSelect);
        for (Orders orders : ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            result.add(ordersDto);
            BeanUtils.copyProperties(orders,ordersDto);
            ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
            ordersDto.setTitle(orders.getBusiness().getTitle());
            ordersDto.setCount(orders.getBusiness().getNumber());
        }
        return result;
    }

    @Override
    public List<OrdersDto> getListByMemberPhone(OrdersDto dto) {
        List<OrdersDto> result = new ArrayList<>();
        Orders condition = new Orders();
        BeanUtils.copyProperties(dto,condition);
        List<Orders> ordersList = ordersMapper.selectByPage(condition);
        for (Orders orders : ordersList) {
            OrdersDto temp = new OrdersDto();
            result.add(temp);
            BeanUtils.copyProperties(orders,temp);
        }
        return result;
    }
}
