/*
SQLyog Ultimate v8.32 
MySQL - 5.5.22 : Database - rddb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rddb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rddb`;

/*Table structure for table `maindevice` */

DROP TABLE IF EXISTS `maindevice`;

CREATE TABLE `maindevice` (
  `ORGC` char(7) DEFAULT NULL,
  `DEVID` bigint(20) NOT NULL,
  `MODC` int(11) DEFAULT NULL,
  `HLDID` bigint(20) DEFAULT NULL,
  `FC` int(11) DEFAULT NULL,
  `FDATE` date DEFAULT NULL,
  `FSN` varchar(20) DEFAULT NULL,
  `CS` char(2) DEFAULT NULL,
  `CSLI` bigint(20) DEFAULT NULL,
  `CSU` varchar(20) DEFAULT NULL,
  `CST` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CSO` varchar(20) DEFAULT NULL,
  `LS` char(2) DEFAULT NULL,
  `LSLI` bigint(20) DEFAULT NULL,
  `CLTAG` varchar(20) DEFAULT NULL,
  `CLC` varchar(20) DEFAULT NULL,
  `LCD` date DEFAULT NULL,
  `LCU` varchar(20) DEFAULT NULL,
  `LCLI` bigint(20) DEFAULT NULL,
  `NCPI` bigint(20) DEFAULT NULL,
  `NCD` date DEFAULT NULL,
  `CT1` char(1) DEFAULT '0',
  `CT2` char(1) DEFAULT '0',
  `CT3` char(1) DEFAULT '0',
  `CT4` char(1) DEFAULT '0',
  `CT5` char(1) DEFAULT '0',
  `ST1` varchar(50) DEFAULT '0',
  `ST2` varchar(50) DEFAULT '0',
  `ST3` varchar(50) DEFAULT '0',
  `ST4` varchar(50) DEFAULT '0',
  `ST5` varchar(50) DEFAULT '0',
  `VER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DEVID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `maindevice` */

insert  into `maindevice`(`ORGC`,`DEVID`,`MODC`,`HLDID`,`FC`,`FDATE`,`FSN`,`CS`,`CSLI`,`CSU`,`CST`,`CSO`,`LS`,`LSLI`,`CLTAG`,`CLC`,`LCD`,`LCU`,`LCLI`,`NCPI`,`NCD`,`CT1`,`CT2`,`CT3`,`CT4`,`CT5`,`ST1`,`ST2`,`ST3`,`ST4`,`ST5`,`VER`) values ('0BJBJXZ',282373123,920,0,893,'2012-10-04','923123412341234','XD',292713731,'刘辉','2012-10-04 15:45:12','操作员','MD',92073123,'loco','4391','2012-10-04','检修员',22073123,292435,'2012-10-04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'version'),('0BJBJXZ',282373124,920,0,893,'2012-10-01','923123412341234','XD',292713731,'刘辉','2012-10-04 21:25:12','操作员','MD',92073123,'loco','4392','2010-09-09','检修员',22073123,292435,'2012-10-02','0','0','0','0','0','','','','','','version');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
