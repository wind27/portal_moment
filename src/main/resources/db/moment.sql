/*
Navicat MySQL Data Transfer

Source Server         : 本地-192.168.217.128
Source Server Version : 50173
Source Host           : 192.168.217.128:3306
Source Database       : wind

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-02-24 18:39:36
*/

SET FOREIGN_KEY_CHECKS=0;

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
