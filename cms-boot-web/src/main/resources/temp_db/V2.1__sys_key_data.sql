SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_key_data`;
CREATE TABLE `sys_key_data` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `key` varchar(64) NOT NULL unique COMMENT 'key',
    `data` json NULL COMMENT 'data',
    `create_date` datetime NULL COMMENT '创建时间',
    `update_date` datetime NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='key json data';

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `sys_key_data`(`key`, `data`, `create_date`, `update_date`) VALUES ('wechat_menus', '[{\"key\": \"wx_demo\", \"name\": \"公众号演示\", \"type\": \"click\", \"subButtons\": [{\"url\": \"http://www.dev56.com\", \"name\": \"鹰视商城\", \"type\": \"view\"}, {\"url\": \"https://www.sutuiquan.club\", \"name\": \"速推圈\", \"type\": \"view\"}]}, {\"key\": \"xc_demo\", \"name\": \"小程序演示\", \"type\": \"click\", \"subButtons\": [{\"url\": \"pages/home/main\", \"name\": \"鹰视商城\", \"type\": \"miniprogram\", \"appId\": \"wxe1900dc6d5245c65\", \"pagePath\": \"pages/home/main\"}]}]', '2020-05-14 10:00:48', '2020-05-14 13:49:52');
INSERT INTO `sys_key_data`(`key`, `data`, `create_date`, `update_date`) VALUES ('wechat_setting', '{\"api\": \"http://[域名]/api/wechat/serve\", \"imageArr\": [], \"wechat_id\": \"\", \"wechat_name\": \"\", \"wechat_type\": \"\", \"wechat_appid\": \"\", \"wechat_token\": \"d\", \"wechat_avatar\": \"\", \"wechat_encode\": \"\", \"wechat_qrcode\": \"\", \"wechat_sourceid\": \"\", \"wechat_appsecret\": \"d\", \"wechat_share_img\": \"\", \"wechat_share_title\": \"\", \"wechat_encodingaeskey\": \"\", \"wechat_share_synopsis\": \"\"}', '2020-05-14 06:05:22', '2020-05-14 06:05:22');
