CREATE TABLE `sys_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL COMMENT '权限ID',
  `menu_id` bigint(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `FK_MENU_PERMISSION` (`menu_id`),
  KEY `FK_ROLE_PERMISSION` (`role_id`),
  CONSTRAINT `FK_MENU_PERMISSION` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FK_ROLE_PERMISSION` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB COMMENT='授权';
