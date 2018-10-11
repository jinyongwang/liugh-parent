package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 第三方用户表
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("tb_user_thirdparty")
public class UserThirdparty extends Model<UserThirdparty> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "user_thirdparty_id", type = IdType.AUTO)
    private Integer userThirdpartyId;
    /**
     * 第三方Id
     */
    @TableField("open_id")
    private String openId;
    /**
     * 绑定用户的id
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 第三方token
     */
    @TableField("access_token")
    private String accessToken;
    /**
     * 第三方类型 qq:QQ 微信:WX 微博:SINA
     */
    @TableField("provider_type")
    private String providerType;
    /**
     * 状态值（1：启用，2：禁用，3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;

    @Override
    protected Serializable pkVal() {
        return this.userThirdpartyId;
    }

}
