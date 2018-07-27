/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : cpm

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-07-06 15:43:03
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', 'menu-a8617c317b204969a054fdag233das2l', 'user', '用户管理列表', '1', '1', '', '1.jpg');
INSERT INTO `tb_menu` VALUES ('2', '0', 'menu-afd83fc912eb44d29012049aae184fd4', 'data', '数据管理列表', '1', '1', '/api/data/manager', null);
INSERT INTO `tb_menu` VALUES ('3', '0', 'menu-a8617c31654653a054b68a343254565fss', 'system', '系统管理', '1', '1', null, 'anticon-laptop');
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
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_operation_log
-- ----------------------------

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
INSERT INTO `liugh`.`tb_role` (`role_code`, `role_name`) VALUES ('role-cf8fea2055344df59a0d3e80540c78f9', 'sysadmin');
INSERT INTO `liugh`.`tb_role` (`role_code`, `role_name`) VALUES ('role-dfsg3tsdfgh55334fsdg2asdf23qrasdf3', 'admin');
INSERT INTO `liugh`.`tb_role` (`role_code`, `role_name`) VALUES ('role-f7943542d87a4f028f446b71d9ede25d', 'user');


-- ----------------------------
-- Table structure for tb_role_to_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_to_menu`;
CREATE TABLE `tb_role_to_menu` (
  `role_to_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) NOT NULL COMMENT '角色代号',
  `menu_code` varchar(50) NOT NULL COMMENT '菜单代号,规范权限标识',
  PRIMARY KEY (`role_to_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

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
INSERT INTO `tb_user` VALUES ('user-006efece76c8433d8974c1a2f98422b6', '13888888888', 'javaer', '$2a$10$VwPL.rHo4PETgCcLDTN2LOwE.ksgCA0jLHbVX5yXEoisHWihX7S/i', null, '1529982192887', null, '1', 'java开发');
INSERT INTO `tb_user` VALUES ('user-190f8710857f4a239570387ffc676c39', '15802933752', 'eee', '$2a$10$fJ9Ou1Ffi9XDf1OQbn0NNe7UGqyRHkOj/hKiELuCXifLVqRATWB.W', 'eee', '1529999033844', null, '1', 'eee');
INSERT INTO `tb_user` VALUES ('user-573388ebd14348cf8b546a6bfdf98ca3', '18792420526', '5', '$2a$10$fRJZ6tiCQpGUxsKVcA4yeeRMySiMAtL60aGGRuvJEqzE4LEOuYg2q', '5', '1530086891950', null, '1', '5');

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
INSERT INTO `tb_user_to_role` VALUES ('2', 'user-006efece76c8433d8974c1a2f98422b6', 'role-cf8fea2055344df59a0d3e80540c78f9');
INSERT INTO `tb_user_to_role` VALUES ('3', 'user-190f8710857f4a239570387ffc676c39', 'role-f7943542d87a4f028f446b71d9ede25d');
