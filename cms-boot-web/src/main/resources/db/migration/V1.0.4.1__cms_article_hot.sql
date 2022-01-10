SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------
-- Table structure for cms_article_hot
-- ------------------------------------
CREATE TABLE `cms_article_hot` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
   `article_id` bigint DEFAULT NULL COMMENT '文章内容ID',
   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
   `sort` int NOT NULL DEFAULT '50' COMMENT '排序',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文章内容热门';

SET FOREIGN_KEY_CHECKS = 1;
