/*
SQLyog Ultimate - MySQL GUI v8.32 
MySQL - 5.5.22 : Database - copyrightdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`copyrightdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `copyrightdb`;

/*Table structure for table `softitem` */

DROP TABLE IF EXISTS `softitem`;

CREATE TABLE `softitem` (
  `registerid` varchar(15) NOT NULL DEFAULT 'TEST',
  `typecode` varchar(12) DEFAULT NULL,
  `softname` varchar(250) DEFAULT NULL,
  `softbrief` varchar(150) DEFAULT NULL,
  `version` varchar(80) DEFAULT NULL,
  `author` varchar(400) DEFAULT NULL,
  `publishdate` varchar(12) DEFAULT NULL,
  `registerdate` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`registerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `softitem` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
