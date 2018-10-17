package com.liugh.service.handler;

import com.liugh.entity.Order;
import com.liugh.enums.OrderAction;
import com.liugh.enums.OrderType;
import com.liugh.model.OrderModel;
import com.liugh.service.processor.ActionProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liugh
 * @since 2018-10-17
 */
@Component("ProductOrderHandler")
public class ProductOrderHandler extends OrderHandler {

	@Override
	public Order handleInternal(OrderAction action, OrderType orderType, OrderModel orderDef, Order currentOrder) throws Exception {
		return  ActionProcessor.process(action,orderType,orderDef,currentOrder);
	}
}
