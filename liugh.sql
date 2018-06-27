/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : cpm

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-06-27 23:25:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `parent_id` int(11) NOT NULL COMMENT '父菜单id',
  `code` varchar(50) NOT NULL COMMENT '菜单编码',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_type` int(2) NOT NULL COMMENT '菜单类型，0：菜单  1：按钮操作',
  `num` int(11) DEFAULT NULL COMMENT '菜单的序号',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  PRIMARY KEY (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('menu-08a093222ab04020886049b726a89a4c', '1013', '101', 'user:delete', '删除用户', '0', '3', '');
INSERT INTO `tb_menu` VALUES ('menu-1492623fee854b43b3e49b80e877e4a2', '2012', '201', 'atlas:import', '上传', '0', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-2fcaf1983232442e9484b48114fe59f6', '1011', '101', 'user:add', '新增用户', '0', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a', '1021', '102', 'role:add', '新增角色', '0', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-71f1c4edd8f24bf09db20867f7fdad2b', '1023', '102', 'role:delete', '角色删除', '0', '3', '');
INSERT INTO `tb_menu` VALUES ('menu-964ed4bc416840b48cdc2ab51510cfcf', '2021', '202', 'attribute:list', '属性列表', '0', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-974abc42a78040e7ac74ceecb70c02b5', '101', '1', 'user:list', '用户管理', '1', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-a8617c317b204969a054b68a3473d3b4', '2023', '202', 'attribute:edit', '属性编辑', '0', '3', '');
INSERT INTO `tb_menu` VALUES ('menu-ac1a7dfa51474de7b205eee5ad4d4dd2', '2010', '201', 'data:import', '数据导入', '1', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-ad61fb43be7d46e7a81e37593042f543', '102', '1', 'role:list', '角色列表', '1', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-afd83fc912eb44d29012049aae184fd4', '2', '0', 'data', '数据管理', '1', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-b16897c1c79b45b099939f5333530eaf', '201', '2', 'data:list1', '数据列表1', '1', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-b3556e9a47204c8abe1bcdd50047f6b4', '1012', '101', 'user:edit', '编辑用户', '0', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-c9623470db144ca68e961e053a6cc8c9', '1022', '102', 'role:edit', '权限设置', '0', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-ca569a407de7459f94e8b096180bc5e9', '202', '2', 'data:list2', '数据列表2', '1', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-d3b091cadf644e66b49364e51641b10b', '2011', '201', 'atlas:type', '属性设置', '1', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-d68955aa58e0428ea824680484a074e6', '2022', '202', 'attribute:add', '添加属性', '0', '2', '');
INSERT INTO `tb_menu` VALUES ('menu-e7c48090579444aeac20958f570d08b7', '1', '0', 'user', '用户管理', '1', '1', '');
INSERT INTO `tb_menu` VALUES ('menu-ebd8496d182446d4a5b4df3b61822141', '203', '2', 'data:list3', '数据列表3', '1', '3', '');
INSERT INTO `tb_menu` VALUES ('menu-ee223032f573480e86e673c7d6754173', '2014', '201', 'atlas:download', '下载', '0', '3', '');
INSERT INTO `tb_menu` VALUES ('menu-f90d7a25a8514434a73f3b92ccb97fc2', '2015', '201', 'atlas:list', '查询', '0', '4', '');
INSERT INTO `tb_menu` VALUES ('menu-fc40bd8965c44f17bbe0994e1aa96102', '2013', '201', 'atlas:delete', '删除', '0', '2', '');

-- ----------------------------
-- Table structure for tb_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operation_log`;
CREATE TABLE `tb_operation_log` (
  `operation_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_description` varchar(64) DEFAULT NULL COMMENT '日志描述',
  `action_args` varchar(300) DEFAULT NULL COMMENT '方法参数',
  `user_no` varchar(50) DEFAULT NULL COMMENT '用户主键',
  `class_name` varchar(300) DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(64) DEFAULT NULL COMMENT '方法名称',
  `ip` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `succeed` int(2) DEFAULT NULL COMMENT '是否成功 1:成功 2异常',
  `message` longtext COMMENT '异常堆栈信息',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
INSERT INTO `tb_operation_log` VALUES ('90', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"userName\":\"mobile\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63047', '1530112540825', '2', '缺少必填参数:mobile &#10; com.liugh.aspect.ValidationParam.hasAllRequired(ValidationParam.java:53) &#10; com.liugh.aspect.ValidationParam.doHandlerAspect(ValidationParam.java:25) &#10; com.liugh.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.liugh.aspect.RecordLog.doHandlerAspect(RecordLog.java:45) &#10; com.liugh.aspect.AspectHandler.doAspectHandler(AspectHandler.java:25) &#10; com.liugh.aspect.ParameterCheckAspect.validationPoint(ParameterCheckAspect.java:62) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:497) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.liugh.controller.LoginController$$EnhancerBySpringCGLIB$$494a1558.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:497) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:745) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('91', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"17765071662\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63047', '1530112554261', '1', null);
INSERT INTO `tb_operation_log` VALUES ('92', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"17765071662\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63047', '1530112559746', '1', null);
INSERT INTO `tb_operation_log` VALUES ('93', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"17765071662\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63047', '1530112564842', '1', null);
INSERT INTO `tb_operation_log` VALUES ('94', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"15802933735\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63124', '1530112807418', '1', null);
INSERT INTO `tb_operation_log` VALUES ('95', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"15802933735\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63124', '1530112812509', '1', null);
INSERT INTO `tb_operation_log` VALUES ('96', '前台密码登录接口:/login', '{\"passWord\":\"*******\",\"mobile\":\"15802933735\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:63153', '1530112977113', '1', null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_code` varchar(50) NOT NULL COMMENT '角色代号主键',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('role-0f0b4e1ab76d4ee6b49d94b59d5b9a86', '普通用户');
INSERT INTO `tb_role` VALUES ('role-4bc6e2b994b24a3284cab4172d10ac81', '系统管理员');

-- ----------------------------
-- Table structure for tb_role_to_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_to_menu`;
CREATE TABLE `tb_role_to_menu` (
  `role_to_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  PRIMARY KEY (`role_to_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of tb_role_to_menu
-- ----------------------------
INSERT INTO `tb_role_to_menu` VALUES ('1', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-e7c48090579444aeac20958f570d08b7');
INSERT INTO `tb_role_to_menu` VALUES ('2', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-afd83fc912eb44d29012049aae184fd4');
INSERT INTO `tb_role_to_menu` VALUES ('3', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-974abc42a78040e7ac74ceecb70c02b5');
INSERT INTO `tb_role_to_menu` VALUES ('4', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-ad61fb43be7d46e7a81e37593042f543');
INSERT INTO `tb_role_to_menu` VALUES ('5', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-b16897c1c79b45b099939f5333530eaf');
INSERT INTO `tb_role_to_menu` VALUES ('6', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-ca569a407de7459f94e8b096180bc5e9');
INSERT INTO `tb_role_to_menu` VALUES ('7', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-ebd8496d182446d4a5b4df3b61822141');
INSERT INTO `tb_role_to_menu` VALUES ('8', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-2fcaf1983232442e9484b48114fe59f6');
INSERT INTO `tb_role_to_menu` VALUES ('9', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-b3556e9a47204c8abe1bcdd50047f6b4');
INSERT INTO `tb_role_to_menu` VALUES ('10', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-08a093222ab04020886049b726a89a4c');
INSERT INTO `tb_role_to_menu` VALUES ('11', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a');
INSERT INTO `tb_role_to_menu` VALUES ('12', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-e7c48090579444aeac20958f570d08b7');
INSERT INTO `tb_role_to_menu` VALUES ('13', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-fc40bd8965c44f17bbe0994e1aa96102');
INSERT INTO `tb_role_to_menu` VALUES ('14', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-1492623fee854b43b3e49b80e877e4a2');
INSERT INTO `tb_role_to_menu` VALUES ('15', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-d3b091cadf644e66b49364e51641b10b');
INSERT INTO `tb_role_to_menu` VALUES ('16', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-ac1a7dfa51474de7b205eee5ad4d4dd2');
INSERT INTO `tb_role_to_menu` VALUES ('17', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-71f1c4edd8f24bf09db20867f7fdad2b');
INSERT INTO `tb_role_to_menu` VALUES ('18', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-c9623470db144ca68e961e053a6cc8c9');
INSERT INTO `tb_role_to_menu` VALUES ('19', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-ee223032f573480e86e673c7d6754173');
INSERT INTO `tb_role_to_menu` VALUES ('20', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-f90d7a25a8514434a73f3b92ccb97fc2');
INSERT INTO `tb_role_to_menu` VALUES ('21', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-d68955aa58e0428ea824680484a074e6');
INSERT INTO `tb_role_to_menu` VALUES ('22', 'role-4bc6e2b994b24a3284cab4172d10ac81', 'menu-a8617c317b204969a054b68a3473d3b4');

-- ----------------------------
-- Table structure for tb_sms_verify
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_verify`;
CREATE TABLE `tb_sms_verify` (
  `sms_verify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sms_id` varchar(64) NOT NULL COMMENT '短信编号（可以自己生成，也可以第三方复返回）',
  `mobile` varchar(11) NOT NULL COMMENT '电话号码',
  `sms_verify` varchar(4) NOT NULL COMMENT '验证码',
  `sms_type` int(2) NOT NULL COMMENT '验证码类型（1：登录验证，2：注册验证，3：修改密码，4：修改账号）',
  `create_time` bigint(20) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`sms_verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='验证码发送记录';

-- ----------------------------
-- Records of tb_sms_verify
-- ----------------------------
INSERT INTO `tb_sms_verify` VALUES ('4', '18062610474527691', '17765071662', '5721', '3', '1529981271139');
INSERT INTO `tb_sms_verify` VALUES ('5', '18062610524128338', '17765071662', '1227', '2', '1529981567067');
INSERT INTO `tb_sms_verify` VALUES ('6', '18062610474527691', '17765071662', '5721', '2', '1529981271137');
INSERT INTO `tb_sms_verify` VALUES ('7', '18062611353322244', '17765071662', '4988', '1', '1529984138804');
INSERT INTO `tb_sms_verify` VALUES ('8', '18062611474923414', '17765071662', '9078', '1', '1529984874513');
INSERT INTO `tb_sms_verify` VALUES ('9', '18062615374426676', '15802933735', '7016', '3', '1529998669671');
INSERT INTO `tb_sms_verify` VALUES ('10', '18062615431224175', '15802933735', '0395', '2', '1529998997079');
INSERT INTO `tb_sms_verify` VALUES ('11', '18062615461724109', '15802933735', '5779', '2', '1529999182814');
INSERT INTO `tb_sms_verify` VALUES ('13', '18062615544523274', '15802933735', '3253', '1', '1529999690552');
INSERT INTO `tb_sms_verify` VALUES ('14', '18062616002726381', '15802933735', '8020', '1', '1530000032381');
INSERT INTO `tb_sms_verify` VALUES ('15', '18062616022921991', '15802933735', '1845', '1', '1530000154636');
INSERT INTO `tb_sms_verify` VALUES ('16', '18062616055921577', '15802933735', '2604', '1', '1530000364098');
INSERT INTO `tb_sms_verify` VALUES ('17', '18062616075926771', '15802933735', '5818', '1', '1530000484373');
INSERT INTO `tb_sms_verify` VALUES ('18', '18062616105522691', '15802933735', '4516', '1', '1530000659965');
INSERT INTO `tb_sms_verify` VALUES ('19', '18062616133527457', '17765071662', '5839', '3', '1530000820928');
INSERT INTO `tb_sms_verify` VALUES ('20', '18062616163324246', '15802933735', '8249', '1', '1530000998588');
INSERT INTO `tb_sms_verify` VALUES ('21', '18062616533827998', '18792420709', '0851', '4', '1530003223400');
INSERT INTO `tb_sms_verify` VALUES ('22', '18062616550127726', '17765071662', '4423', '4', '1530003306194');
INSERT INTO `tb_sms_verify` VALUES ('23', '18062711451123204', '15802933735', '7464', '3', '1530071117320');
INSERT INTO `tb_sms_verify` VALUES ('24', '18062711481224683', '15802933735', '1166', '3', '1530071297720');
INSERT INTO `tb_sms_verify` VALUES ('25', '18062714572029930', '15802933735', '6871', '2', '1530082646361');
INSERT INTO `tb_sms_verify` VALUES ('26', '18062714572121409', '15802933735', '1996', '2', '1530082647355');
INSERT INTO `tb_sms_verify` VALUES ('27', '18062714574422981', '15802933735', '8320', '2', '1530082670121');
INSERT INTO `tb_sms_verify` VALUES ('28', '18062714580122041', '15802933735', '1852', '3', '1530082686818');
INSERT INTO `tb_sms_verify` VALUES ('29', '18062715002527791', '15802933735', '8372', '3', '1530082830941');
INSERT INTO `tb_sms_verify` VALUES ('30', '18062715015225048', '15802933735', '9358', '3', '1530082917715');
INSERT INTO `tb_sms_verify` VALUES ('31', '18062715054821288', '15802933735', '6705', '3', '1530083153637');
INSERT INTO `tb_sms_verify` VALUES ('32', '18062715222226973', '17765071662', '6637', '3', '1530084148199');
INSERT INTO `tb_sms_verify` VALUES ('33', '18062715224024302', '17765071662', '5476', '3', '1530084165794');
INSERT INTO `tb_sms_verify` VALUES ('34', '18062715235129279', '17765071662', '3487', '3', '1530084237440');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_no` varchar(50) NOT NULL COMMENT '用户主键',
  `mobile` varchar(11) NOT NULL COMMENT '是电话号码，也是账号（登录用）',
  `user_name` varchar(50) NOT NULL COMMENT '姓名',
  `pass_word` varchar(255) NOT NULL COMMENT '密码',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `status` int(2) DEFAULT '2' COMMENT '状态值（1：启用，2：禁用，3：删除）',
  `job` varchar(32) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('user-006efece-76c8-433d-8974-c1a2f98422b6', '15802933735', 'eee', '$2a$10$W618Xstq./l1bwOXlNXt/eQkj85GDMGPFHCygovovR3kTIvVx1KSi', 'eee', '1529999033844', null, '2', 'eee');
INSERT INTO `tb_user` VALUES ('user-190f8710-857f-4a23-9570-387ffc676c39', '17765071662', '&lt;script&gt;alert(1)&lt;script&gt;', '$2a$10$fJ9Ou1Ffi9XDf1OQbn0NNe7UGqyRHkOj/hKiELuCXifLVqRATWB.W', null, '1529982192887', null, '2', 'java开发');

-- ----------------------------
-- Table structure for tb_user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_to_role`;
CREATE TABLE `tb_user_to_role` (
  `user_to_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_no` varchar(50) NOT NULL COMMENT '用户编号',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  PRIMARY KEY (`user_to_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of tb_user_to_role
-- ----------------------------
INSERT INTO `tb_user_to_role` VALUES ('2', 'user-006efece-76c8-433d-8974-c1a2f98422b6', 'role-0f0b4e1ab76d4ee6b49d94b59d5b9a86');
INSERT INTO `tb_user_to_role` VALUES ('3', 'user-190f8710-857f-4a23-9570-387ffc676c39', 'role-4bc6e2b994b24a3284cab4172d10ac81');
