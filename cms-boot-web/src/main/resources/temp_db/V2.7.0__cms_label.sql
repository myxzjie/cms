SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `cms_label` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='标签';

CREATE TABLE `cms_article_label` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(11) DEFAULT NULL,
  `label_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='内容标签';


SET FOREIGN_KEY_CHECKS = 1;
