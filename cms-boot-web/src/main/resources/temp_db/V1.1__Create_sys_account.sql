
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(16) DEFAULT NULL COMMENT '名称',
  `nick_name` varchar(12) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `e_mail` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `state` int(2) DEFAULT '1' COMMENT '状态 1正常，0失败',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remarks` varchar(128) DEFAULT NULL COMMENT ' 备注',
  `stype` char(2) DEFAULT NULL COMMENT '人员类型 1操作人员，2app用户',
  `sex` char(1) DEFAULT '1' COMMENT '性别 1女 0男',
  `card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `birtn` datetime DEFAULT NULL COMMENT '出生日期',
  `create_user` bigint(11) DEFAULT NULL COMMENT ' 创建人',
  `locked` int(2) DEFAULT '1' COMMENT '1,正常，0冻结',
  `org_id` bigint(11) DEFAULT NULL COMMENT '组织ID',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `sys_account`
ADD COLUMN `rank_id` bigint(11) NULL AFTER `avatar`,
ADD COLUMN `points` int(4) NULL AFTER `rank_id`;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account`(`id`, `name`, `nick_name`, `phone`, `e_mail`, `password`, `salt`, `state`, `create_date`, `remarks`, `stype`, `sex`, `card`, `birtn`, `create_user`, `locked`, `org_id`, `avatar`, `rank_id`, `points`) VALUES (1, 'admin', NULL, NULL, NULL, '$2a$10$A1hZCFyN3yg8K098dMzz4.rIw/2/AiBuno3.ApBS7n.OZ901k/pLS', NULL, 1, '2019-12-20 03:17:38', NULL, NULL, '1', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);