package com.liugh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liugh.service.IRoleToMenuService;
import com.liugh.entity.RoleToMenu;
import com.liugh.mapper.RoleToMenuMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
public class RoleToMenuServiceImpl extends ServiceImpl<RoleToMenuMapper, RoleToMenu> implements IRoleToMenuService {

    @Override
    @Cacheable(value = "UserToRole",keyGenerator="wiselyKeyGenerator")
    public List<RoleToMenu> selectByRoleId(Integer roleId) {
        EntityWrapper<RoleToMenu> ew = new EntityWrapper<>();
        ew.where("role_id={0}", roleId);
        return this.selectList(ew);
    }
}
