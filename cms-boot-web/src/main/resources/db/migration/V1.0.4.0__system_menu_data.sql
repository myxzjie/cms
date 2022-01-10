SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `sys_menu`(`id`, `pid`, `name`, `component`, `component_name`, `sort`, `icon`, `path`, `hidden`, `create_date`, `permission`, `type`, `cache`, `redirect`, `state`, `always_show`) VALUES (22, 1, '用户管理', 'system/account', 'account', 50, 'user', '/account', b'0', '2021-11-30 07:11:30', NULL, 1, b'0', NULL, 1, b'0');

INSERT INTO `sys_permission`(`role_id`, `menu_id`) VALUES ( 1, 22);

SET FOREIGN_KEY_CHECKS = 1;