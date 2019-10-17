SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `cms_ad` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `position_id` bigint(11) NOT NULL COMMENT '0,站外广告;从1开始代表的是该广告所处的广告位,同表ad_postition中的字段position_id的值',
  `media_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '广告类型,0图片;1flash;2代码3文字',
  `ad_name` varchar(64) NOT NULL COMMENT '广告名称',
  `ad_link` varchar(256) DEFAULT NULL COMMENT '广告链接地址',
  `ad_code` varchar(128) DEFAULT NULL COMMENT '广告链接的表现,文字广告就是文字或图片和flash就是它们的地址',
  `start_time` datetime NOT NULL COMMENT '广告开始时间',
  `end_time` datetime NOT NULL COMMENT '广告结速时间',
  `man` varchar(16) DEFAULT NULL COMMENT '广告联系人',
  `email` varchar(128) DEFAULT NULL COMMENT '广告联系人的邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '广告联系人的电话',
  `click_count` int(4) NOT NULL DEFAULT '0' COMMENT '该广告点击数',
  `enabled` tinyint(1) NOT NULL COMMENT '广告是否关闭;1开启; 0关闭; 关闭后广告将不再有效',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='广告';

INSERT INTO `cms_ad`(`id`, `position_id`, `media_type`, `ad_name`, `ad_link`, `ad_code`, `start_time`, `end_time`, `man`, `email`, `phone`, `click_count`, `enabled`, `create_date`) VALUES (1, 1, 0, 'banner-01', NULL, 'banner_01', '2020-02-26 16:51:39', '2050-02-26 16:51:44', NULL, NULL, NULL, 0, 1, '2020-02-26 16:52:42');

CREATE TABLE `cms_ad_position` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL COMMENT '创建用户ID',
  `position_name` varchar(64) NOT NULL COMMENT '广告位名称',
  `position_code` varchar(64) NOT NULL COMMENT '广告位code',
  `ad_width` smallint(4) NOT NULL COMMENT '广告位宽',
  `ad_height` smallint(4) DEFAULT NULL COMMENT '广告位高',
  `position_model` varchar(256) DEFAULT NULL COMMENT '参数模型',
  `position_desc` varchar(256) DEFAULT NULL COMMENT '广告位说明',
  `position_style` text NOT NULL COMMENT '样例代码',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否启动',
  `theme` varchar(128) DEFAULT NULL COMMENT '样式模板',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='广告位';


INSERT INTO `cms_ad_position`(`id`, `user_id`, `position_name`, `position_code`, `ad_width`, `ad_height`, `position_model`, `position_desc`, `position_style`, `enabled`, `theme`, `create_date`) VALUES (1, 1, '首页大轮播图', 'home_banner', 1920, 516, NULL, NULL, '', 1, NULL, '2020-02-26 16:49:09');


SET FOREIGN_KEY_CHECKS = 1;
