package com.liugh.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author liugh123
 * @since 2018-05-08
 */
@RestController
@RequestMapping("/operationLog")
//不加入swagger ui里
@ApiIgnore
public class OperationLogController {

}

