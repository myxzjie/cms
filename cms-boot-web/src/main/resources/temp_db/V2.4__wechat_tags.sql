SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_tags
-- ----------------------------
DROP TABLE IF EXISTS `wx_tags`;
CREATE TABLE `wx_tags` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `name` varchar(64) DEFAULT NULL,
  `count` int(4) DEFAULT '0' COMMENT '此标签下粉丝数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='微信标签';

SET FOREIGN_KEY_CHECKS = 1;
