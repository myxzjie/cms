/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : db_et

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-04-12 22:35:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_account`;
CREATE TABLE `wx_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '微信号ID',
  `site_id` bigint(11) DEFAULT NULL COMMENT '组织ID',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(16) DEFAULT NULL COMMENT '公众帐号名称',
  `code` varchar(64) DEFAULT NULL COMMENT '公众微信号',
  `url` varchar(256) DEFAULT NULL COMMENT 'url',
  `token` varchar(32) DEFAULT NULL COMMENT '公众帐号TOKEN',
  `appid` varchar(64) DEFAULT NULL COMMENT '公众帐号APPID',
  `appsecret` varchar(64) DEFAULT NULL COMMENT '公众帐号APPSECRET',
  `stype` int(2) DEFAULT NULL COMMENT '公众号类型 1服务号，2.订阅号，3企业号',
  `email` varchar(128) DEFAULT NULL COMMENT '公众帐号邮箱',
  `description` varchar(255) DEFAULT NULL COMMENT '公众帐号描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `state` int(2) DEFAULT NULL COMMENT '状态 1 正常 0失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='微信公众号';

-- ----------------------------
-- Records of wx_account
-- ----------------------------
INSERT INTO `wx_account` VALUES ('11', '1', '1', '1', '1', '/wechat/1', '1', '13', '1', '1', '1', '1', '2018-04-12 00:58:16', null, '1');
