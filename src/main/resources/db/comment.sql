/*
Navicat MySQL Data Transfer

Source Server         : 本地-192.168.217.128
Source Server Version : 50173
Source Host           : 192.168.217.128:3306
Source Database       : wind

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-02-24 18:39:27
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
