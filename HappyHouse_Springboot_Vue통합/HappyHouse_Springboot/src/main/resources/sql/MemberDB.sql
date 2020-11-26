CREATE DATABASE  IF NOT EXISTS `happyhouse`;

USE `happyhouse`;

CREATE TABLE `member`(
     `id` varchar(20) PRIMARY KEY,
     `pw` varchar(20) NOT NULL,
     `name` varchar(20) NOT NULL,
     `phoneNumber` varchar(20) NOT NULL,
     `gender` varchar(1) NOT NULL,
     `email` varchar(30) NOT NULL,
     `nickName` varchar(20) NOT NULL,
     `emailCheck` varchar(1) NOT NULL,
     `phoneCheck` varchar(1) NOT NULL,
     `admincode` varchar(1) NOT NULL
);

INSERT INTO `member` VALUES('admin', 'admin', 'admin', '01020323323', '0', 'kimchi@gmail.com','김치','1','1','1');
INSERT INTO `member` VALUES('ssafy', 'ssafy', 'ssafy', '01039498489', '1', 'africa@gmail.com','아프리카','1','1','0');
INSERT INTO `member` VALUES('ssafy1', 'ssafy1', 'ssafy1', '01039498489', '1', 'africa@gmail.com','아프리카','1','1','0');
INSERT INTO `member` VALUES('ssafy2', 'ssafy2', 'ssafy2', '01039498489', '1', 'africa@gmail.com','아프리카','1','1','0');
INSERT INTO `member` VALUES('ssafy3', 'ssafy3', 'ssafy3', '01039498489', '1', 'africa@gmail.com','아프리카','1','1','0');
INSERT INTO `member` VALUES('ssafy4', 'ssafy4', 'ssafy4', '01039498489', '1', 'africa@gmail.com','아프리카','1','1','0');

commit;