/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : db_et

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-04-12 22:36:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_button
-- ----------------------------
DROP TABLE IF EXISTS `wx_button`;
CREATE TABLE `wx_button` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `p_id` bigint(20) DEFAULT '0' COMMENT '父级ID',
  `name` varchar(16) DEFAULT NULL COMMENT '菜单标题',
  `button_key` varchar(128) DEFAULT NULL COMMENT '菜单KEY值',
  `type` varchar(128) DEFAULT NULL COMMENT '菜单的响应动作类型',
  `url` varchar(1024) DEFAULT NULL COMMENT '网页链接',
  `media_id` varchar(128) DEFAULT NULL COMMENT '调用新增永久素材接口返回的合',
  `orders` int(2) DEFAULT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `site_id` bigint(11) DEFAULT NULL COMMENT '组织ID',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`user_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`user_id`) REFERENCES `sys_account` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_button
-- ----------------------------
INSERT INTO `wx_button` VALUES ('3', '1', '0', '微信公', 'k', 'view', 'http://www.dev56.com/', null, '1', '2016-10-08 00:06:01', '1');
INSERT INTO `wx_button` VALUES ('4', '1', '0', '关于我们', 'key03', 'view', 'http://www.dev56.com/', null, '8', '2016-10-08 00:21:19', '1');
INSERT INTO `wx_button` VALUES ('6', '42', '0', '关于我们', 'key003', 'view', 'http://www.dev56.com/category/2', null, '2', '2016-10-11 23:24:29', '1');
