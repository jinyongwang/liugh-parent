package com.liugh.model;


import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: OrderDetailModel.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderDetailModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2017-05-17 17:24:19		freeapis		Initial.
 *
 * </pre>
 */
public class OrderDetailModel {
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String spuCode;
	private Long spuId;
	private Long skuId;
	private Long designerId;
	private String orderDetail;
	private Integer unitPrice;
	private Integer purchasePrice;
	private Integer count;
	private String canSettle;
	private String isSettled;
	private String status;

	//总价
	private Integer countPrice;
	//载体ID
	private Long sampleSpuId;
	//设计师名称
	private String designerName;
	//载体名称
	private String sampleName;
	//载体照片
	private String samplePic;
	//商品照片
	private String skuPic;

	private String unitPriceY;

	private String countPriceY;

    private Integer  settleAmount;

	private String settleAmountY;

	private Date finishedDate;//完成时间

	private Date settleDate;//结算时间

	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getSpuCode() {
		return this.spuCode;
	}
	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}
	
	public Long getSpuId() {
		return this.spuId;
	}
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	
	public Long getSkuId() {
		return this.skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	
	public Long getDesignerId() {
		return this.designerId;
	}
	public void setDesignerId(Long designerId) {
		this.designerId = designerId;
	}
	
	public String getOrderDetail() {
		return this.orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	public Integer getUnitPrice() {
		return this.unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getCount() {
		return this.count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCanSettle() {
		return canSettle;
	}

	public void setCanSettle(String canSettle) {
		this.canSettle = canSettle;
	}

	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(Integer countPrice) {
		this.countPrice = countPrice;
	}

	public Long getSampleSpuId() {
		return sampleSpuId;
	}

	public void setSampleSpuId(Long sampleSpuId) {
		this.sampleSpuId = sampleSpuId;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getSamplePic() {
		return samplePic;
	}

	public void setSamplePic(String samplePic) {
		this.samplePic = samplePic;
	}

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public String getUnitPriceY() {
		return unitPriceY;
	}

	public void setUnitPriceY(String unitPriceY) {
		this.unitPriceY = unitPriceY;
	}

	public String getCountPriceY() {
		return countPriceY;
	}

	public void setCountPriceY(String countPriceY) {
		this.countPriceY = countPriceY;
	}


	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public String getSettleAmountY() {
		return settleAmountY;
	}

	public void setSettleAmountY(String settleAmountY) {
		this.settleAmountY = settleAmountY;
	}

    public Integer getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Integer settleAmount) {
        this.settleAmount = settleAmount;
    }

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
}

