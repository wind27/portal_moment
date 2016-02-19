/*
Navicat MySQL Data Transfer

Source Server         : 本地-192.168.217.128
Source Server Version : 50173
Source Host           : 192.168.217.128:3306
Source Database       : wind

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-02-19 19:07:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `target_id` bigint(20) DEFAULT '0' COMMENT '评论对象ID',
  `target_type` tinyint(255) DEFAULT '1' COMMENT '评论类型：1、此刻；',
  `content` varchar(255) DEFAULT '' COMMENT '评论内容',
  `pid` bigint(20) DEFAULT '0' COMMENT '父ID',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0、删除；1、正常；',
  `create_time` bigint(20) DEFAULT '0' COMMENT '评论时间',
  `operate_type` int(11) DEFAULT '1' COMMENT '操作类型：1、评论；2、回复；3、转发；',
  `target_uid` bigint(20) DEFAULT '0' COMMENT '目标用户',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '这是一个很不错的平台', '0', '1', '1454396232392', '1', '0');

-- ----------------------------
-- Table structure for moment
-- ----------------------------
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text NOT NULL COMMENT '评论',
  `uid` bigint(20) NOT NULL DEFAULT '0' COMMENT '发布人id',
  `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT '0' COMMENT '修改时间',
  `publish_time` bigint(20) DEFAULT '0' COMMENT '发布时间',
  `praise_uid` varchar(255) DEFAULT '' COMMENT '点赞用户列表',
  `collection_uid` varchar(255) DEFAULT '' COMMENT '收藏用户列表',
  `view_num` int(11) DEFAULT '0' COMMENT '浏览数',
  `status` tinyint(255) DEFAULT '1' COMMENT '状态：0、删除；1、正常',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moment
-- ----------------------------
INSERT INTO `moment` VALUES ('1', '创建第一个此刻', '编辑第一个此刻', '1', '1454319055092', '1454396094936', '1454319055092', '[]', '[]', null, '1');

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户uid',
  `target_uid` bigint(20) DEFAULT NULL COMMENT '目标用户uid',
  `type` tinyint(4) DEFAULT '0' COMMENT '0、没有任何关系;1、uid 关注 target_uid;2、target_uid关注uid;3、双向关注；-1、uid 拉黑 target_uid;-2、target_uid拉黑uid;-3、互相拉黑',
  `focus` tinyint(4) DEFAULT '0' COMMENT '0、非特别关注;1、特别关注',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `birth` bigint(20) DEFAULT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `single` tinyint(255) DEFAULT '0' COMMENT '婚否：0、未婚；1、已婚',
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `sex` tinyint(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `status` tinyint(255) DEFAULT '1' COMMENT '状态：0、禁用；1、启用',
  `register_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `last_login_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1001', '钱春', '游风', '2016', '', '1', '18301633814', 'qianchun27@hotmail.com', '0', '1', '18301633814', 'xxxx', '陕西', '咸阳市', '秦都区', '0', '2016', '2016', '2016');
INSERT INTO `user` VALUES ('1002', '张跃雨', 'Hobart', '1455876148152', '', '0', '13512341234', 'zhangyueyu@hotmail.com', '0', '1', '13512341234', 'xxxx', '河南', '濮阳市', 'xx区', '1', '1455876148152', '1455876148152', '1455876148152');
