

DROP TABLE IF EXISTS `doccatalog`;

CREATE TABLE `doccatalog` (
  `docid` INTEGER PRIMARY KEY AUTOINCREMENT,  
  `type` varchar(2) DEFAULT NULL,
  `docsenddate` decimal(8,0) DEFAULT NULL,
  `docsendtime` varchar(25) DEFAULT NULL,
  `docsender` varchar(40) DEFAULT NULL,
  `doccaption` varchar(100) DEFAULT NULL,
  `doccode` varchar(30) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `baseurl` varchar(100) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `indate` decimal(8,0) DEFAULT NULL,
  `intimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ishidden` decimal(1,0) DEFAULT NULL
) ;

DROP TABLE IF EXISTS `docrecv`;

CREATE TABLE `docrecv` (
  `Docid` INTEGER PRIMARY KEY,
  `Transmitter` varchar(50) DEFAULT NULL,
  `Recvdate` decimal(8,0) DEFAULT NULL,
  `Opertimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `RecvTag` varchar(50) DEFAULT NULL,
  `Memo` varchar(200) DEFAULT NULL,
  `triggertime` varchar(8) DEFAULT NULL,
  `isok` decimal(1,0) DEFAULT NULL,
  `oktimestamp` timestamp NULL DEFAULT NULL,
  `okmemo` varchar(100) DEFAULT NULL
);



DROP VIEW IF EXISTS `vcatadaynum`;

CREATE VIEW `vcatadaynum` AS 
select `doccatalog`.`docsenddate` AS `docsenddate`,count(0) AS `COUNT(*)` from `doccatalog` group by `doccatalog`.`docsenddate` order by `doccatalog`.`docsenddate` desc ;


DROP VIEW IF EXISTS `vdocrecv`;

CREATE VIEW `vdocrecv` AS 
select `c`.`docid` AS `docid`,`c`.`type` AS `type`,`c`.`docsenddate` AS `docsenddate`,`c`.`docsendtime` AS `docsendtime`,`c`.`docsender` AS `docsender`,`c`.`doccaption` AS `doccaption`,`c`.`doccode` AS `doccode`,`c`.`contact` AS `contact`,`c`.`phone` AS `phone`,`c`.`baseurl` AS `baseurl`,`c`.`url` AS `url`,`c`.`indate` AS `indate`,`c`.`intimestamp` AS `intimestamp`,`c`.`ishidden` AS `ishidden`,`r`.`Transmitter` AS `transmitter`,`r`.`Recvdate` AS `recvdate`,`r`.`Opertimestamp` AS `Opertimestamp`,`r`.`RecvTag` AS `RecvTag`,`r`.`Memo` AS `Memo`,`r`.`triggertime` AS `triggertime`,`r`.`isok` AS `isok`,`r`.`oktimestamp` AS `oktimestamp`,`r`.`okmemo` AS `okmemo` 
from (`doccatalog` `c` join `docrecv` `r`) where (`c`.`docid` = `r`.`Docid`) order by `c`.`docid` ;
