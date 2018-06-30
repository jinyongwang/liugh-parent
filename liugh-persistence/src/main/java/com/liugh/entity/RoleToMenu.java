package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
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

    public RoleToMenu(String roleCode, String menuCode) {
        this.roleCode=roleCode;
        this.menuCode=menuCode;
    }
    public RoleToMenu() {
    }

    public Integer getRoleToMenuId() {
        return roleToMenuId;
    }

    public void setRoleToMenuId(Integer roleToMenuId) {
        this.roleToMenuId = roleToMenuId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleToMenuId;
    }

    @Override
    public String toString() {
        return "RoleToMenu{" +
                "roleToMenuId=" + roleToMenuId +
                ", roleCode=" + roleCode +
                ", menuCode=" + menuCode +
                "}";
    }
}
