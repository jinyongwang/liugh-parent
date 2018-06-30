package com.liugh.service;

import com.liugh.entity.Role;
import com.baomidou.mybatisplus.service.IService;
import com.liugh.model.RoleModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据Id查询角色以及角色权限信息
     * @param roleCode 角色ID
     * @return 角色以及角色权限信息
     */
    RoleModel findById(String roleCode);

    /**
     * 新增角色以及角色权限信息
     * @param roleModel 角色以及角色权限信息
     * @return 新增结果 true/false
     */
    boolean add(RoleModel roleModel);

    /**
     * 修改角色以及角色权限信息
     * @param roleModel 角色以及角色权限信息
     * @return 新增结果 true/false
     */
    boolean update(RoleModel roleModel);

    /**
     * 根据角色Id删除角色以及角色权限
     * @param iroleCoded
     * @return
     */
    boolean delete(String roleCode);

}
