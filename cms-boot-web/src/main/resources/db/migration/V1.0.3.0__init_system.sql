SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
CREATE TABLE `sys_account` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   `name` varchar(16) DEFAULT NULL COMMENT '名称',
   `nick_name` varchar(12) DEFAULT NULL,
   `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
   `e_mail` varchar(128) DEFAULT NULL COMMENT '邮箱',
   `password` varchar(64) DEFAULT NULL COMMENT '密码',
   `salt` varchar(50) DEFAULT NULL COMMENT '盐',
   `state` int DEFAULT '1' COMMENT '状态 1正常，0失败',
   `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `remarks` varchar(128) DEFAULT NULL COMMENT ' 备注',
   `stype` char(2) DEFAULT NULL COMMENT '人员类型 1操作人员，2app用户',
   `sex` char(1) DEFAULT '1' COMMENT '性别 1女 0男',
   `card` varchar(20) DEFAULT NULL COMMENT '身份证号',
   `birtn` datetime DEFAULT NULL COMMENT '出生日期',
   `create_user` bigint DEFAULT NULL COMMENT ' 创建人',
   `locked` int DEFAULT '1' COMMENT '1,正常，0冻结',
   `org_id` bigint DEFAULT NULL COMMENT '组织ID',
   `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
   `rank_id` bigint DEFAULT NULL,
   `points` int DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息';

-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
CREATE TABLE `sys_account_role` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `role_id` bigint DEFAULT NULL COMMENT '权限ID',
    `user_id` bigint DEFAULT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色信息';

-- ----------------------------
-- Table structure for sys_key_data
-- ----------------------------
CREATE TABLE `sys_key_data` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `key` varchar(64) NOT NULL COMMENT 'key',
    `data` json DEFAULT NULL COMMENT 'data',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_date` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='key json data';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
CREATE TABLE `sys_menu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pid` bigint NOT NULL COMMENT '上级菜单ID',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
    `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件',
    `component_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '-' COMMENT '组件名称',
    `sort` int NOT NULL COMMENT '排序',
    `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
    `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接地址',
    `hidden` bit(1) DEFAULT b'0' COMMENT '是否隐藏',
    `create_date` datetime DEFAULT NULL COMMENT '创建日期',
    `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限',
    `type` int DEFAULT NULL COMMENT '类型',
    `cache` bit(1) DEFAULT b'0',
    `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `state` int NOT NULL DEFAULT '1' COMMENT '1 正常，0无效',
    `always_show` bit(1) DEFAULT b'0' COMMENT '是否展开',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `FK_MENU_PID` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '权限ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `FK_MENU_PERMISSION` (`menu_id`),
  KEY `FK_ROLE_PERMISSION` (`role_id`),
  CONSTRAINT `FK_MENU_PERMISSION` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FK_ROLE_PERMISSION` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='授权';

-- ----------------------------
-- Table structure for sys_pictures
-- ----------------------------
CREATE TABLE `sys_pictures` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `group_id` bigint NOT NULL DEFAULT '0',
    `user_id` bigint DEFAULT NULL COMMENT '用户ID',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
    `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接',
    `type` int NOT NULL COMMENT '1 图片, 2 视频, 3 音频',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `state` int NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
    `origin` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源地',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图床管理';

-- ----------------------------
-- Table structure for sys_pictures_group
-- ----------------------------
CREATE TABLE `sys_pictures_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图床管理组';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE `sys_role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '权限code',
    `role_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名称',
    `role_type` int DEFAULT '1' COMMENT '权限类型 1系统，2cms,3微信，4客户端',
    `role_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限描述',
    `state` int DEFAULT '1' COMMENT '权限状态 1正常，0失败',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint DEFAULT NULL COMMENT '创建人',
    `org_id` bigint DEFAULT NULL COMMENT '组织ID',
    `role_level` int DEFAULT NULL COMMENT '权限级别，数字越大级别越小',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色信息';

-- ----------------------------
-- Table structure for sys_system_log
-- ----------------------------
CREATE TABLE `sys_system_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `request_ip` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `type` int DEFAULT NULL COMMENT '类型',
  `level` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志级别',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '说明',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方法',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'url',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '参数',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `time` bigint DEFAULT NULL COMMENT '请求耗时',
  `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '异常详细',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_verify_code
-- ----------------------------
CREATE TABLE `sys_verify_code` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `scenes` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务名称',
   `value` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '值',
   `target` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '目标者',
   `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
   `create_date` datetime NOT NULL COMMENT '时间',
   `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '信息说明',
   `state` int NOT NULL DEFAULT '1' COMMENT '1 正常，0无效',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='验证code';

SET FOREIGN_KEY_CHECKS = 1;