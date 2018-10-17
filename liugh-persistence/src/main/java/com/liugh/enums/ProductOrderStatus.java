package com.liugh.enums;


import com.liugh.base.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 设计订单流程状态
 * </p>
 *
 * @author liugh
 * @since 2018-10-17
 */
public enum ProductOrderStatus implements OrderStatus {

	holder(null, null),

	/**
	 * 待付款
	 */
	waitPay(null, OrderAction.submit),

	/**
	 * 待处理
	 */
	waitHandle(waitPay, OrderAction.pay),

	/**
	 * 待发货
	 */
	waitDelivery(waitHandle, OrderAction.finishProduce),

	/**
	 * 待收货
	 */
	waitReceive(waitDelivery, OrderAction.deliver),

	/**
	 * 已完成（用户确认收货）
	 */
	finished(waitReceive, OrderAction.confirmReceive),

	/**
	 * 已取消
	 */
	canceled(waitPay, OrderAction.cancel),

	/**
	 * 已关闭
	 */
	closed(null, null);

	private OrderStatus prevStatus;

	private OrderAction action;

	private Logger logger = LoggerFactory.getLogger(ProductOrderStatus.class);

	private ProductOrderStatus(OrderStatus prevStatus, OrderAction action) {
		this.prevStatus = prevStatus;
		this.action = action;
	}

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public OrderStatus prevStatus() {
		return this.prevStatus;
	}

	@Override
	public boolean canTransformTo(OrderStatus orderStatus) {
		if (orderStatus == null)
			return false;
		return orderStatus.prevStatus() == this;
	}

	@Override
	public OrderStatus getByName(String statusName) throws Exception {
		OrderStatus result = null;
		for (ProductOrderStatus status : ProductOrderStatus.values()) {
			logger.info(status.getName());
			if (status.name().equals(statusName)) {
				result = status;
				break;
			}
		}
		if (result == null)
			throw new BusinessException("未识别的订单状态!");
		return result;
	}

	@Override
	public OrderStatus getByAction(OrderAction action) throws Exception {
		OrderStatus result = null;
		for (ProductOrderStatus status : ProductOrderStatus.values()) {
			if (status.action != null && status.action == action) {

				result = status;
				break;
			}
		}
		if (result == null)
			throw new BusinessException("未识别的订单状态!");
		return result;
	}

	@Override
	public OrderStatus getCloseStatus() {
		return closed;
	}

	@Override
	public OrderStatus getFinishedStatus() {
		return finished;
	}

	@Override
	public OrderStatus getCancleStatus() {
		return canceled;
	}

	@Override
	public OrderStatus getWaitPayStatus() {
		return waitPay;
	}


}
