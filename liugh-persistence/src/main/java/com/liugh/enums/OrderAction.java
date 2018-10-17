package com.liugh.enums;

/**
 * Description:订单处理动作
 * @author liugh
 * @since 2018-10-17
 */
public enum OrderAction {
    /**
     * 提交订单
     */
    submit("提交订单"),
    /**
     * 导出订单
     */
    export("导出订单"),
    /**
     * 审核通过
     */
    approved("审核通过"),
    /**
     * 审核不通过
     */
    unapproved("审核不通过"),
    /**
     * 支付订单
     */
    pay("支付订单"),
    /**
     * 发货
     */
    deliver("发货"),
    /**
     * 用户确认收货
     */
    confirmReceive("用户确认收货"),
    /**
     * 完成生产
     */
    finishProduce("完成生产"),
    /**
     * 关闭
     */
    close("关闭"),
    /**
     * 取消订单
     */
    cancel("取消订单");

    private String label;
    private OrderAction(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


    public static OrderAction getByName(String name){
        OrderAction result = null;
        for(OrderAction orderAction : OrderAction.values()){
            if(orderAction.name().equals(name)){
                result = orderAction;
                break;
            }
        }
        return result;
    }
}