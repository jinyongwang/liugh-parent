package com.liugh.model;

/**
 * @author liugh
 */
public class NgUserModel {
    private String token;
    private String name;
    private String email;
    private Integer id;
    private Long time;
    private Integer roleId;
    private String avatar;
    private String telephone;
    private String unit;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public NgUserModel() {
    }

    public NgUserModel(String token, String name, String email, Integer id, Long time,
                       Integer roleId, String avatar, String telephone, String unit) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.id = id;
        this.time = time;
        this.roleId = roleId;
        this.avatar = avatar;
        this.telephone = telephone;
        this.unit = unit;
    }
}
