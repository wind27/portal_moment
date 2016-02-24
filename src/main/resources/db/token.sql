/*
Navicat MySQL Data Transfer

Source Server         : 本地-192.168.217.128
Source Server Version : 50173
Source Host           : 192.168.217.128:3306
Source Database       : wind

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-02-24 18:39:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户uid',
  `login_name` varchar(255) DEFAULT '' COMMENT '登录用户名',
  `app_token` varchar(255) DEFAULT '' COMMENT 'app 登录 token',
  `pc_token` varchar(255) DEFAULT NULL COMMENT 'pc 登录 token',
  `login_type` tinyint(4) DEFAULT '1' COMMENT '登录用户类型：1、email；',
  `time` bigint(20) DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('1', '1001', 'qianchun27@hotmail.com', '2222', '1111', '1', '1455868540488');
