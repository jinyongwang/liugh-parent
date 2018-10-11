package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("tb_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色代号主键
     */
    @TableId("role_code")
    private String roleCode;

    @Override
    protected Serializable pkVal() {
        return this.roleCode;
    }

}
