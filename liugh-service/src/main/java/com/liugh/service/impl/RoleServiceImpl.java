package com.liugh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liugh.entity.RoleToMenu;
import com.liugh.model.RoleModel;
import com.liugh.service.IRoleService;
import com.liugh.entity.Role;
import com.liugh.mapper.RoleMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liugh.service.IRoleToMenuService;
import com.liugh.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleToMenuService roleToMenuService;

    @Override
    public RoleModel findById(String roleCode) {
        Role role = this.selectById(roleCode);
        List<String> menuCodes = new ArrayList<>();
        EntityWrapper<RoleToMenu> ew = new EntityWrapper<>();
        ew.where("role_code={0}", roleCode);
        List<RoleToMenu> roleMenuList = roleToMenuService.selectList(ew);
        if (!ComUtil.isEmpty(roleMenuList)) {
            for (RoleToMenu roleToMenu : roleMenuList) {
                menuCodes.add(roleToMenu.getMenuCode());
            }
        }
        return new RoleModel(role.getRoleName(), role.getRoleCode(), menuCodes);
    }

    @Override
    public boolean add(RoleModel roleModel) {
        Role role = new Role(roleModel.getRoleName(), roleModel.getRoleCode());
        boolean result = this.insert(role);
        if (! result) {
            return false;
        }
        result = roleToMenuService.saveAll(role.getRoleCode(), roleModel.getMenuCode());
        return result;
    }

    @Override
    public boolean update(RoleModel roleModel) {
        Role role = this.selectById(roleModel.getRoleCode());
        if (ComUtil.isEmpty(role)) {
            return false;
        }
        role.setRoleCode(roleModel.getRoleCode());
        role.setRoleName(roleModel.getRoleName());
        boolean result = this.updateById(role);
        if (! result) {
            return result;
        }
        result = roleToMenuService.deleteAllByRoleCode(roleModel.getRoleCode());
        if (! result) {
            return result;
        }
        result = roleToMenuService.saveAll(role.getRoleCode(), roleModel.getMenuCode());
        return result;
    }

    @Override
    public boolean delete(String roleCode) {
        boolean result = roleToMenuService.deleteAllByRoleCode(roleCode);
        if(! result){
            return  result;
        }
        return this.deleteById(roleCode);
    }
}
