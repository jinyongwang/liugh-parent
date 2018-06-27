package com.liugh.model;

/**
 * <p>
 * 文章评论表
 * </p>
 *
 * @author liuzq
 * @since 2018-06-26
 */
public class ArticleCommentModel {

    /**
     * 主键
     */
    private Integer articleCommentId;

    /**
     * 评论用户编码
     */
    private String fromUserNo;
    /**
     * 评论用户姓名
     */
    private String userName;
    /**
     * 评论用户头像
     */
    private String avatar;
    /**
     * 评论时间
     */
    private Long createTime;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复条数
     */
    private Integer replyCount;

    public Integer getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Integer articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
