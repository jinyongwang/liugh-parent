package com.liugh.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/roleToPermission")
//不加入swagger ui里
@ApiIgnore
public class RoleToMenuController {

}

