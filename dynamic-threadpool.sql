/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : dynamic-threadpool

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-06-16 14:22:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(255) DEFAULT NULL,
  `item_id` varchar(255) DEFAULT NULL,
  `tp_id` varchar(255) DEFAULT NULL,
  `tp_name` varchar(255) DEFAULT NULL,
  `core_size` int(6) DEFAULT NULL,
  `max_size` int(6) DEFAULT NULL,
  `queue_type` int(6) DEFAULT NULL,
  `capacity` int(6) DEFAULT NULL,
  `rejected_type` varchar(255) DEFAULT NULL,
  `keep_alive_time` int(6) DEFAULT NULL,
  `allow_core_thread_time_out` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `is_alarm` int(6) DEFAULT NULL,
  `capacity_alarm` int(6) DEFAULT NULL,
  `liveness_alarm` int(6) DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除',
  `desc` varchar(255) DEFAULT NULL COMMENT '简介',
  `gmt_create` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES ('4', 'common', 'message-center', 'message-consume', null, '3', '10', '9', '1000', null, '1000', null, '{\"capacity\":1000,\"capacityAlarm\":80,\"coreSize\":3,\"isAlarm\":2,\"itemId\":\"message-center\",\"keepAliveTime\":1000,\"livenessAlarm\":80,\"maxSize\":10,\"namespace\":\"common\",\"queueType\":9,\"tpId\":\"message-consume\"}', '2f04ac1c703749e25660b852d4061cd8', '2', '80', '80', '0', null, '2022-06-15 15:41:29.496000', '2022-06-15 15:41:29.500000');
INSERT INTO `config_info` VALUES ('5', 'common', 'message-center', 'message-produce', null, '3', '10', '9', '1000', null, '1000', null, '{\"capacity\":1000,\"capacityAlarm\":80,\"coreSize\":3,\"isAlarm\":2,\"itemId\":\"message-center\",\"keepAliveTime\":1000,\"livenessAlarm\":80,\"maxSize\":10,\"namespace\":\"common\",\"queueType\":9,\"tpId\":\"message-produce\"}', '5e3109344a427f45720800c2f3e697bd', '2', '80', '80', '0', null, '2022-06-15 15:43:08.689000', '2022-06-15 15:43:08.689000');

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `item_id` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `item_desc` varchar(255) DEFAULT NULL COMMENT '项目简介',
  `owner` varchar(255) DEFAULT NULL COMMENT '负责人',
  `gmt_create` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(6) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES ('1', 'prescription', 'dynamic-threadpool-example', '动态线程池示例项目', '动态线程池示例项目，对应 Horse 项目的 example 模块', '马', '2022-06-15 22:11:00.000000', '2022-06-15 23:05:35.000000', '0');

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `tenant_name` varchar(255) DEFAULT NULL COMMENT '租户名称',
  `tenant_desc` varchar(255) DEFAULT NULL COMMENT '租户简介',
  `owner` varchar(255) DEFAULT NULL COMMENT '负责人',
  `gmt_create` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(6) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES ('1', 'prescription', '处方组', '负责维护处方服务, 包括不限于电子处方等业务', '韫', '2022-06-15 23:06:33.000000', '2022-06-15 23:06:39.000000', '0');
