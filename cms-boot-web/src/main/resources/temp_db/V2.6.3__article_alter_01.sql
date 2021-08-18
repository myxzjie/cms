ALTER TABLE `cms_article`
MODIFY COLUMN `author` varchar(20) NULL DEFAULT NULL COMMENT '作者' AFTER `description`,
ADD COLUMN `user_id` bigint(11) NULL AFTER `author`;
