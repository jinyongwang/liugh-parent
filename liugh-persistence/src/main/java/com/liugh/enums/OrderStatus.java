package com.liugh.enums;

/**
 * Description:订单状态接口,不同类型的订单走不同的订单流程,不同的订单流程有不同的状态节点
 * @author liugh
 * @since 2018-10-17
 */
public interface OrderStatus {

    String getName();

    OrderStatus prevStatus();

    boolean canTransformTo(OrderStatus orderStatus);

    OrderStatus getByName(String statusName) throws Exception;

    OrderStatus getByAction(OrderAction action) throws Exception;

    OrderStatus getCloseStatus();

    OrderStatus getCancleStatus();

    OrderStatus getWaitPayStatus();

    OrderStatus getFinishedStatus();

}