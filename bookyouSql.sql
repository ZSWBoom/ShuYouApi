/*
 Navicat MySQL Data Transfer

 Source Server         : zhushaowei
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : bookyou

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 03/03/2020 12:32:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply_record_table
-- ----------------------------
DROP TABLE IF EXISTS `apply_record_table`;
CREATE TABLE `apply_record_table`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bookid` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '0 待审核 1 已审核 2取消',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handletime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `recordbook`(`bookid`) USING BTREE,
  INDEX `recorduser`(`create_user`) USING BTREE,
  CONSTRAINT `recordbook` FOREIGN KEY (`bookid`) REFERENCES `bookdata` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `recorduser` FOREIGN KEY (`create_user`) REFERENCES `usertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of apply_record_table
-- ----------------------------
INSERT INTO `apply_record_table` VALUES (8, 6, 1, 1, '2020-02-01 20:46:59', '2020-02-01 20:48:41');
INSERT INTO `apply_record_table` VALUES (9, 6, 1, 1, '2020-02-01 22:31:17', '2020-02-01 22:35:58');
INSERT INTO `apply_record_table` VALUES (10, 6, 1, 1, '2020-02-01 23:02:05', '2020-02-01 23:40:59');
INSERT INTO `apply_record_table` VALUES (11, 7, 1, 1, '2020-02-01 23:02:11', '2020-02-01 23:42:55');
INSERT INTO `apply_record_table` VALUES (12, 8, 3, 1, '2020-02-01 23:02:20', '2020-02-01 23:03:35');
INSERT INTO `apply_record_table` VALUES (13, 8, 1, 1, '2020-02-01 23:02:24', '2020-02-01 23:07:44');
INSERT INTO `apply_record_table` VALUES (14, 1, 3, 0, '2020-02-01 23:31:12', NULL);
INSERT INTO `apply_record_table` VALUES (15, 6, 3, 1, '2020-02-01 23:31:16', '2020-02-01 23:47:20');
INSERT INTO `apply_record_table` VALUES (16, 7, 3, 0, '2020-02-01 23:31:33', NULL);
INSERT INTO `apply_record_table` VALUES (17, 3, 3, 0, '2020-02-01 23:32:18', NULL);

-- ----------------------------
-- Table structure for bookcomment
-- ----------------------------
DROP TABLE IF EXISTS `bookcomment`;
CREATE TABLE `bookcomment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bookid`(`bookid`) USING BTREE,
  INDEX `userid`(`userId`) USING BTREE,
  CONSTRAINT `bookid` FOREIGN KEY (`bookid`) REFERENCES `bookdata` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `usertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bookcomment
-- ----------------------------
INSERT INTO `bookcomment` VALUES (1, 1, 1, '好书', '2019-12-23 23:15:29', '10');
INSERT INTO `bookcomment` VALUES (2, 1, 1, '好书好书好书', '2019-12-23 23:15:55', '8');

-- ----------------------------
-- Table structure for bookdata
-- ----------------------------
DROP TABLE IF EXISTS `bookdata`;
CREATE TABLE `bookdata`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` int(11) NULL DEFAULT NULL,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `createDate` datetime NULL DEFAULT NULL,
  `bookAuthor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `holder` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `owner`(`owner`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `holder`(`holder`) USING BTREE,
  CONSTRAINT `holder` FOREIGN KEY (`holder`) REFERENCES `usertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `owner` FOREIGN KEY (`owner`) REFERENCES `usertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `booktype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bookdata
-- ----------------------------
INSERT INTO `bookdata` VALUES (1, 1, '《Kotlin开发艺术探索》', 1, '简介', '1', '', '50.00', 0, '2019-12-11 23:52:34', '未知', '1', 1);
INSERT INTO `bookdata` VALUES (2, 1, '《Kotlin开发艺术探索》', 1, '简介', '1', '', '50.00', 0, '2019-12-11 23:52:34', '未知', '1', 1);
INSERT INTO `bookdata` VALUES (3, 1, '《Flutter实战》', 4, 'flutter牛逼', '2', '', '40.00', 0, '2019-12-23 22:19:12', '未知', '1', 1);
INSERT INTO `bookdata` VALUES (4, 3, '《Java编程思想》', 4, 'java', '33', NULL, '50.00', 0, '2019-12-24 00:08:09', '未知', '1', 3);
INSERT INTO `bookdata` VALUES (5, 3, '《Java编程思想》', 4, 'java', '33', NULL, '50.00', 0, '2019-12-24 00:08:09', '未知', '1', 3);
INSERT INTO `bookdata` VALUES (6, 1, '《Flutter》', 1, 'test', '1', 'http://localhost:8080/file/e7720f17-4f4e-4051-8ce3-71a9974eb8d9.jpg', '0', 1, '2019-12-11 23:52:34', '朱少威', '0', 3);
INSERT INTO `bookdata` VALUES (7, 4, '《Flutter》', 1, 'test', '1', 'http://localhost:8080/file/c71aee4d-2af8-4f81-a8f3-235a8704e698.jpg', '100', 1, '2020-01-01 15:44:56', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (8, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/8db07b92-8fe1-4ab5-b128-9194cf4fe360.jpg', '100', 0, '2020-01-01 16:30:38', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (9, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/58a7fc8a-5309-46ae-9591-658f22fd4a6d.jpg', '100', 0, '2020-01-01 16:33:31', '朱少威', '0', 4);
INSERT INTO `bookdata` VALUES (10, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/dd99c78b-fc4b-4ab8-98fe-1ba84c09ee2e.jpg', '100', 0, '2020-01-01 16:34:00', '朱少威', '0', 4);
INSERT INTO `bookdata` VALUES (11, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/b4b1b0c7-9104-44be-8dc8-65eda440c326.jpg', '100', 0, '2020-01-01 16:34:10', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (14, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/78fc84e6-2139-42cf-a34f-5597b453c7d1.jpg', '100', 0, '2020-01-01 16:34:54', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (15, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/ba6558e5-f5d5-4208-94eb-b3ea609825c0.jpg', '100', 0, '2020-01-01 16:35:58', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (16, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/61c85a9c-37b2-462d-9ce9-74cfaa26a10a.jpg', '100', 0, '2020-01-01 16:36:24', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (17, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/4637d97a-4f4f-49c9-838f-f184369bfe76.jpg', '100', 0, '2020-01-01 16:36:53', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (18, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/4d631ca2-6674-445b-9123-fe55ea2479f6.jpg', '100', 0, '2020-01-01 16:39:10', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (19, 1, '《Flutter2》', 1, 'test', '1', 'http://47.93.58.173:8080/file/41c6fd13-71ab-41f2-8feb-7b59e6061aef.jpg', '100', 0, '2020-01-01 16:39:32', '朱少威', '0', 1);
INSERT INTO `bookdata` VALUES (20, 4, '《Flutter3》', 1, 'test4444', '1', 'http://47.93.58.173:8080/file/f95d26f4-281f-463f-b94b-48854ef83cc0.jpg', '100', 0, '2020-02-01 23:20:31', '朱少威', '0', 4);

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `id` int(11) NOT NULL,
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES (1, '技术');
INSERT INTO `booktype` VALUES (2, '科幻');
INSERT INTO `booktype` VALUES (3, '管理');
INSERT INTO `booktype` VALUES (4, '工具');

-- ----------------------------
-- Table structure for borrow_table
-- ----------------------------
DROP TABLE IF EXISTS `borrow_table`;
CREATE TABLE `borrow_table`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NULL DEFAULT NULL,
  `bookid` int(11) NULL DEFAULT NULL,
  `borrow_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expect_return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '0正在被借阅 1已归还',
  `renew_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`userid`) USING BTREE,
  INDEX `book`(`bookid`) USING BTREE,
  CONSTRAINT `book` FOREIGN KEY (`bookid`) REFERENCES `bookdata` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`userid`) REFERENCES `usertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of borrow_table
-- ----------------------------
INSERT INTO `borrow_table` VALUES (1, 1, 2, '2020-02-01 20:40:23', '2020-02-16 20:40:23', '2020-02-01 22:39:17', 1, 0);
INSERT INTO `borrow_table` VALUES (2, 1, 6, '2020-02-01 20:48:41', '2020-02-01 21:48:41', '2020-02-01 21:41:34', 1, 0);
INSERT INTO `borrow_table` VALUES (3, 1, 6, '2020-02-01 22:35:58', '2020-02-16 22:35:58', '2020-02-01 23:12:13', 1, 0);
INSERT INTO `borrow_table` VALUES (4, 3, 8, '2020-02-01 23:03:35', '2020-02-16 23:03:35', '2020-02-01 23:06:49', 1, 0);
INSERT INTO `borrow_table` VALUES (5, 1, 8, '2020-02-01 23:07:44', '2020-02-16 23:07:44', '2020-02-01 23:12:46', 1, 0);
INSERT INTO `borrow_table` VALUES (6, 1, 6, '2020-02-01 23:40:59', '2020-02-16 23:40:59', '2020-02-01 23:45:27', 1, 0);
INSERT INTO `borrow_table` VALUES (7, 1, 7, '2020-02-01 23:42:55', '2020-02-16 23:42:55', NULL, 0, 0);
INSERT INTO `borrow_table` VALUES (8, 3, 6, '2020-02-01 23:47:20', '2020-03-17 23:47:20', NULL, 0, 1);

-- ----------------------------
-- Table structure for usertable
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `points` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO `usertable` VALUES (1, 'zhushaowei@myhexin.com', '朱少威', '202CB962AC59075B964B07152D234B70', NULL, '36', '18438610363');
INSERT INTO `usertable` VALUES (3, 'zhushaowei1@myhexin.com', 'zhushaowei', '202CB962AC59075B964B07152D234B70', NULL, NULL, '18438610363');
INSERT INTO `usertable` VALUES (4, 'weishao@myhexin.com', 'weishao', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, NULL, '18438610363');
INSERT INTO `usertable` VALUES (5, 'zhushaowei1111@myhexin.com', 'zhushaowei1111', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, NULL, '18438610363');

-- ----------------------------
-- Event structure for test
-- ----------------------------
DROP EVENT IF EXISTS `test`;
delimiter ;;
CREATE EVENT `test`
ON SCHEDULE
EVERY '1' HOUR STARTS '2020-01-26 21:41:34'
ON COMPLETION PRESERVE
COMMENT '借书 预期时间 到达时自动将借书状态改为“归还”'
DO UPDATE borrow_table as a INNER JOIN bookdata as b ON a.bookid = b.id SET a.type = 1, a.real_return_date = NOW(), b.status = 0 WHERE a.type = 0 and TIMESTAMPDIFF(HOUR,NOW(), a.expect_return_date) = 0
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
