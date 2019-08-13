package com.rabbitmq.mapper;


import com.rabbitmq.model.OrderInfoModel;


public interface OrderMapper {

    /**
     * 插入订单信息
     *
     * @param orderInfoModel
     * @return
     */
    Long insert(OrderInfoModel orderInfoModel);

    /**
     * 按照订单号查询订单信息
     *
     * @param orderNo
     * @return
     */
    OrderInfoModel getByOrderNo(String orderNo);
}
