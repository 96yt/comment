package com.yt.comment.mapper;

import com.yt.comment.entity.Orders;

import java.util.List;

/**
 * Description:
 *
 * @author:Tong
 */
public interface OrdersMapper {

    /**
     * 新增
     * @param orders 订单表对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 根据主键查询订单表对象
     * @param id 主键值
     * @return 对象
     */
    Orders selectById(Long id);

    /**
     * 修改
     * @param orders
     * @return
     */
    int update(Orders orders);

    /**
     * 根据条件查询订单列表
     * @param orders 查询条件
     * @return 订单列表
     */
    List<Orders> select(Orders orders);

    /**
     * 根据会员手机号进行订单的查询
     * @param orders
     * @return
     */
    List<Orders> selectByPage(Orders orders);
}
