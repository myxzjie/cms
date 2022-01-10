SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `sys_menu`(`id`, `pid`, `name`, `component`, `component_name`, `sort`, `icon`, `path`, `hidden`, `create_date`, `permission`, `type`, `cache`, `redirect`, `state`, `always_show`) VALUES (23, 3, '热门文章', 'article/hot', 'article-hot', 50, NULL, 'article-hot', b'0', '2022-01-10 07:51:45', NULL, 1, b'0', NULL, 1, NULL);
INSERT INTO `sys_menu`(`id`, `pid`, `name`, `component`, `component_name`, `sort`, `icon`, `path`, `hidden`, `create_date`, `permission`, `type`, `cache`, `redirect`, `state`, `always_show`) VALUES (24, 3, '推荐文章', 'article/recommend-stat', 'recommend-stat', 50, NULL, 'recommend-stat', b'0', '2022-01-10 16:35:48', NULL, 1, b'0', NULL, 1, NULL);

INSERT INTO `sys_permission`(`role_id`, `menu_id`) VALUES (1, 23);
INSERT INTO `sys_permission`(`role_id`, `menu_id`) VALUES (1, 24);

UPDATE `sys_menu` SET `pid` = 3, `name` = '文章列表', `component` = 'article', `component_name` = 'article-list', `sort` = 99, `icon` = '', `path` = 'list', `hidden` = b'0', `create_date` = '2020-03-25 18:30:36', `permission` = NULL, `type` = 1, `cache` = b'0', `redirect` = NULL, `state` = 1, `always_show` = b'0' WHERE `id` = 4;

SET FOREIGN_KEY_CHECKS = 1;