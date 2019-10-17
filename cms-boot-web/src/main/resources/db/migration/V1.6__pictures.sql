SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `sys_pictures_group` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图床管理组';

CREATE TABLE `sys_pictures` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(11) NOT NULL DEFAULT 0,
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `type` int(2) NOT NULL COMMENT '1 图片, 2 视频, 3 音频',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图床管理';


SET FOREIGN_KEY_CHECKS = 1;
