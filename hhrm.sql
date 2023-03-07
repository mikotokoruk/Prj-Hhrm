/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : authority2.0

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 07/02/2023 19:03:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
INSERT INTO `sys_file` VALUES (1, '截屏2022-12-27 20.51.26.png', 'png', 681, 0, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', NULL);
INSERT INTO `sys_file` VALUES (2, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 0, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', NULL);
INSERT INTO `sys_file` VALUES (3, 'IMG_8542.PNG', 'PNG', 109, 0, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', NULL);
INSERT INTO `sys_file` VALUES (4, 'IMG_8542.PNG', 'PNG', 109, 0, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', NULL);
INSERT INTO `sys_file` VALUES (5, '截屏2023-01-25 20.45.38.png', 'png', 60, 0, 'http://localhost:8888/file/264091ed65494c75b52c7055de9cd84a.png', '6c89e4b3135ff25a528166c782f30821', NULL);
INSERT INTO `sys_file` VALUES (6, 'IMG_8542.PNG', 'PNG', 109, 0, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', 1);
INSERT INTO `sys_file` VALUES (7, 'IMG_8542.PNG', 'PNG', 109, 0, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', 1);
INSERT INTO `sys_file` VALUES (8, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (9, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (10, '截屏2022-12-27 20.51.37 (2).png', 'png', 120, 1, 'http://localhost:8888/file/7b9a2dc6f85f4f678573c66b3db3db85.png', 'a9713f4bea8429afc89cbff523ebb9c6', 0);
INSERT INTO `sys_file` VALUES (11, 'im.jpg', 'jpg', 189, 1, 'http://localhost:8888/file/555e5ae36a5a4338a5da82f19e8a5136.jpg', 'd7c8f81f3847758aa94612e8a4edb5c9', 0);
INSERT INTO `sys_file` VALUES (12, 'sys-info.log', 'log', 3, 1, 'http://localhost:8888/file/fa0d06b4160b4bcfad3a2439ce35245f.log', 'b9a5bf0d062ee76b535486230f4b2b85', 0);
INSERT INTO `sys_file` VALUES (13, 'sys-user.log', 'log', 0, 0, 'http://localhost:8888/file/296bdfa3173c42bf8d8e9c48282eeeea.log', 'd9ab6150057ddd2313d0a9c2cf250a64', 1);
INSERT INTO `sys_file` VALUES (14, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (15, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (16, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (17, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (18, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (19, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (20, 'IMG_8542.PNG', 'PNG', 109, 1, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', 0);
INSERT INTO `sys_file` VALUES (21, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (22, 'B3A39514-4AE0-40A5-938B-BC601F311E73_1_105_c.jpeg', 'jpeg', 193, 1, 'http://localhost:8888/file/ac0e89e8778f4d4ea2096c6b874897fe.jpeg', 'e9a3652b858dc2a6f9841ed8614ef3e0', 0);
INSERT INTO `sys_file` VALUES (23, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (24, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (25, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (26, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (27, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (28, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (29, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (30, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (31, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (32, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (33, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (34, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (35, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (36, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (37, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (38, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (39, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (40, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (41, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (42, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (43, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (44, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (45, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (46, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (47, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (48, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (49, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (50, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (51, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (52, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (53, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (54, '截屏2023-01-20 17.53.37.png', 'png', 5038, 1, 'http://localhost:8888/file/0884d8d794d141018a6cac2219d52706.png', '17b2f54e308728d66f5be14644686d20', 0);
INSERT INTO `sys_file` VALUES (55, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (56, '截屏2023-01-20 17.53.37.png', 'png', 5038, 1, 'http://localhost:8888/file/0884d8d794d141018a6cac2219d52706.png', '17b2f54e308728d66f5be14644686d20', 0);
INSERT INTO `sys_file` VALUES (57, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (58, '截屏2023-01-20 17.53.37.png', 'png', 5038, 1, 'http://localhost:8888/file/0884d8d794d141018a6cac2219d52706.png', '17b2f54e308728d66f5be14644686d20', 0);
INSERT INTO `sys_file` VALUES (59, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (60, '截屏2023-01-20 17.53.37.png', 'png', 5038, 1, 'http://localhost:8888/file/0884d8d794d141018a6cac2219d52706.png', '17b2f54e308728d66f5be14644686d20', 0);
INSERT INTO `sys_file` VALUES (61, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (62, '截屏2022-12-21 19.07.59.png', 'png', 1666, 1, 'http://localhost:8888/file/c64291d0eab74239bff11e38d98b8c7a.png', '875602ff851b3a7685380929a6f34ce3', 0);
INSERT INTO `sys_file` VALUES (63, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (64, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (65, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (66, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (67, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (68, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 1);
INSERT INTO `sys_file` VALUES (69, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 1);
INSERT INTO `sys_file` VALUES (70, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 1);
INSERT INTO `sys_file` VALUES (71, 'B3A39514-4AE0-40A5-938B-BC601F311E73_1_105_c.jpeg', 'jpeg', 193, 1, 'http://localhost:8888/file/ac0e89e8778f4d4ea2096c6b874897fe.jpeg', 'e9a3652b858dc2a6f9841ed8614ef3e0', 1);
INSERT INTO `sys_file` VALUES (72, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (73, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (74, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (75, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (76, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (77, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (78, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (79, '截屏2023-01-20 17.53.37.png', 'png', 5038, 1, 'http://localhost:8888/file/0884d8d794d141018a6cac2219d52706.png', '17b2f54e308728d66f5be14644686d20', 0);
INSERT INTO `sys_file` VALUES (80, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (81, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (82, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (83, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (84, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (85, '截屏2022-12-27 20.51.26.png', 'png', 681, 1, 'http://localhost:8085/file/b0f8b991360741c98c41ca13964cad7b.png', '242cea715331ae1218628d0ce0428f85', 0);
INSERT INTO `sys_file` VALUES (86, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (87, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (88, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (89, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (90, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (91, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (92, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (93, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (94, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (95, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (96, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (97, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (98, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (99, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (100, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (101, '截屏2022-12-27 20.51.24.png', 'png', 633, 1, 'http://localhost:8888/file/65dbff2fce444857a205830a4bd0a622.png', '0126338eafac364d6bc9d45b2d63a928', 0);
INSERT INTO `sys_file` VALUES (102, '截屏2023-01-20 19.22.23.png', 'png', 1193, 1, 'http://localhost:8888/file/19945f9568f543139f46ba2270ea5de9.png', 'f71121b3a47b89fb0c00a1b6fc9abe81', 0);
INSERT INTO `sys_file` VALUES (103, '截屏2023-01-21 20.09.02.png', 'png', 754, 1, 'http://localhost:8888/file/eac62ff9d4ab459da9ffbe4af6f2a083.png', 'efdc92150124af1aa043ae5f5f09c863', 0);
INSERT INTO `sys_file` VALUES (104, '截屏2022-12-27 20.51.26 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (105, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (106, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (107, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (108, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (109, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (110, '截屏2023-01-20 19.22.23.png', 'png', 1193, 1, 'http://localhost:8888/file/19945f9568f543139f46ba2270ea5de9.png', 'f71121b3a47b89fb0c00a1b6fc9abe81', 0);
INSERT INTO `sys_file` VALUES (111, '截屏2023-01-21 20.09.02 (2).png', 'png', 927, 1, 'http://localhost:8888/file/d26d0a5702304fb68d250094d131e506.png', 'aefff18682209cc47b89a15e582b20c7', 0);
INSERT INTO `sys_file` VALUES (112, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (113, '截屏2023-01-11 23.37.40.png', 'png', 4251, 1, 'http://localhost:8888/file/5216412986894b859d5a2c4ea7a92250.png', 'fa8718306e9fcc8cfbba5842062d8993', 0);
INSERT INTO `sys_file` VALUES (114, '截屏2023-01-20 19.00.30.png', 'png', 1138, 1, 'http://localhost:8888/file/92ba9aeda16641d2a88924d3693d7b99.png', '6a002b9ce6d0f528c1be5a1d9c596725', 0);
INSERT INTO `sys_file` VALUES (115, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (116, '截屏2022-12-27 20.51.24 (2).png', 'png', 136, 1, 'http://localhost:8888/file/ea3204337ab743cdbe8f4c2dfaa887ce.png', 'ce5c9c8e36ec5a9cce90edee29da03cf', 0);
INSERT INTO `sys_file` VALUES (117, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (118, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (119, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (120, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (121, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (122, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (123, 'IMG_8542.PNG', 'PNG', 109, 1, 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG', '68dfd121173710a0c23b5db942a8b9ec', 0);
INSERT INTO `sys_file` VALUES (124, '截屏2022-12-27 20.51.46.png', 'png', 119, 1, 'http://localhost:8888/file/174813caa3374acc9049b0149bb5257c.png', 'fa8fd80e5aa71ce23fddd9f0c4200fdf', 0);
INSERT INTO `sys_file` VALUES (125, '截屏2023-01-20 18.03.03.png', 'png', 1211, 1, 'http://localhost:8888/file/ddc3d71519ec4b10b6fbfa5d0304fde0.png', '9c2fe98cac2f2766a34313e22dadc4d2', 0);
INSERT INTO `sys_file` VALUES (126, 'B3A39514-4AE0-40A5-938B-BC601F311E73_1_105_c.jpeg', 'jpeg', 193, 1, 'http://localhost:8888/file/ac0e89e8778f4d4ea2096c6b874897fe.jpeg', 'e9a3652b858dc2a6f9841ed8614ef3e0', 0);
INSERT INTO `sys_file` VALUES (127, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (128, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (129, '截屏2023-01-25 20.45.38.png', 'png', 60, 1, 'http://localhost:8888/file/264091ed65494c75b52c7055de9cd84a.png', '6c89e4b3135ff25a528166c782f30821', 0);
INSERT INTO `sys_file` VALUES (130, 'B3A39514-4AE0-40A5-938B-BC601F311E73_1_105_c.jpeg', 'jpeg', 193, 1, 'http://localhost:8888/file/ac0e89e8778f4d4ea2096c6b874897fe.jpeg', 'e9a3652b858dc2a6f9841ed8614ef3e0', 0);
INSERT INTO `sys_file` VALUES (131, '截屏2022-12-27 20.51.37 (2).png', 'png', 120, 1, 'http://localhost:8888/file/7b9a2dc6f85f4f678573c66b3db3db85.png', 'a9713f4bea8429afc89cbff523ebb9c6', 0);
INSERT INTO `sys_file` VALUES (132, '截屏2022-12-23 10.44.29.png', 'png', 866, 1, 'http://localhost:8888/file/f3200674ce6f473cba881cc74a9785f1.png', '992230b04aeb47d019fa71f418e70099', 0);
INSERT INTO `sys_file` VALUES (133, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (134, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (135, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
INSERT INTO `sys_file` VALUES (136, '截屏2023-01-02 15.56.51.png', 'png', 2444, 1, 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', '507a783376b1a02e0b657d38b7e65d08', 0);
INSERT INTO `sys_file` VALUES (137, '新建 Microsoft Office Word 文档.docx', 'docx', 9, 1, 'http://localhost:8888/file/7019372065624ad4969fe6b56b148394.docx', 'e518dfb5f968818c59bcd376265cad3c', 0);
INSERT INTO `sys_file` VALUES (138, '截屏2023-01-11 23.37.33.png', 'png', 826, 1, 'http://localhost:8888/file/a670baa41e3f4ddcb80900a3b08c1df0.png', 'a3bc02e80ef4fe5ba3955d5728aa1418', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父id',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '访问路径',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '组件路径',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '图标',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `statu` bigint(20) DEFAULT '0' COMMENT '当前状态',
  `perms` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `order_num` int(10) DEFAULT '1' COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '', ' ', 'el-icon-menu', '系统管理', '2023-01-22 11:34:15', '2023-01-22 11:34:18', 0, 'system', 999);
INSERT INTO `sys_menu` VALUES (2, 1, '/sys/user', 'sys/User', 'el-icon-menu', '用户管理', '2023-01-23 13:13:45', '2023-01-23 13:13:49', 0, 'user', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '/sys/role', 'sys/Role', 'el-icon-menu', '角色管理', '2023-01-23 13:13:52', '2023-01-23 13:13:55', 0, 'role', 1);
INSERT INTO `sys_menu` VALUES (4, 1, '/sys/menu', 'sys/Menu', 'el-icon-menu', '菜单管理', '2023-01-23 13:13:57', '2023-01-23 13:14:00', 0, 'menu', 1);
INSERT INTO `sys_menu` VALUES (46, 1, '/sys/file', 'sys/File', 'el-icon-document-copy', '文件管理', '2023-01-25 23:25:04', NULL, 0, 'file', 1);
INSERT INTO `sys_menu` VALUES (47, 0, '', '', 'el-icon-menu', '系统工具', NULL, NULL, 0, 'tool', 999);
INSERT INTO `sys_menu` VALUES (48, 47, '/sys/generator', 'sys/Generator', 'el-icon-upload2', '代码生成', NULL, NULL, 0, 'generator', 1);
INSERT INTO `sys_menu` VALUES (113, 0, '/index', 'sys/Index', 'el-icon-star-on', '系统主页', '2023-02-06 17:14:00', NULL, 0, 'index', 1);
INSERT INTO `sys_menu` VALUES (152, 0, '/student', 'Student', 'el-icon-s-operation', '学生管理', '2023-02-07 18:44:46', NULL, 0, 'student', 999);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `perms` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `statu` int(10) DEFAULT '0' COMMENT '当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有全部权限', 'admin', '2023-01-22 11:40:31', '2023-01-31 20:10:53', 0);
INSERT INTO `sys_role` VALUES (6, '普通用户', '拥有部分权限', 'user', '2023-02-01 17:32:11', '2023-01-31 20:22:54', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (341, 6, 113);
INSERT INTO `sys_role_menu` VALUES (376, 1, 113);
INSERT INTO `sys_role_menu` VALUES (377, 1, 1);
INSERT INTO `sys_role_menu` VALUES (378, 1, 2);
INSERT INTO `sys_role_menu` VALUES (379, 1, 3);
INSERT INTO `sys_role_menu` VALUES (380, 1, 4);
INSERT INTO `sys_role_menu` VALUES (381, 1, 46);
INSERT INTO `sys_role_menu` VALUES (382, 1, 47);
INSERT INTO `sys_role_menu` VALUES (383, 1, 48);
INSERT INTO `sys_role_menu` VALUES (384, 1, 147);
COMMIT;

-- ----------------------------
-- Table structure for sys_student
-- ----------------------------
DROP TABLE IF EXISTS `sys_student`;
CREATE TABLE `sys_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学生管理';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `statu` bigint(20) DEFAULT '0' COMMENT '当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$NGDvki6BA91qwZbtwXxINezkJt.BYjIv4YWHfTeaWBXr94BGwGCYK', '曹坤', 'http://localhost:8888/file/1374eeef5af64a50a52289a0d7135e9a.png', NULL, '2023-02-07 14:32:53', 0);
INSERT INTO `sys_user` VALUES (2, 'L7H', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '崔明辉', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'iH', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '韩擎苍', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (4, 'Nwr', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '熊志泽', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (5, 'RM', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '戴昊强', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'nMar', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '沈耀杰', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (7, 'TKnhD', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '石果', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (8, 'dGK', '$2a$10$AzKghvTbwiV8rfmKm6SspeNx4lw4zcgyjy18Si7SHdQGPzxkBJtni', '段昊天', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (101, '1234', '$2a$10$j15RzenRH8v8Au0pW7saWO1w2NHhoJOhdcEsFiu1uwAaFoFv5aPbi', '12345', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (102, '12345', '$2a$10$q1/J.6VJovP3sTu82Do2a.SEiH8jXaoIa8slGmL67uGtRAHRcIJZO', '234', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (103, '2345', '$2a$10$Qh/QjVeXx7SLwYen.ATpDeIvuU6ieFisXRttl67YXoTNeRvFUZtr6', '234534', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (104, '2345', '$2a$10$AP9Td8JjrkDmt5X9Jq2Rje27bElf88g.bM.pkTN15T96k38Zsj9om', '32423', 'http://localhost:8888/file/admin.jepg', '2023-01-25 15:44:31', '2023-01-25 15:45:50', 0);
INSERT INTO `sys_user` VALUES (105, '4567', '$2a$10$6/u.VtluAP9.jG30S7VIlOgmECveixgkiWxv855/C5Q5QYTces55G', '34567', 'http://localhost:8888/file/admin.jepg', '2023-01-25 22:43:08', NULL, 0);
INSERT INTO `sys_user` VALUES (106, '345', '$2a$10$Ja9ogcZlrcJjsUF27/Z8Ouk2iWFY/UEK4mGBg80.swJeZcs542kNi', '23456345', 'http://localhost:8888/file/admin.jepg', '2023-01-25 22:47:50', NULL, 0);
INSERT INTO `sys_user` VALUES (107, '4567', '$2a$10$5h3AIeCtgH7CjLG5MyuPUuq7Ztppb/G/bKJnTiGbzG4TxkBMKAdLG', '4567', 'http://localhost:8888/file/admin.jepg', '2023-01-25 22:51:23', NULL, 0);
INSERT INTO `sys_user` VALUES (108, 'rety', '$2a$10$SbYAbtFsSKB.ar9DejMsD.NktUw2Q3g88VP1.7cTfH6ip7vssG0.K', 'retyurty', 'http://localhost:8888/file/admin.jepg', '2023-01-25 23:08:43', NULL, 0);
INSERT INTO `sys_user` VALUES (109, 'rety', '$2a$10$jrGOxS66KNN9nKJN7OS1P.lG8HMGPLqbiRMbIOaDhEC8z3DJWNfUW', 'retyurty', 'http://localhost:8888/file/admin.jepg', '2023-01-25 23:08:43', NULL, 0);
INSERT INTO `sys_user` VALUES (110, '32456', '$2a$10$RTd1luaw8x6ByG1iV4NMKu0DKklDow04sKANJ1pPTkX7Njb8claF2', '324567', 'http://localhost:8888/file/admin.jepg', '2023-01-25 23:09:33', NULL, 0);
INSERT INTO `sys_user` VALUES (111, '2345', '$2a$10$VrQ8098hgzr6drNgF6/qLOiK3zB97V2VyG6qJO.weJ7mXjDVM96tG', '1234567', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:05:39', NULL, 0);
INSERT INTO `sys_user` VALUES (112, '23456', '$2a$10$dRgdCf0TVChKI0SjhUgsauDiL76AFhkvuguXMdGpvZbSfsDsDQDPG', '23456', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:05:48', NULL, 0);
INSERT INTO `sys_user` VALUES (113, '123432456', '$2a$10$pfL4/psTYJs09EaSyjwC5..IkzJoMlIeofXeJB16R.mXB8isREj7.', '23456324', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:09:33', '2023-01-26 17:10:08', 0);
INSERT INTO `sys_user` VALUES (114, 'dwdweadeas', '$2a$10$6j4MK9/ytfrVZQ82wFFCeuWllrpmW/my9uL9.5z9wIdZv3QT5CmSm', 'easdes', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:10:37', '2023-01-31 20:41:37', 0);
INSERT INTO `sys_user` VALUES (115, '324567', '$2a$10$6GK16FFIEnKXvH5xyOZJxey5RrWo4FO3ZykcTdhvhbuBRo4fGXS/u', '43567456', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:12:47', NULL, 0);
INSERT INTO `sys_user` VALUES (116, '324567', '$2a$10$dqXHNoO6FO9.srhwe8IeU.afc5UGM0J3SoFwnMEMutVA29y1PBv1S', '43567456', 'http://localhost:8888/file/admin.jepg', '2023-01-26 17:12:47', NULL, 0);
INSERT INTO `sys_user` VALUES (117, 'dyy', '$2a$10$OAvtheW0EhF6u.iZZ5TLxO3.nhl.H0efI0Ge9d85G8Scs8TUAWtzq', '张三', 'http://localhost:8888/file/admin.jepg', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (118, 'admin1', '$2a$10$tcwDJtU/U6uRR18duAToCuW7NBTmRaBdvrzYAuIxxeDpkFDAsglbi', 'admin', 'http://localhost:8888/file/admin.jepg', '2023-02-06 21:39:25', NULL, 0);
INSERT INTO `sys_user` VALUES (119, 'admin2', '$2a$10$7dVy6N1/gcNvQsIH5Y6kvOMZur8yBnIFRM8PriLnFnSAAsD3pIZ5a', '张二狗', 'http://localhost:8888/file/admin.jepg', '2023-02-06 21:45:26', NULL, 0);
INSERT INTO `sys_user` VALUES (120, '测试数据', NULL, '测试', NULL, '2023-02-07 10:34:56', NULL, 0);
INSERT INTO `sys_user` VALUES (121, 'admin1234', '12345', '张杰', 'http://localhost:8888/file/admin.jepg', '2023-02-07 10:56:59', NULL, 0);
INSERT INTO `sys_user` VALUES (122, 'admin12345', '12345', '曹坤', 'http://localhost:8888/file/admin.jepg', '2023-02-07 11:00:37', '2023-02-07 11:00:37', 0);
INSERT INTO `sys_user` VALUES (123, 'ce ee', '12345', '张三', 'http://localhost:8888/file/admin.jepg', '2023-02-07 11:01:15', '2023-02-07 11:01:38', 0);
INSERT INTO `sys_user` VALUES (124, '测试睡觉', '12345', '张三', 'http://localhost:8888/file/admin.jepg', '2023-02-07 11:03:40', '2023-02-07 11:03:40', 0);
INSERT INTO `sys_user` VALUES (125, 'ADMIN123', '12345', '测试', 'http://localhost:8888/file/admin.jepg', '2023-02-07 12:40:32', '2023-02-07 12:46:06', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (3, 2, 6);
INSERT INTO `sys_user_role` VALUES (6, 103, 1);
INSERT INTO `sys_user_role` VALUES (7, 103, 6);
INSERT INTO `sys_user_role` VALUES (8, 117, 6);
INSERT INTO `sys_user_role` VALUES (9, 119, 6);
INSERT INTO `sys_user_role` VALUES (10, 125, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
