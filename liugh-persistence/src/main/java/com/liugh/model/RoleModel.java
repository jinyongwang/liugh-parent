package com.liugh.model;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liugh
 * @since 2018-06-30
 */
public class RoleModel {
    private String roleName;
    private String roleCode;
    private List<String> menuCode;

    public RoleModel() {
    }

    public RoleModel(String roleName, String roleCode, List<String> menuCode) {
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.menuCode = menuCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(List<String> menuCode) {
        this.menuCode = menuCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", menuCode=" + menuCode +
                '}';
    }


}
