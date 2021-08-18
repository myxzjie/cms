SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `wx_article`
-- ----------------------------
DROP TABLE IF EXISTS `wx_article`;
CREATE TABLE `wx_article` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `digest` varchar(50) DEFAULT NULL COMMENT '摘要',
  `author` varchar(32) DEFAULT NULL COMMENT '作者',
  `is_cover` char(1) DEFAULT NULL COMMENT '是否展示封面图片（0/1）',
  `thumb_media_id` varchar(50) DEFAULT NULL COMMENT '上传微信，封面图片标识',
  `content` text COMMENT '内容',
  `url` varchar(50) DEFAULT NULL COMMENT '内容链接',
  `sort` int(11) DEFAULT NULL COMMENT '文章排序',
  `image` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `is_comment` varchar(32) DEFAULT NULL COMMENT '是否可以留言',
  `is_fans_comment` varchar(32) DEFAULT NULL COMMENT '是否仅粉丝可以留言',
  `news_id` varchar(32) DEFAULT NULL COMMENT '图文ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图文消息文章列表表';

-- ----------------------------
-- Table structure for `wx_article_template`
-- ----------------------------
DROP TABLE IF EXISTS `wx_article_template`;
CREATE TABLE `wx_article_template` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键 主键ID',
  `tpl_name` varchar(32) DEFAULT NULL COMMENT '模板名称',
  `publish` bit(1) DEFAULT b'0' COMMENT '是否已上传微信',
  `media_id` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图文消息模板表';

-- ----------------------------
-- Table structure for `wx_text`
-- ----------------------------
DROP TABLE IF EXISTS `wx_text_template`;
CREATE TABLE `wx_text_template` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tpl_name` varchar(32) DEFAULT NULL COMMENT '模板名字',
  `content` varchar(255) DEFAULT NULL COMMENT '模板内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='文本模板表';



SET FOREIGN_KEY_CHECKS = 1;
