SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_account_fans
-- ----------------------------
DROP TABLE IF EXISTS `wx_account_fans`;
CREATE TABLE `wx_account_fans` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) DEFAULT NULL,
  `subscribe` int(1) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息',
  `nick_name` varchar(16) DEFAULT NULL COMMENT '性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `sex` int(1) DEFAULT NULL,
  `language` varchar(16) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `subscribe_time` int(11) DEFAULT NULL COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `union_id` varchar(32) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(64) DEFAULT NULL,
  `group_id` varchar(32) DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `tag_ids` varchar(64) DEFAULT NULL,
  `subscribe_scene` varchar(32) DEFAULT NULL,
  `qr_scene` varchar(256) DEFAULT NULL,
  `qr_scene_str` varchar(256) DEFAULT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `state` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='微信粉丝';


SET FOREIGN_KEY_CHECKS = 1;
