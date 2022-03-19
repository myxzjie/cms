SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_ad
-- ----------------------------
CREATE TABLE `sys_visit_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ip` varchar(64) NOT NULL COMMENT 'ip地址',
  `type` varchar(64) DEFAULT '0' COMMENT '类型',
  `pv_date` datetime NOT NULL COMMENT 'pv date',
  `from_name` varchar(256) DEFAULT NULL COMMENT 'from 名称',
  `from_path` varchar(256) DEFAULT NULL COMMENT 'from 路径',
  `from_query` varchar(256) NOT NULL COMMENT 'from 参数',
  `to_name` varchar(256) NOT NULL COMMENT 'to 名称',
  `to_path` varchar(256) DEFAULT NULL COMMENT 'to 路径',
  `to_query` varchar(256) DEFAULT NULL COMMENT 'to 参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='PV Log';

SET FOREIGN_KEY_CHECKS = 1;