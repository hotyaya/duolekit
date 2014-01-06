/*
SQLyog Ultimate v8.32 
MySQL - 5.5.16 : Database - job
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`job` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `job`;

/*Table structure for table `doccatalog` */

DROP TABLE IF EXISTS `doccatalog`;

CREATE TABLE `doccatalog` (
  `docid` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(2) DEFAULT NULL,
  `docsenddate` decimal(8,0) DEFAULT NULL,
  `docsender` varchar(40) DEFAULT NULL,
  `doccaption` varchar(100) DEFAULT NULL,
  `doccode` varchar(30) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `baseurl` varchar(100) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `indate` decimal(8,0) DEFAULT NULL,
  `intimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ishidden` decimal(1,0) DEFAULT NULL,
  PRIMARY KEY (`docid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `doccatalog` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
