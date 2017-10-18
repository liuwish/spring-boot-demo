/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2017-10-18 17:21:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_term
-- ----------------------------
DROP TABLE IF EXISTS `crm_term`;
CREATE TABLE `crm_term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(2) NOT NULL COMMENT '业务编码',
  `name` varchar(20) NOT NULL COMMENT '学期名称',
  `seq` int(2) NOT NULL COMMENT '排序',
  `create_user_name` varchar(12) NOT NULL COMMENT '创建人ID',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除(0:未删除;1:已删除;)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_term_code` (`code`),
  UNIQUE KEY `index_term_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crm_term
-- ----------------------------
INSERT INTO `crm_term` VALUES ('1', '10', '春', '1', 'system', '0', '2017-08-18 15:26:59');
INSERT INTO `crm_term` VALUES ('2', '20', '暑', '2', 'system', '0', '2017-08-18 15:29:19');
INSERT INTO `crm_term` VALUES ('3', '30', '秋', '3', 'system', '0', '2017-08-18 15:29:49');
INSERT INTO `crm_term` VALUES ('4', '40', '寒', '4', 'system', '0', '2017-08-18 15:30:12');
INSERT INTO `crm_term` VALUES ('5', '99', '春1', '9', 'system', '0', '2017-09-29 18:42:12');
