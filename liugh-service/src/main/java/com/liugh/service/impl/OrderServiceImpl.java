package com.liugh.service.impl;

import com.liugh.entity.Order;
import com.liugh.enums.OrderAction;
import com.liugh.enums.OrderType;
import com.liugh.mapper.OrderMapper;
import com.liugh.model.OrderModel;
import com.liugh.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liugh.service.handler.OrderHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务实现类
 * </p>
 *
 * @author liugh
 * @since 2018-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    public Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception {
        Order order = OrderHandler.handle(action, orderType, orderDef);
        return order;
    }

}
