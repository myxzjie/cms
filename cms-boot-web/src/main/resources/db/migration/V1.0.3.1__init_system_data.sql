SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES (1, 'admin', NULL, NULL, NULL, '$2a$10$D.bKp2MJBeh.6Yob6i1ezePF9wphPMfgAMFgrNPYuM7By.u4LwoS2', NULL, 1, '2019-12-20 03:17:38', NULL, NULL, '1', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'administrator', '超级管理员', 1, '2', 1, '2016-07-23 11:15:36', 1, 1, 12);
INSERT INTO `sys_role` VALUES (2, 'admin', '管理员', 1, '', 1, '2016-07-24 00:19:53', 1, 1, 1);
INSERT INTO `sys_role` VALUES (3, 'member', '普通会员', 4, '', 1, '2016-09-23 17:33:00', 1, 1, 2);
INSERT INTO `sys_role` VALUES (4, 'user', '普通用户', 1, '', 0, '2016-09-23 18:06:17', 1, 1, 1);

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES (1, 1, 1);
INSERT INTO `sys_account_role` VALUES (2, 2, 1);
INSERT INTO `sys_account_role` VALUES (3, 4, 1);

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'Layout', 'system', 99, 'system', '/system', b'0', '2018-12-18 15:11:29', NULL, 1, b'0', '/system/job', 1, b'0');
INSERT INTO `sys_menu` VALUES (2, 1, '定时任务', 'system/job', 'job', 99, 'timing', 'job', b'0', '2018-12-18 15:11:29', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (3, 0, '内容管理', 'Layout', 'article', 99, 'list', '/article', b'0', '2020-03-25 18:28:03', NULL, 1, b'0', '/article/list', 1, b'0');
INSERT INTO `sys_menu` VALUES (4, 3, '文章列表', 'article/article', 'article-list', 99, '', 'list', b'0', '2020-03-25 18:30:36', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (5, 3, '分类列表', 'article/category', 'article-category', 99, '', 'category', b'0', '2020-03-25 18:30:40', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (6, 1, '菜单管理', 'system/menu', 'menu', 99, 'menu', 'menu', b'0', '2020-03-26 00:24:30', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (7, 9, '公众号管理', 'wechat/setting', 'wechat-setting', 50, '', 'setting', b'0', '2020-03-26 09:20:53', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (8, 1, '角色管理', 'permission/role', 'permission', 50, 'role', '/permission/role', b'0', '2020-03-26 16:17:53', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (9, 0, '微信管理', 'Layout', 'wechat', 99, 'wechat', '/wechat', b'0', '2020-03-25 18:28:03', NULL, 1, b'0', '/wechat/setting', 1, b'0');
INSERT INTO `sys_menu` VALUES (10, 9, '图文管理', 'wechat/material', 'wechat-material', 50, '', 'material', b'0', '2020-05-15 18:42:33', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (11, 9, '图文编辑', 'wechat/material/components/article-form', 'wechat-article', 50, '', 'material/article-form/:id(\\d+)', b'1', '2020-05-18 15:59:56', NULL, 1, b'0', '', 1, b'0');
INSERT INTO `sys_menu` VALUES (12, 9, '粉丝用户', 'wechat/account-fans', 'wechat-account-fans', 50, '', 'account-fans', b'1', '2020-06-03 09:09:22', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (13, 9, '标签管理', 'wechat/tags', 'wechat-tags', 50, '', 'tags', b'0', '2020-06-04 23:10:23', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (14, 1, '导航栏管理', 'system/navigation', 'navigation', 50, 'list', '/navigation', b'0', '2020-07-07 10:38:06', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (15, 0, '广告管理', 'Layout', 'ad', 50, 'component', '/ad', b'0', '2020-07-15 17:43:21', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (16, 15, '广告位管理', 'ad/position', 'ad-position', 50, '', 'position', b'0', '2020-07-15 17:45:16', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (17, 15, '广告列表', 'ad', 'ad-list', 50, '', 'index', b'0', '2020-07-15 17:46:54', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (18, 0, '接口文档', 'Layout', 'swagger', 50, 'documentation', '/swagger', b'0', '2020-07-22 09:45:05', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (19, 18, 'API文档', 'swagger/index', 'documentation', 50, '', 'index', b'0', '2020-07-22 10:49:40', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (20, 0, '图标管理', 'Layout', 'mrg-icons', 50, 'icon', '/icons', b'0', '2020-07-22 11:11:08', NULL, 1, b'0', NULL, 1, b'0');
INSERT INTO `sys_menu` VALUES (21, 20, '图标', 'icons/index', 'icons', 50, '', 'index', b'0', '2020-07-22 11:12:08', NULL, 1, b'0', NULL, 1, b'0');

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 2, 3);
INSERT INTO `sys_permission` VALUES (2, 2, 4);
INSERT INTO `sys_permission` VALUES (3, 2, 5);
INSERT INTO `sys_permission` VALUES (4, 3, 1);
INSERT INTO `sys_permission` VALUES (5, 3, 2);
INSERT INTO `sys_permission` VALUES (6, 1, 1);
INSERT INTO `sys_permission` VALUES (7, 1, 2);
INSERT INTO `sys_permission` VALUES (8, 1, 6);
INSERT INTO `sys_permission` VALUES (9, 1, 8);
INSERT INTO `sys_permission` VALUES (10, 1, 14);
INSERT INTO `sys_permission` VALUES (11, 1, 3);
INSERT INTO `sys_permission` VALUES (12, 1, 4);
INSERT INTO `sys_permission` VALUES (13, 1, 5);
INSERT INTO `sys_permission` VALUES (14, 1, 9);
INSERT INTO `sys_permission` VALUES (15, 1, 7);
INSERT INTO `sys_permission` VALUES (16, 1, 10);
INSERT INTO `sys_permission` VALUES (17, 1, 11);
INSERT INTO `sys_permission` VALUES (18, 1, 12);
INSERT INTO `sys_permission` VALUES (19, 1, 13);
INSERT INTO `sys_permission` VALUES (20, 1, 15);
INSERT INTO `sys_permission` VALUES (21, 1, 16);
INSERT INTO `sys_permission` VALUES (22, 1, 17);
INSERT INTO `sys_permission` VALUES (23, 1, 18);
INSERT INTO `sys_permission` VALUES (24, 1, 19);
INSERT INTO `sys_permission` VALUES (25, 1, 20);
INSERT INTO `sys_permission` VALUES (26, 1, 21);

INSERT INTO `sys_key_data`(`key`, `data`, `create_date`, `update_date`) VALUES ('wechat_menus', '[{\"key\": \"wx_demo\", \"name\": \"公众号演示\", \"type\": \"click\", \"subButtons\": [{\"url\": \"http://www.dev56.com\", \"name\": \"鹰视商城\", \"type\": \"view\"}, {\"url\": \"https://www.sutuiquan.club\", \"name\": \"速推圈\", \"type\": \"view\"}]}, {\"key\": \"xc_demo\", \"name\": \"小程序演示\", \"type\": \"click\", \"subButtons\": [{\"url\": \"pages/home/main\", \"name\": \"鹰视商城\", \"type\": \"miniprogram\", \"appId\": \"wxe1900dc6d5245c65\", \"pagePath\": \"pages/home/main\"}]}]', '2020-05-14 10:00:48', '2020-05-14 13:49:52');
INSERT INTO `sys_key_data`(`key`, `data`, `create_date`, `update_date`) VALUES ('wechat_setting', '{\"api\": \"http://[域名]/api/wechat/serve\", \"imageArr\": [], \"wechat_id\": \"\", \"wechat_name\": \"\", \"wechat_type\": \"\", \"wechat_appid\": \"\", \"wechat_token\": \"d\", \"wechat_avatar\": \"\", \"wechat_encode\": \"\", \"wechat_qrcode\": \"\", \"wechat_sourceid\": \"\", \"wechat_appsecret\": \"d\", \"wechat_share_img\": \"\", \"wechat_share_title\": \"\", \"wechat_encodingaeskey\": \"\", \"wechat_share_synopsis\": \"\"}', '2020-05-14 06:05:22', '2020-05-14 06:05:22');

SET FOREIGN_KEY_CHECKS = 1;