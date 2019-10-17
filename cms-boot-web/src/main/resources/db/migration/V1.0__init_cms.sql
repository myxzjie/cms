SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` bigint(11) DEFAULT NULL COMMENT '栏目ID',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `author` bigint(11) DEFAULT NULL COMMENT '作者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `count_read` int(4) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `count_comment` int(4) NOT NULL DEFAULT '0' COMMENT '评论量',
  `count_collect` int(4) NOT  NULL  default '0' COMMENT '收藏量',
  `count_forward` int(4) NOT  NULL  default '0' COMMENT '转发量',
  `count_praise` int(4) NOT  NULL  default '0' COMMENT '点赞量',
  `sort` int(4) NOT NULL DEFAULT '50' COMMENT '排序',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态,1 正常，0 删除',
  `approve_status` int(2) DEFAULT NULL COMMENT '审核状态，1草稿，2提交，3发布',
  `publish_date` datetime DEFAULT NULL COMMENT '发布时间',
  `publish_author` bigint(11) DEFAULT NULL COMMENT '发布者',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `recommend_stat` int(1) DEFAULT NULL COMMENT '推荐类型:0不推荐',
  `show_state` int DEFAULT '1' COMMENT '是否显示:1显示,0 隐藏',
  `content` text COMMENT '内容',
  `content_type` char(1) default '1' COMMENT '内容类型:1图片,2视频，3音频',
  `origin_url` varchar(1024) DEFAULT NULL COMMENT '资源: 图片,视频,音频(多个逗号分割)',
  `image` varchar(256) DEFAULT NULL COMMENT '封面图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='文章内容';

DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '栏目ID',
  `p_id` bigint(11) DEFAULT '0' COMMENT '栏目父级ID',
  `category_name` varchar(50) DEFAULT NULL COMMENT '栏目名称',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `target` varchar(16) DEFAULT NULL COMMENT '跳转类型. 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '栏目描述',
  `sort` int(4) DEFAULT '50' COMMENT '排序',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `show_state` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示栏目:1显示,0 隐藏',
  -- `template` varchar(64) DEFAULT 'product-show' COMMENT '模板页面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  COMMENT='栏目';


DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
 `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 `article_id` bigint(11) DEFAULT NULL,
 `author` bigint(11) DEFAULT NULL,
 `content` varchar(255) DEFAULT '' COMMENT '评论内容',
 `create_date` datetime DEFAULT NULL COMMENT '创建时间',
 `show_state` char(1) NOT NULL DEFAULT '1' COMMENT '是否显示. 1显示,0 隐藏',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='内容评论';



SET FOREIGN_KEY_CHECKS = 1;
