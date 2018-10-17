package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息
 * </p>
 *
 * @author liugh
 * @since 2018-10-17
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("tb_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;


    /**
     * 物理主键
     */
    @TableId("sequence_nbr")
    private Long sequenceNbr;
    /**
     * 用户id
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 订单的唯一编号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 订单来源
     */
    private String source;
    /**
     * 订单类型(商品，设计)
     */
    @TableField("order_type")
    private String orderType;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;
    /**
     * 订单金额
     */
    @TableField("order_amount")
    private Integer orderAmount;
    /**
     * 邮费
     */
    private Integer postage;
    /**
     * 实际支付金额
     */
    @TableField("pay_amount")
    private Integer payAmount;
    /**
     * 折扣金额
     */
    private Integer discount;
    /**
     * 优惠金额
     */
    private Integer coupon;
    /**
     * 是否已评价
     */
    @TableField("is_comment")
    private String isComment;
    /**
     * 发票信息
     */
    @TableField("receipt_detail")
    private String receiptDetail;
    /**
     * 物流记录id
     */
    @TableField("transport_id")
    private Long transportId;
    /**
     * 收货人手机号
     */
    @TableField("receiver_mobile")
    private String receiverMobile;
    /**
     * 收货人姓名
     */
    @TableField("receiver_name")
    private String receiverName;
    /**
     * 收货信息(json存储)
     */
    @TableField("receiver_address")
    private String receiverAddress;
    /**
     * 是否已拆单
     */
    @TableField("is_split")
    private String isSplit;
    /**
     * 数据状态 :i 非激活 /a  激活
     */
    @TableField("rec_status")
    private String recStatus;
    /**
     * 取消订单理由
     */
    @TableField("cancel_result")
    private String cancelResult;
    /**
     * 审核不通过理由
     */
    @TableField("check_fail_result")
    private String checkFailResult;
    /**
     * 处理订单跟进记录
     */
    @TableField("order_recode")
    private String orderRecode;
    /**
     * 备注
     */
    private String description;

    @Override
    protected Serializable pkVal() {
        return this.sequenceNbr;
    }

}
