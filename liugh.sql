/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : liugh

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-07-14 16:59:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_info_to_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_info_to_user`;
CREATE TABLE `tb_info_to_user` (
  `info_to_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `identity_info` varchar(50) NOT NULL COMMENT '用户账号',
  `user_no` varchar(50) NOT NULL COMMENT '用户主键',
  `identity_type` int(2) NOT NULL COMMENT '登录类型:0 用户名登录 1手机登录 2 邮箱登录',
  PRIMARY KEY (`info_to_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户账号关系表';

-- ----------------------------
-- Records of tb_info_to_user
-- ----------------------------
INSERT INTO `tb_info_to_user` VALUES ('1', '13888888888', 'user-006efece76c8433d8974c1a2f98422b6', '1');
INSERT INTO `tb_info_to_user` VALUES ('2', 'admin', 'user-006efece76c8433d8974c1a2f98422b6', '0');
INSERT INTO `tb_info_to_user` VALUES ('3', '53182347@qq.com', 'user-006efece76c8433d8974c1a2f98422b6', '2');
INSERT INTO `tb_info_to_user` VALUES ('4', '15802933752', 'user-190f8710857f4a239570387ffc676c39', '1');
INSERT INTO `tb_info_to_user` VALUES ('5', '15802933753@qq.com', 'user-190f8710857f4a239570387ffc676c39', '2');
INSERT INTO `tb_info_to_user` VALUES ('6', 'user', 'user-190f8710857f4a239570387ffc676c39', '0');
INSERT INTO `tb_info_to_user` VALUES ('7', '15802933752', 'user-573388ebd14348cf8b546a6bfdf98ca3', '1');
INSERT INTO `tb_info_to_user` VALUES ('8', 'liugh', 'user-573388ebd14348cf8b546a6bfdf98ca3', '0');
INSERT INTO `tb_info_to_user` VALUES ('9', '18792420523@qq.com', 'user-573388ebd14348cf8b546a6bfdf98ca3', '2');
INSERT INTO `tb_info_to_user` VALUES ('11', '17765071663', 'user-2eaa08353bc94aeeaa8087f5096f87f3', '1');
INSERT INTO `tb_info_to_user` VALUES ('12', '66864662@qq.com', 'user-2eaa08353bc94aeeaa8087f5096f87f3', '2');
INSERT INTO `tb_info_to_user` VALUES ('13', 'heihei', 'user-2eaa08353bc94aeeaa8087f5096f87f3', '0');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) NOT NULL COMMENT '父菜单主键',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  `code` varchar(50) DEFAULT NULL COMMENT '代码控制权限标识符',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_type` int(2) NOT NULL COMMENT '菜单类型，1：菜单  2：业务操作',
  `num` int(11) DEFAULT NULL COMMENT '菜单的序号',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2024 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', 'menu-a8617c317b204969a054fdag233das2l', 'userMenu', '用户管理列表', '1', '1', '', '1.jpg');
INSERT INTO `tb_menu` VALUES ('2', '0', 'menu-afd83fc912eb44d29012049aae184fd4', 'dataMenu', '数据管理列表', '1', '1', '/api/data/manager', null);
INSERT INTO `tb_menu` VALUES ('3', '0', 'menu-a8617c31654653a054b68a343254565fss', 'systemMenu', '系统管理', '1', '1', null, 'anticon-laptop');
INSERT INTO `tb_menu` VALUES ('101', '1', 'menu-974abc42a78040e7ac74ceecb70c02b5', 'user:list', '用户管理列表', '1', '1', '/api/user/manager', null);
INSERT INTO `tb_menu` VALUES ('102', '1', 'menu-ad61fb43be7d46e7a81e37593042f543', 'role:list', '角色列表', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('201', '2', 'menu-b16897c1c79b45b099939f5333530eaf', 'data:list1', '数据列表1', '1', '1', '', null);
INSERT INTO `tb_menu` VALUES ('202', '2', 'menu-ca569a407de7459f94e8b096180bc5e9', 'data:list2', '数据列表2', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('301', '3', 'menu-a8617c31654653a054fdsg23asdg5423', 'system:info', '网站信息', '1', '1', '/home/system-management/website-information', null);
INSERT INTO `tb_menu` VALUES ('302', '3', 'menu-a8617c317b204969a054b653df212zg712', 'system:passwd', '密码修改', '1', '1', '/home/system-management/password-modification', null);
INSERT INTO `tb_menu` VALUES ('303', '3', 'menu-a8617c31t34ytrfsdfg3j5e36u121rfdg465u', 'system:word', '屏蔽词', '1', '1', '/home/system-management/banned-word', null);
INSERT INTO `tb_menu` VALUES ('1011', '101', 'menu-2fcaf1983232442e9484b48114fe59f6', 'user:add', '新增用户', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('1012', '101', 'menu-b3556e9a47204c8abe1bcdd50047f6b4', 'user:edit', '编辑用户', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('1013', '101', 'menu-08a093222ab04020886049b726a89a4c', 'user:delete', '删除用户', '2', '3', '', null);
INSERT INTO `tb_menu` VALUES ('1021', '102', 'menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a', 'role:add', '新增角色', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('1022', '102', 'menu-c9623470db144ca68e961e053a6cc8c9', 'role:edit', '权限设置', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('1023', '102', 'menu-71f1c4edd8f24bf09db20867f7fdad2b', 'role:delete', '角色删除', '2', '3', '', null);
INSERT INTO `tb_menu` VALUES ('2010', '201', 'menu-ac1a7dfa51474de7b205eee5ad4d4dd2', 'data:importlist', '数据导入列表', '1', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2011', '201', 'menu-d3b091cadf644e66b49364e51641b10b', 'data:atrlist', '属性设置列表', '1', '2', '', null);
INSERT INTO `tb_menu` VALUES ('2012', '201', 'menu-1492623fee854b43b3e49b80e877e4a2', 'data:upload', '上传按钮', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2021', '202', 'menu-964ed4bc416840b48cdc2ab51510cfcf', 'data:delete', '属性删除', '2', '1', '', null);
INSERT INTO `tb_menu` VALUES ('2022', '202', 'menu-d68955aa58e0428ea824680484a074e6', 'data:add', '添加属性', '2', '2', '', null);
INSERT INTO `tb_menu` VALUES ('2023', '202', 'menu-a8617c317b204969a054b68a3473d3b4', 'data:edit', '属性编辑', '2', '3', '', null);

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` int(2) DEFAULT NULL COMMENT '类型 1:消息类型11;2:消息类型22;3:消息类型33;4:消息类型44;5:消息类型55',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `mobile` varchar(50) NOT NULL COMMENT '消息所有者',
  `theme_no` varchar(50) NOT NULL COMMENT '关联的主题no',
  `is_read` int(2) NOT NULL COMMENT '是否已读 0 未读; 1 已读',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='消息通知表';

-- ----------------------------
-- Records of tb_notice
-- ----------------------------

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
  `model_name` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `action` varchar(50) DEFAULT NULL COMMENT '操作',
  `succeed` int(2) DEFAULT NULL COMMENT '是否成功 1:成功 2异常',
  `message` longtext COMMENT '异常堆栈信息',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------
INSERT INTO `tb_operation_log` VALUES ('203', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"admin\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:61415', '1563093405368', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('204', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"admin\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62364', '1563094229504', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('205', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"53182347@qq.com\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62364', '1563094238134', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('206', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"531823417@qq.com\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62364', '1563094242076', 'Login', 'SignIn', '2', '用户不存在 &#10; com.liugh.service.impl.UserServiceImpl.checkMobileAndPasswd(UserServiceImpl.java:129) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207) &#10; com.sun.proxy.$Proxy126.checkMobileAndPasswd(Unknown Source) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99) &#10; org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282) &#10; org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213) &#10; com.sun.proxy.$Proxy126.checkMobileAndPasswd(Unknown Source) &#10; com.liugh.controller.LoginController.login(LoginController.java:48) &#10; com.liugh.controller.LoginController$$FastClassBySpringCGLIB$$63a1b1d5.invoke(<generated>) &#10; org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) &#10; org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:721) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) &#10; org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:97) &#10; com.liugh.aspect.RecordLogAspect.execute(RecordLogAspect.java:56) &#10; com.liugh.aspect.RecordLogAspect.doHandlerAspect(RecordLogAspect.java:37) &#10; com.liugh.config.ControllerAspect.validationPoint(ControllerAspect.java:50) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629) &#10; org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618) &#10; org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) &#10; org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) &#10; org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:656) &#10; com.liugh.controller.LoginController$$EnhancerBySpringCGLIB$$3972a38.login(<generated>) &#10; sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) &#10; sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) &#10; sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) &#10; java.lang.reflect.Method.invoke(Method.java:498) &#10; org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) &#10; org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) &#10; org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) &#10; org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) &#10; org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) &#10; org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963) &#10; org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897) &#10; org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) &#10; org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:648) &#10; org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) &#10; javax.servlet.http.HttpServlet.service(HttpServlet.java:729) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61) &#10; org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108) &#10; org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365) &#10; org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90) &#10; org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83) &#10; org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387) &#10; org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362) &#10; org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) &#10; org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) &#10; org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192) &#10; org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165) &#10; org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) &#10; org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) &#10; org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:474) &#10; org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) &#10; org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) &#10; org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) &#10; org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349) &#10; org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:783) &#10; org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) &#10; org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:798) &#10; org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1434) &#10; org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) &#10; java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) &#10; java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) &#10; org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) &#10; java.lang.Thread.run(Thread.java:745) &#10; ');
INSERT INTO `tb_operation_log` VALUES ('207', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"53182347@qq.com\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62599', '1563094481855', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('208', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"admin\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62599', '1563094517438', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('209', '注册接口', '{\"mobile\":\"17765071663\",\"roleName\":\"user\",\"createUser\":\"user-006efece76c8433d8974c1a2f98422b6\",\"email\":\"66864662@qq.com\",\"username\":\"heihei\"}', 'user-006efece76c8433d8974c1a2f98422b6', 'com.liugh.controller.LoginController', 'register', '0:0:0:0:0:0:0:1:62875', '1563094695899', 'Login', 'register', '1', null);
INSERT INTO `tb_operation_log` VALUES ('210', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"66864662@qq.com\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62875', '1563094711757', 'Login', 'SignIn', '1', null);
INSERT INTO `tb_operation_log` VALUES ('211', '前台密码登录接口', '{\"password\":\"*******\",\"identity\":\"heihei\"}', null, 'com.liugh.controller.LoginController', 'login', '0:0:0:0:0:0:0:1:62875', '1563094728483', 'Login', 'SignIn', '1', null);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `sequence_nbr` bigint(20) NOT NULL COMMENT '物理主键',
  `user_no` varchar(50) NOT NULL COMMENT '用户id',
  `order_no` varchar(20) NOT NULL COMMENT '订单的唯一编号',
  `source` varchar(32) DEFAULT NULL COMMENT '订单来源',
  `order_type` varchar(32) NOT NULL COMMENT '订单类型(商品，设计)',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `order_status` varchar(32) NOT NULL COMMENT '订单状态',
  `order_amount` int(11) NOT NULL COMMENT '订单金额',
  `postage` int(11) NOT NULL COMMENT '邮费',
  `pay_amount` int(11) NOT NULL COMMENT '实际支付金额',
  `discount` int(11) DEFAULT '0' COMMENT '折扣金额',
  `coupon` int(11) DEFAULT '0' COMMENT '优惠金额',
  `is_comment` varchar(1) NOT NULL COMMENT '是否已评价',
  `receipt_detail` varchar(500) DEFAULT NULL COMMENT '发票信息',
  `transport_id` bigint(20) DEFAULT NULL COMMENT '物流记录id',
  `receiver_mobile` varchar(11) NOT NULL COMMENT '收货人手机号',
  `receiver_name` varchar(50) NOT NULL COMMENT '收货人姓名',
  `receiver_address` varchar(500) DEFAULT NULL COMMENT '收货信息(json存储)',
  `is_split` varchar(1) NOT NULL COMMENT '是否已拆单',
  `rec_status` varchar(1) NOT NULL DEFAULT 'a' COMMENT '数据状态 :i 非激活 /a  激活',
  `cancel_result` varchar(100) DEFAULT NULL COMMENT '取消订单理由',
  `check_fail_result` varchar(100) DEFAULT NULL COMMENT '审核不通过理由',
  `order_recode` varchar(100) DEFAULT NULL COMMENT '处理订单跟进记录',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`sequence_nbr`),
  UNIQUE KEY `INDEX_ORDER_NO` (`order_no`) USING BTREE,
  KEY `INDEX_USER_ID` (`user_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表，当前表只保存流转中的订单信息';

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('770480571594227712', 'user-006efece76c8433d8974c1a2f98422b6', '770480571594227712', 'postman', 'Product', '1539763171888', 'waitDelivery', '4540000', '0', '4540000', '0', '0', 'N', '{\"receiptType\":\"普通纸质发票\",\"receiptTitle\":\"公司\",\"receiptContent\":\"中国\"}', null, '13888888888', '刘大神', '西安', 'Y', 'A', null, null, null, null);
INSERT INTO `tb_order` VALUES ('770480571984297984', 'user-006efece76c8433d8974c1a2f98422b6', '770480571594227713', 'postman', 'Product', '1539763171888', 'waitReceive', '3360000', '0', '3360000', '0', '0', 'N', '{\"receiptType\":\"普通纸质发票\",\"receiptTitle\":\"公司\",\"receiptContent\":\"中国\"}', null, '13888888888', '刘大神', '西安', 'N', 'A', null, null, null, null);
INSERT INTO `tb_order` VALUES ('770480572001075200', 'user-006efece76c8433d8974c1a2f98422b6', '770480571594227714', 'postman', 'Product', '1539763171888', 'waitDelivery', '1180000', '0', '1180000', '0', '0', 'N', '{\"receiptType\":\"普通纸质发票\",\"receiptTitle\":\"公司\",\"receiptContent\":\"中国\"}', '872003869557731328', '13888888888', '刘大神', '西安', 'N', 'A', null, null, null, null);

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
INSERT INTO `tb_role` VALUES ('role-cf8fea2055344df59a0d3e80540c78f9', 'sysadmin');
INSERT INTO `tb_role` VALUES ('role-dfsg3tsdfgh55334fsdg2asdf23qrasdf3', 'admin');
INSERT INTO `tb_role` VALUES ('role-f7943542d87a4f028f446b71d9ede25d', 'user');

-- ----------------------------
-- Table structure for tb_role_to_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_to_menu`;
CREATE TABLE `tb_role_to_menu` (
  `role_to_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  PRIMARY KEY (`role_to_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of tb_role_to_menu
-- ----------------------------
INSERT INTO `tb_role_to_menu` VALUES ('1', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-e7c48090579444aeac20958f570d08b7');
INSERT INTO `tb_role_to_menu` VALUES ('2', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-afd83fc912eb44d29012049aae184fd4');
INSERT INTO `tb_role_to_menu` VALUES ('3', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-974abc42a78040e7ac74ceecb70c02b5');
INSERT INTO `tb_role_to_menu` VALUES ('4', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ad61fb43be7d46e7a81e37593042f543');
INSERT INTO `tb_role_to_menu` VALUES ('5', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-b16897c1c79b45b099939f5333530eaf');
INSERT INTO `tb_role_to_menu` VALUES ('6', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ca569a407de7459f94e8b096180bc5e9');
INSERT INTO `tb_role_to_menu` VALUES ('7', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ebd8496d182446d4a5b4df3b61822141');
INSERT INTO `tb_role_to_menu` VALUES ('8', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-2fcaf1983232442e9484b48114fe59f6');
INSERT INTO `tb_role_to_menu` VALUES ('9', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-b3556e9a47204c8abe1bcdd50047f6b4');
INSERT INTO `tb_role_to_menu` VALUES ('10', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-08a093222ab04020886049b726a89a4c');
INSERT INTO `tb_role_to_menu` VALUES ('11', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-3da7d8a0b35e42c7b4d0b3c9cb710a7a');
INSERT INTO `tb_role_to_menu` VALUES ('13', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-fc40bd8965c44f17bbe0994e1aa96102');
INSERT INTO `tb_role_to_menu` VALUES ('14', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-1492623fee854b43b3e49b80e877e4a2');
INSERT INTO `tb_role_to_menu` VALUES ('15', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-d3b091cadf644e66b49364e51641b10b');
INSERT INTO `tb_role_to_menu` VALUES ('16', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ac1a7dfa51474de7b205eee5ad4d4dd2');
INSERT INTO `tb_role_to_menu` VALUES ('17', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-71f1c4edd8f24bf09db20867f7fdad2b');
INSERT INTO `tb_role_to_menu` VALUES ('18', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-c9623470db144ca68e961e053a6cc8c9');
INSERT INTO `tb_role_to_menu` VALUES ('19', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-ee223032f573480e86e673c7d6754173');
INSERT INTO `tb_role_to_menu` VALUES ('20', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-f90d7a25a8514434a73f3b92ccb97fc2');
INSERT INTO `tb_role_to_menu` VALUES ('21', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-d68955aa58e0428ea824680484a074e6');
INSERT INTO `tb_role_to_menu` VALUES ('22', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054b68a3473d3b4');
INSERT INTO `tb_role_to_menu` VALUES ('23', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054fdag233das2l');
INSERT INTO `tb_role_to_menu` VALUES ('24', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31654653a054b68a343254565fss');
INSERT INTO `tb_role_to_menu` VALUES ('25', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31654653a054fdsg23asdg5423');
INSERT INTO `tb_role_to_menu` VALUES ('26', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c317b204969a054b653df212zg712');
INSERT INTO `tb_role_to_menu` VALUES ('27', 'role-cf8fea2055344df59a0d3e80540c78f9', 'menu-a8617c31t34ytrfsdfg3j5e36u121rfdg465u');
INSERT INTO `tb_role_to_menu` VALUES ('28', 'role-f7943542d87a4f028f446b71d9ede25d', 'menu-974abc42a78040e7ac74ceecb70c02b5');
INSERT INTO `tb_role_to_menu` VALUES ('29', 'role-f7943542d87a4f028f446b71d9ede25d', 'menu-a8617c317b204969a054fdag233das2l');

-- ----------------------------
-- Table structure for tb_sms_verify
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_verify`;
CREATE TABLE `tb_sms_verify` (
  `sms_verify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sms_id` varchar(64) NOT NULL COMMENT '短信编号（可以自己生成，也可以第三方复返回）',
  `mobile` varchar(11) NOT NULL COMMENT '电话号码',
  `sms_verify` varchar(4) NOT NULL COMMENT '验证码',
  `sms_type` int(2) NOT NULL COMMENT '验证码类型（1：登录验证，2：注册验证，3：忘记密码，4：修改账号）',
  `create_time` bigint(20) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`sms_verify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='验证码发送记录';

-- ----------------------------
-- Records of tb_sms_verify
-- ----------------------------
INSERT INTO `tb_sms_verify` VALUES ('4', '18062610474527691', '13888888888', '5721', '3', '1529981271139');
INSERT INTO `tb_sms_verify` VALUES ('5', '18062610524128338', '13888888888', '1227', '2', '1529981567067');
INSERT INTO `tb_sms_verify` VALUES ('6', '18062610474527691', '13888888888', '5721', '2', '1529981271137');
INSERT INTO `tb_sms_verify` VALUES ('7', '18062611353322244', '13888888888', '4988', '1', '1529984138804');
INSERT INTO `tb_sms_verify` VALUES ('8', '18062611474923414', '13888888888', '9078', '1', '1529984874513');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_no` varchar(50) NOT NULL COMMENT '用户主键',
  `mobile` varchar(11) NOT NULL COMMENT '是电话号码，也是账号（登录用）',
  `user_name` varchar(50) NOT NULL COMMENT '姓名',
  `pass_word` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `status` int(2) DEFAULT '2' COMMENT '状态值（1：启用，2：禁用，3：删除）',
  `job` varchar(32) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('user-006efece76c8433d8974c1a2f98422b6', '13888888888', 'admin', '$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i', '53182347@qq.com', '1529982192887', null, '1', 'java开发');
INSERT INTO `tb_user` VALUES ('user-190f8710857f4a239570387ffc676c39', '15802933753', 'user', '$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i', '15802933753@qq.com', '1529999033844', null, '1', 'eee');
INSERT INTO `tb_user` VALUES ('user-2eaa08353bc94aeeaa8087f5096f87f3', '17765071663', 'heihei', '$2a$10$A378Pg5hCfXW7qs6VFXitOBkI5czk4X.QcxC7irAD1BUgIQr8xKae', '66864662@qq.com', '1563094696422', null, '1', null);
INSERT INTO `tb_user` VALUES ('user-573388ebd14348cf8b546a6bfdf98ca3', '18792420523', 'liugh', '$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i', '18792420523@qq.com', '1530086891950', null, '1', '5');

-- ----------------------------
-- Table structure for tb_user_thirdparty
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_thirdparty`;
CREATE TABLE `tb_user_thirdparty` (
  `user_thirdparty_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `open_id` varchar(128) NOT NULL COMMENT '第三方Id',
  `user_no` varchar(50) DEFAULT NULL COMMENT '绑定用户的id',
  `access_token` varchar(500) DEFAULT NULL COMMENT '第三方token',
  `provider_type` varchar(32) NOT NULL COMMENT '第三方类型 qq:QQ 微信:WX 微博:SINA',
  `status` int(2) DEFAULT '1' COMMENT '状态值（1：启用，2：禁用，3：删除）',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_thirdparty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='第三方用户表';

-- ----------------------------
-- Records of tb_user_thirdparty
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_to_role`;
CREATE TABLE `tb_user_to_role` (
  `user_to_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_no` varchar(50) NOT NULL COMMENT '用户编号',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  PRIMARY KEY (`user_to_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of tb_user_to_role
-- ----------------------------
INSERT INTO `tb_user_to_role` VALUES ('2', 'user-006efece76c8433d8974c1a2f98422b6', 'role-cf8fea2055344df59a0d3e80540c78f9');
INSERT INTO `tb_user_to_role` VALUES ('3', 'user-190f8710857f4a239570387ffc676c39', 'role-f7943542d87a4f028f446b71d9ede25d');
INSERT INTO `tb_user_to_role` VALUES ('4', 'user-573388ebd14348cf8b546a6bfdf98ca3', 'role-f7943542d87a4f028f446b71d9ede25d');
INSERT INTO `tb_user_to_role` VALUES ('5', 'user-2eaa08353bc94aeeaa8087f5096f87f3', 'role-f7943542d87a4f028f446b71d9ede25d');
