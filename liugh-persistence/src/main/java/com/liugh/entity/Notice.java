package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 消息通知表
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
@TableName("tb_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型 1:消息类型11;2:消息类型22;3:消息类型33;4:消息类型44;5:消息类型55
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 消息所有者
     */
    private String mobile;
    /**
     * 关联的主题no
     */
    @TableField("theme_no")
    private String themeNo;
    /**
     * 是否已读 0 未读; 1 已读
     */
    @TableField("is_read")
    private Integer isRead;


    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getThemeNo() {
        return themeNo;
    }

    public void setThemeNo(String themeNo) {
        this.themeNo = themeNo;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }

    @Override
    public String toString() {
        return "Notice{" +
        "noticeId=" + noticeId +
        ", title=" + title +
        ", type=" + type +
        ", createTime=" + createTime +
        ", mobile=" + mobile +
        ", themeNo=" + themeNo +
        ", isRead=" + isRead +
        "}";
    }
}
