SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `role_code` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限code',
  `role_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `role_type` int(2) DEFAULT '1' COMMENT '权限类型 1系统，2cms,3微信，4客户端',
  `role_desc` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限描述',
  `state` int(2) DEFAULT '1' COMMENT '权限状态 1正常，0失败',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(11) DEFAULT NULL COMMENT '创建人',
  `org_id` bigint(11) DEFAULT NULL COMMENT '组织ID',
  `role_level` int(2) DEFAULT NULL COMMENT '权限级别，数字越大级别越小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMINISTRATOR', '超级管理员', '1', '2', '1', '2016-07-23 11:15:36', '1', '1', '12');
INSERT INTO `sys_role` VALUES ('2', 'ADMIN', '管理员', '1', '', '1', '2016-07-24 00:19:53', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('3', 'MEMBER', '普通会员', '4', '', '1', '2016-09-23 17:33:00', '1', '1', '2');
INSERT INTO `sys_role` VALUES ('4', 'USER', '普通用户', '1', '', '0', '2016-09-23 18:06:17', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('5', 'user_role_2', '普通用户', '4', '', '0', '2016-09-26 09:11:59', '1', '1', '1');


-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role`;
CREATE TABLE `sys_account_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL COMMENT '权限ID',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES ('1', '1', '1');
