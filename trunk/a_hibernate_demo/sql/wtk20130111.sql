/*
SQLyog Ultimate v8.32 
MySQL - 5.5.16 : Database - wtk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wtk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wtk`;

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `did` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `dept` varchar(200) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`did`,`pid`,`dept`,`memo`) values ('8ac0c8a73c2753ac013c2753ad960001','0','信息化处','新成立'),('8ac0c8a73c2753fd013c2753fe4e0001','0','信息化处','新成立'),('8ac0c8a73c27543c013c27543cb60001','0','信息化处','新成立'),('8ac0c8a73c276f06013c276f06c40001','0','信息化处','新成立'),('8ac0c8a73c277f83013c277f84930001','0','信息化处','新成立');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
