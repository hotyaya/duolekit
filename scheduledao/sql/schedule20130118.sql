/*
SQLyog Ultimate v8.32 
MySQL - 5.5.16 : Database - schedule
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schedule` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `schedule`;

/*Table structure for table `jobitem` */

DROP TABLE IF EXISTS `jobitem`;

CREATE TABLE `jobitem` (
  `ID` char(32) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userid` int(11) DEFAULT NULL,
  `seq` bigint(19) DEFAULT NULL,
  `actionDate` int(8) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `numPrefix` varchar(30) DEFAULT NULL,
  `numNo` varchar(50) DEFAULT NULL,
  `numSuffix` varchar(20) DEFAULT NULL,
  `content1` varchar(1000) DEFAULT NULL,
  `content2` varchar(1000) DEFAULT NULL,
  `content3` varchar(1000) DEFAULT NULL,
  `content4` varchar(1000) DEFAULT NULL,
  `content5` varchar(1000) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `sourceID` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `statusCreatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jobitem` */

insert  into `jobitem`(`ID`,`createTime`,`userid`,`seq`,`actionDate`,`ip`,`type`,`numPrefix`,`numNo`,`numSuffix`,`content1`,`content2`,`content3`,`content4`,`content5`,`source`,`sourceID`,`status`,`statusCreatetime`,`memo`) values ('8ac0c8a73c3d990e013c3d990f5d0001','2013-01-15 17:45:05',1,1,20130115,NULL,'???????','???','[2013]','2?','1','2','3','4','5','82391231234123',NULL,'??','2013-01-15 17:45:05','????'),('8ac0c8a73c3da079013c3da07adc0001','2013-01-15 17:53:11',1,1,20130115,NULL,'???????','???','[2013]','2?','1','2','3','4','5','82391231234123',NULL,'??','2013-01-15 17:53:11','????'),('8ac0c8a73c3da0db013c3da0dc4f0001','2013-01-15 17:53:36',1,1,20130115,NULL,'北京铁路局局文','京信息','[2013]','2号','1','2','3','4','5','82391231234123',NULL,'办完','2013-01-15 17:53:36','测试数据'),('8ac0c8a73c4cbccd013c4cbcce6b0001','2013-01-18 16:18:26',1,1,20130115,NULL,'北京铁路局局文','京信息','[2013]','2号','1','2','3','4','5','82391231234123',NULL,'办完','2013-01-18 16:18:26','测试数据');

/*Table structure for table `process` */

DROP TABLE IF EXISTS `process`;

CREATE TABLE `process` (
  `ID` char(32) NOT NULL,
  `seq` int(10) NOT NULL,
  `processCreatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `person` varchar(10) DEFAULT NULL,
  `personContact` varchar(50) DEFAULT NULL,
  `processContent` varchar(200) DEFAULT NULL,
  `processResult` varchar(200) DEFAULT NULL COMMENT '向领导请示',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `process` */

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `ID` char(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `tagCreatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`,`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

/*Table structure for table `vjobitemmaxseq` */

DROP TABLE IF EXISTS `vjobitemmaxseq`;

/*!50001 DROP VIEW IF EXISTS `vjobitemmaxseq` */;
/*!50001 DROP TABLE IF EXISTS `vjobitemmaxseq` */;

/*!50001 CREATE TABLE  `vjobitemmaxseq`(
 `maxseq` bigint(19) 
)*/;

/*View structure for view vjobitemmaxseq */

/*!50001 DROP TABLE IF EXISTS `vjobitemmaxseq` */;
/*!50001 DROP VIEW IF EXISTS `vjobitemmaxseq` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vjobitemmaxseq` AS (select max(`jobitem`.`seq`) AS `maxseq` from `jobitem`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
