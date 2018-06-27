package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 验证码发送记录
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@TableName("tb_sms_verify")
public class SmsVerify extends Model<SmsVerify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "sms_verify_id", type = IdType.AUTO)
    private Integer smsVerifyId;
    /**
     * 短信编号（可以自己生成，也可以第三方复返回）
     */
    @TableField("sms_id")
    private String smsId;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 验证码
     */
    @TableField("sms_verify")
    private String smsVerify;
    /**
     * 验证码类型（1：登录验证，2：注册验证，3：忘记密码，4：修改账号）
     */
    @TableField("sms_type")
    private Integer smsType;
    /**
     * 发送时间
     */
    @TableField("create_time")
    private Long createTime;

    public SmsVerify(){}

    public SmsVerify(String smsId, String mobile, String smsVerify, Integer smsType, Long createTime) {
        this.smsId = smsId;
        this.mobile = mobile;
        this.smsVerify = smsVerify;
        this.smsType = smsType;
        this.createTime = createTime;
    }

    public Integer getSmsVerifyId() {
        return smsVerifyId;
    }

    public void setSmsVerifyId(Integer smsVerifyId) {
        this.smsVerifyId = smsVerifyId;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsVerify() {
        return smsVerify;
    }

    public void setSmsVerify(String smsVerify) {
        this.smsVerify = smsVerify;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.smsVerifyId;
    }

    @Override
    public String toString() {
        return "SmsVerify{" +
        "smsVerifyId=" + smsVerifyId +
        ", smsId=" + smsId +
        ", mobile=" + mobile +
        ", smsVerify=" + smsVerify +
        ", smsType=" + smsType +
        ", createTime=" + createTime +
        "}";
    }
}
