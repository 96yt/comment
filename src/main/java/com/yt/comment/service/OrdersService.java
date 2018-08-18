package com.yt.comment.service;

import com.yt.comment.dto.OrdersDto;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface OrdersService {

    /**
     * 新增订单
     * @param ordersDto
     * @return
     */
    boolean add(OrdersDto ordersDto);

    /**
     * 根据主键获取订单的Dto对象
     * @param id
     * @return
     */
    OrdersDto getById(Long id);

    /**
     * 根据会员id获取全部订单dto列表
     * @param memberId
     * @return
     */
    List<OrdersDto> getListByMemberId(Long memberId);

    /**
     * 根据会员电话号码查询订单
     * @param dto
     * @return
     */
    List<OrdersDto> getListByMemberPhone(OrdersDto dto);
}
