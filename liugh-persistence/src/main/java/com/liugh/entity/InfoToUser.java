package com.liugh.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户电话关系表
 * </p>
 *
 * @author liugh123
 * @since 2018-10-29
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("tb_info_to_user")
public class InfoToUser extends Model<InfoToUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "info_to_user_id", type = IdType.AUTO)
    private Integer infoToUserId;
    /**
     * 用户账号
     */

    @TableField("identity_info")
    private String identityInfo;
    /**
     * 用户主键
     */
    @TableField("user_no")
    private String userNo;

    /**
     * 登录类型:0 用户名登录 1手机登录 2 邮箱登录
     */
    @TableField("identity_type")
    private Integer identityType;


    @Override
    protected Serializable pkVal() {
        return this.infoToUserId;
    }
}
