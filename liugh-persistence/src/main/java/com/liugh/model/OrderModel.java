package com.liugh.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liugh
 * @since 2018-10-17
 */
public class OrderModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private Long userId;
	private String orderNo;
	private String parentOrderNo;
	private String source;
	private String orderType;
	private Date createTime;
	private String orderStatus;
	private Integer orderAmount;
	private Integer postage;
	private Integer payAmount;
	private Integer discount;
	private Integer coupon;
	private String isComment;
	private Long transportId;
	private String receiptDetail;
	private String receiverName;
	private String receiverMobile;
	private String receiverAddress;
	private String isParent;
	private String isSplit;
	//订单明细列表
	private List<OrderDetailModel> orderDetailModels;
	//下单方式:购物车结算、立即购买
	private String orderWay;
	//快递公司编码
	private String transportCompany;
	//运单号
	private String transportNo;
	//剩余时间（未支付则为剩余支付时间，未收货则是剩余收货时间）
	private Long remainingTime;
	//最后操作人
	private String recUserName;
	private String orderAmountY;
	private String payAmountY;
	private Long sequenceNBR;
	private Date recDate;

	public Long getSequenceNBR() {
		return sequenceNBR;
	}

	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}

	private String recUserId;
	private String recStatus;
	private String extend1;
	private String extend2;
	private String extend3;
	private String description;
	private String changedFields;


	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public String getRecUserId() {
		return recUserId;
	}
	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getExtend2() {
		return extend2;
	}
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	public String getExtend3() {
		return extend3;
	}
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getChangedFields() {
		return changedFields;
	}

	public void setChangedFields(String changedFields) {
		this.changedFields = changedFields;
	}
	public String getTransportCompany() {
		return transportCompany;
	}

	public void setTransportCompany(String transportCompany) {
		this.transportCompany = transportCompany;
	}

	public String getTransportNo() {
		return transportNo;
	}

	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getParentOrderNo() {
		return this.parentOrderNo;
	}
	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}
	
	public String getSource() {
		return this.source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getOrderType() {
		return this.orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getOrderStatus() {
		return this.orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderAmount() {
		return this.orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public Integer getPostage() {
		return this.postage;
	}
	public void setPostage(Integer postage) {
		this.postage = postage;
	}
	
	public Integer getPayAmount() {
		return this.payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	
	public Integer getDiscount() {
		return this.discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public Integer getCoupon() {
		return this.coupon;
	}
	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}
	
	public String getIsComment() {
		return this.isComment;
	}
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public Long getTransportId() {
		return transportId;
	}
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getOrderWay() {
		return orderWay;
	}

	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}

	public List<OrderDetailModel> getOrderDetailModels() {
		return orderDetailModels;
	}

	public void setOrderDetailModels(List<OrderDetailModel> orderDetailModels) {
		this.orderDetailModels = orderDetailModels;
	}

	public String getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(String receiptDetail) {
		this.receiptDetail = receiptDetail;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}

	public Long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(Long remainingTime) {
		this.remainingTime = remainingTime;
	}

	public String getRecUserName() {
		return recUserName;
	}

	public void setRecUserName(String recUserName) {
		this.recUserName = recUserName;
	}

	public String getOrderAmountY() {
		return orderAmountY;
	}

	public void setOrderAmountY(String orderAmountY) {
		this.orderAmountY = orderAmountY;
	}

	public String getPayAmountY() {
		return payAmountY;
	}

	public void setPayAmountY(String payAmountY) {
		this.payAmountY = payAmountY;
	}
}

