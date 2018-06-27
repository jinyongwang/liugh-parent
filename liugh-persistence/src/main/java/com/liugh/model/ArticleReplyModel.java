package com.liugh.model;

/**
 * <p>
 * 文章评论表
 * </p>
 *
 * @author liuzq
 * @since 2018-06-26
 */
public class ArticleReplyModel {

    /**
     * 主键
     */
    private Integer articleReplyId;

    /**
     * 回复用户编码
     */
    private String fromUserNo;
    /**
     * 回复用户姓名
     */
    private String fromUserName;
    /**
     * 回复用户头像
     */
    private String avatar;
    /**
     * 被回复用户编码
     */
    private String toUserNo;
    /**
     * 被回复用户姓名
     */
    private String toUserName;
    /**
     * 回复时间
     */
    private Long createTime;
    /**
     * 回复内容
     */
    private String content;

    public Integer getArticleReplyId() {
        return articleReplyId;
    }

    public void setArticleReplyId(Integer articleReplyId) {
        this.articleReplyId = articleReplyId;
    }

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
