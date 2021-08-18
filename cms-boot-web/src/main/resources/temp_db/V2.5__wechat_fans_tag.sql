SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_fans_tag
-- ----------------------------
DROP TABLE IF EXISTS `wx_fans_tag`;
CREATE TABLE `wx_fans_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fans_id` bigint(11) DEFAULT NULL,
  `tag_id` bigint(11) DEFAULT NULL,
  `open_id` varchar(64) DEFAULT NULL COMMENT 'openId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='微信粉丝标签';

SET FOREIGN_KEY_CHECKS = 1;
