ALTER TABLE `cms_ad`
CHANGE COLUMN `ad_code` `content` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文字广告' AFTER `ad_link`,
MODIFY COLUMN `media_type` tinyint(3) NULL DEFAULT 0 COMMENT '广告类型,0图片;1文字;2代码' AFTER `position_id`;
