package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

/**
 * <p>
 * 角色菜单表
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
@TableName("tb_role_to_menu")
public class RoleToMenu extends Model<RoleToMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_to_menu_id", type = IdType.AUTO)
    private Integer roleToMenuId;
    /**
     * 角色代号
     */
    @TableField("role_code")
    private String roleCode;
    /**
     * 菜单代号,规范权限标识
     */
    @TableField("menu_code")
    private String menuCode;

    @Override
    protected Serializable pkVal() {
        return this.roleToMenuId;
    }

}
