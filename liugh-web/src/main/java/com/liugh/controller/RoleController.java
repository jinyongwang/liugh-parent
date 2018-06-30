package com.liugh.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.base.PageResult;
import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.entity.Role;
import com.liugh.model.RoleModel;
import com.liugh.service.IRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/api/role")
//不加入swagger ui里
@ApiIgnore
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping(value = "/all")
    @RequiresPermissions(value = {"role:list"})
    public PublicResult<List<Role>> findAll() {
        return new PublicResult<>(PublicResultConstant.SUCCESS, roleService.selectList(new EntityWrapper<>()));
    }

    @ApiOperation(value="获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "10", dataType = "Integer")
    })
    @GetMapping(value = "/page")
    @RequiresPermissions(value = {"role:list"})
    public PublicResult<PageResult<Role>> findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                                   @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        Page<Role> model = roleService.selectPage(new Page<>(pageIndex, pageSize));
        return new PublicResult<>(PublicResultConstant.SUCCESS, new PageResult<>(
                model.getTotal(),
                pageIndex, pageSize,
                model.getRecords()));
    }

    @ApiOperation(value="新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleCode", value = "职责描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "permissionId", value = "权限List<Integer>", required = true, dataType = "List<Integer>"),
    })
    @PostMapping
    @RequiresPermissions(value = {"role:add"})
    public PublicResult<String> addRole(RoleModel roleModel) {
        boolean result = roleService.add(roleModel);
        return !result?new PublicResult<>(PublicResultConstant.INVALID_USER, null): new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

}

