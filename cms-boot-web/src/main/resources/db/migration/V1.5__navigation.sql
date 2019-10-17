SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `cms_navigation` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(11) NOT NULL DEFAULT 0  COMMENT 'pid',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(256) DEFAULT NULL COMMENT '地址',
  `enabled` tinyint(1) NOT NULL COMMENT '是否关闭;1开启; 0关闭;',
  `target` varchar(16) DEFAULT NULL COMMENT '跳转类型. 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  `sort` int(4) DEFAULT 50 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='导航栏';

SET FOREIGN_KEY_CHECKS = 1;
