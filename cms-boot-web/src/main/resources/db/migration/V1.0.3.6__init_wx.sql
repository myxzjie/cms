SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_account_fans
-- ----------------------------
CREATE TABLE `wx_account_fans` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `subscribe` int DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息',
   `nick_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别，值为1时是男性，值为2时是女性，值为0时是未知',
   `sex` int DEFAULT NULL,
   `language` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家',
   `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
   `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
   `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
   `subscribe_time` int DEFAULT NULL COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
   `union_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
   `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
   `tag_ids` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `subscribe_scene` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `qr_scene` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `qr_scene_str` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `user_id` bigint DEFAULT NULL,
   `create_date` datetime DEFAULT NULL,
   `state` int DEFAULT '1',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信粉丝';

-- ----------------------------
-- Table structure for wx_article
-- ----------------------------
CREATE TABLE `wx_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `digest` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '摘要',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `is_cover` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否展示封面图片（0/1）',
  `thumb_media_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上传微信，封面图片标识',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容链接',
  `sort` int DEFAULT NULL COMMENT '文章排序',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片路径',
  `is_comment` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可以留言',
  `is_fans_comment` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否仅粉丝可以留言',
  `news_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图文ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图文消息文章列表表';

-- ----------------------------
-- Table structure for wx_article_template
-- ----------------------------
CREATE TABLE `wx_article_template` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 主键ID',
    `tpl_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
    `publish` bit(1) DEFAULT b'0' COMMENT '是否已上传微信',
    `media_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图文消息模板表';

-- ----------------------------
-- Table structure for wx_fans_tag
-- ----------------------------
CREATE TABLE `wx_fans_tag` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `fans_id` bigint DEFAULT NULL,
    `tag_id` bigint DEFAULT NULL,
    `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'openId',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信粉丝标签';

-- ----------------------------
-- Table structure for wx_tags
-- ----------------------------
CREATE TABLE `wx_tags` (
   `id` bigint NOT NULL COMMENT 'ID',
   `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
   `count` int DEFAULT '0' COMMENT '此标签下粉丝数',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信标签';

-- ----------------------------
-- Table structure for wx_text_template
-- ----------------------------
CREATE TABLE `wx_text_template` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tpl_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名字',
    `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板内容',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文本模板表';

SET FOREIGN_KEY_CHECKS = 1;