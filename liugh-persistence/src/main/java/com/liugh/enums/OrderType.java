package com.liugh.enums;

/**
 * <p>
 * 订单类型枚举
 * </p>
 *
 * @author liugh
 * @since 2018-10-17
 */
public enum OrderType {

    /**
     * 采购订单
     */
    Purchase(SampleDesignOrderStatus.holder),

    /**
     * 商品成品订单
     */
    Product(ProductOrderStatus.holder);

    private OrderStatus statusHolder;

    private OrderType(OrderStatus statusHolder){
        this.statusHolder = statusHolder;
    }

    public OrderStatus getStatusHolder(){
        return this.statusHolder;
    }

    public static OrderType getInstance(String orderType){
        try {
            return OrderType.valueOf(orderType);
        }catch (Exception e){
            return null;
        }
    }
}

