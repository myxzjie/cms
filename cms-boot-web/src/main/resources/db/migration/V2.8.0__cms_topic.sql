SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `cms_topic` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  `cover_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `sort` int(11) DEFAULT '50',
  `recommend_stat` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='话题';


SET FOREIGN_KEY_CHECKS = 1;
