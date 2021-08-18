SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(11) NOT NULL COMMENT '上级菜单ID',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `component_name` varchar(20)  DEFAULT '-' COMMENT '组件名称',
  `sort` int(4) NOT NULL COMMENT '排序',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `hidden` bit(1) DEFAULT b'0' COMMENT '是否隐藏',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_MENU_PID` (`pid`) USING BTREE
) ENGINE=InnoDB COMMENT='菜单';

SET FOREIGN_KEY_CHECKS = 1;
