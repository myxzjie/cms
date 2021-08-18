SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_ad
-- ----------------------------
CREATE TABLE `cms_ad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `position_id` bigint NOT NULL COMMENT '0,站外广告;从1开始代表的是该广告所处的广告位,同表ad_postition中的字段position_id的值',
  `media_type` tinyint DEFAULT '0' COMMENT '广告类型,0图片;1文字;2代码',
  `ad_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '广告名称',
  `ad_link` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告链接地址',
  `content` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文字广告',
  `start_time` datetime NOT NULL COMMENT '广告开始时间',
  `end_time` datetime NOT NULL COMMENT '广告结速时间',
  `man` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告联系人',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告联系人的邮箱',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告联系人的电话',
  `click_count` int NOT NULL DEFAULT '0' COMMENT '该广告点击数',
  `enabled` tinyint(1) NOT NULL COMMENT '广告是否关闭;1开启; 0关闭; 关闭后广告将不再有效',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='广告';

-- ----------------------------
-- Table structure for cms_ad_position
-- ----------------------------
CREATE TABLE `cms_ad_position` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `user_id` bigint NOT NULL COMMENT '创建用户ID',
   `position_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '广告位名称',
   `position_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '广告位code',
   `ad_width` smallint NOT NULL COMMENT '广告位宽',
   `ad_height` smallint DEFAULT NULL COMMENT '广告位高',
   `position_model` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数模型',
   `position_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告位说明',
   `position_style` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '样例代码',
   `enabled` tinyint(1) DEFAULT NULL COMMENT '是否启动',
   `theme` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式模板',
   `create_date` datetime NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='广告位';

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
CREATE TABLE `cms_article` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
   `category_id` bigint DEFAULT NULL COMMENT '栏目ID',
   `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
   `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接',
   `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关键字',
   `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
   `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
   `user_id` bigint DEFAULT NULL,
   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
   `count_read` int NOT NULL DEFAULT '0' COMMENT '浏览量',
   `count_comment` int NOT NULL DEFAULT '0' COMMENT '评论量',
   `count_collect` int NOT NULL DEFAULT '0' COMMENT '收藏量',
   `count_forward` int NOT NULL DEFAULT '0' COMMENT '转发量',
   `count_praise` int NOT NULL DEFAULT '0' COMMENT '点赞量',
   `sort` int NOT NULL DEFAULT '50' COMMENT '排序',
   `state` int NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
   `approve_status` int DEFAULT NULL COMMENT '审核状态，1草稿，2提交，3发布',
   `publish_date` datetime DEFAULT NULL COMMENT '发布时间',
   `publish_author` bigint DEFAULT NULL COMMENT '发布者',
   `start_time` datetime DEFAULT NULL COMMENT '开始时间',
   `end_time` datetime DEFAULT NULL COMMENT '结束时间',
   `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
   `recommend_stat` int DEFAULT NULL COMMENT '推荐类型:0不推荐',
   `show_state` int DEFAULT '1' COMMENT '是否显示:1显示,0 隐藏',
   `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
   `content_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '内容类型:1图片,2视频，3音频',
   `origin_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源: 图片,视频,音频(多个逗号分割)',
   `image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '封面图',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文章内容';

-- ----------------------------
-- Table structure for cms_article_label
-- ----------------------------
CREATE TABLE `cms_article_label` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `article_id` bigint DEFAULT NULL,
 `label_id` bigint DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='内容标签';

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
CREATE TABLE `cms_category` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '栏目ID',
    `p_id` bigint DEFAULT '0' COMMENT '栏目父级ID',
    `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '栏目名称',
    `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
    `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接',
    `target` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '跳转类型. 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
    `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关键字',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '栏目描述',
    `sort` int DEFAULT '50' COMMENT '排序',
    `state` int DEFAULT NULL COMMENT '状态',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_date` datetime DEFAULT NULL COMMENT '修改时间',
    `show_state` int NOT NULL DEFAULT '1' COMMENT '是否显示栏目:1显示,0 隐藏',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='栏目';

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
CREATE TABLE `cms_comment` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `article_id` bigint DEFAULT NULL,
   `author` bigint DEFAULT NULL,
   `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '评论内容',
   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
   `show_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '是否显示. 1显示,0 隐藏',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='内容评论';

-- ----------------------------
-- Table structure for cms_label
-- ----------------------------
DROP TABLE IF EXISTS `cms_label`;
CREATE TABLE `cms_label` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `name` varchar(16) NOT NULL,
 `state` int NOT NULL DEFAULT '1',
 `create_date` datetime DEFAULT NULL,
 `update_date` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='标签';

-- ----------------------------
-- Table structure for cms_navigation
-- ----------------------------
CREATE TABLE `cms_navigation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `p_id` bigint NOT NULL DEFAULT '0' COMMENT 'pid',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `enabled` tinyint(1) NOT NULL COMMENT '是否关闭;1开启; 0关闭;',
  `target` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '跳转类型. 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  `sort` int DEFAULT '50' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='导航栏';

-- ----------------------------
-- Table structure for cms_topic
-- ----------------------------
DROP TABLE IF EXISTS `cms_topic`;
CREATE TABLE `cms_topic` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
 `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
 `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
 `sort` int DEFAULT '50',
 `recommend_stat` int DEFAULT '0',
 `create_date` datetime DEFAULT NULL,
 `update_date` datetime DEFAULT NULL,
 `state` int NOT NULL DEFAULT '1',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='话题';

SET FOREIGN_KEY_CHECKS = 1;
