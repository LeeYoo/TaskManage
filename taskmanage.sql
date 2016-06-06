/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.1.58-community : Database - taskmanage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`taskmanage` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `taskmanage`;

/*Table structure for table `chartinfo` */

DROP TABLE IF EXISTS `chartinfo`;

CREATE TABLE `chartinfo` (
  `chartId` int(11) NOT NULL AUTO_INCREMENT,
  `progress` int(11) NOT NULL,
  `updateTime` date NOT NULL,
  `user_no` int(11) DEFAULT NULL,
  `work_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`chartId`),
  KEY `FKD5ADB18CE0B6D7B` (`work_no`),
  KEY `FKD5ADB18C5BF958F5` (`user_no`),
  CONSTRAINT `FKD5ADB18C5BF958F5` FOREIGN KEY (`user_no`) REFERENCES `user` (`userno`),
  CONSTRAINT `FKD5ADB18CE0B6D7B` FOREIGN KEY (`work_no`) REFERENCES `work` (`workId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=gbk;

/*Data for the table `chartinfo` */

insert  into `chartinfo`(`chartId`,`progress`,`updateTime`,`user_no`,`work_no`) values (1,20,'2015-06-01',1,1),(2,26,'2015-06-02',1,1),(3,35,'2015-06-03',1,1),(4,46,'2015-06-04',1,1),(5,67,'2015-06-05',1,1),(6,80,'2015-06-07',1,1),(7,95,'2015-06-09',1,1),(8,100,'2015-06-10',1,1),(10,12,'2015-06-01',2,2),(11,24,'2015-06-02',2,2),(12,35,'2015-06-03',2,2),(13,53,'2015-06-04',2,2),(14,62,'2015-06-05',2,2),(15,75,'2015-06-06',2,2),(16,88,'2015-06-07',2,2),(17,95,'2015-06-08',2,2),(18,100,'2015-06-09',2,2),(19,80,'2015-06-07',6,5),(20,23,'2015-06-08',3,3),(21,100,'2015-06-08',6,5),(22,100,'2015-06-08',6,5),(23,67,'2015-06-08',3,3),(24,100,'2015-06-08',3,3),(25,50,'2015-06-08',3,3),(26,100,'2015-06-08',3,3),(27,-10,'2015-06-08',3,3),(28,110,'2015-06-08',3,3),(29,100,'2015-06-08',3,3),(30,100,'2015-06-08',6,5),(31,100,'2015-06-08',6,5),(32,80,'2015-06-08',4,4),(33,90,'2015-06-08',6,5);

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `deptID` int(11) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`deptID`),
  UNIQUE KEY `deptname` (`deptname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `dept` */

insert  into `dept`(`deptID`,`deptname`) values (1,'办公室'),(2,'财务企划部'),(4,'风险条线'),(3,'人力资源部'),(5,'运营管理部'),(6,'综合金融部');

/*Table structure for table `group_privilege` */

DROP TABLE IF EXISTS `group_privilege`;

CREATE TABLE `group_privilege` (
  `g_Id` varchar(36) NOT NULL,
  `p_module` varchar(20) NOT NULL,
  `p_privilege` varchar(20) NOT NULL,
  PRIMARY KEY (`g_Id`,`p_module`,`p_privilege`),
  KEY `FK185614F12EDC3E7E` (`p_module`,`p_privilege`),
  KEY `FK185614F1EA81E3E2` (`g_Id`),
  CONSTRAINT `FK185614F12EDC3E7E` FOREIGN KEY (`p_module`, `p_privilege`) REFERENCES `systemprivilege` (`module`, `privilege`),
  CONSTRAINT `FK185614F1EA81E3E2` FOREIGN KEY (`g_Id`) REFERENCES `privilegegroup` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `group_privilege` */

insert  into `group_privilege`(`g_Id`,`p_module`,`p_privilege`) values ('9e76b7e3-3c96-4c24-98b6-730830690483','score','view'),('a53470e0-62e4-434d-9852-77206fd49383','chart','view'),('a53470e0-62e4-434d-9852-77206fd49383','dept','add'),('a53470e0-62e4-434d-9852-77206fd49383','dept','delete'),('a53470e0-62e4-434d-9852-77206fd49383','dept','edit'),('a53470e0-62e4-434d-9852-77206fd49383','dept','view'),('a53470e0-62e4-434d-9852-77206fd49383','privilege','view'),('a53470e0-62e4-434d-9852-77206fd49383','privilegeGroup','add'),('a53470e0-62e4-434d-9852-77206fd49383','privilegeGroup','delete'),('a53470e0-62e4-434d-9852-77206fd49383','privilegeGroup','edit'),('a53470e0-62e4-434d-9852-77206fd49383','privilegeGroup','view'),('a53470e0-62e4-434d-9852-77206fd49383','score','add'),('a53470e0-62e4-434d-9852-77206fd49383','score','view'),('a53470e0-62e4-434d-9852-77206fd49383','user','delete'),('a53470e0-62e4-434d-9852-77206fd49383','user','privilegeSet'),('a53470e0-62e4-434d-9852-77206fd49383','user','view');

/*Table structure for table `privilegegroup` */

DROP TABLE IF EXISTS `privilegegroup`;

CREATE TABLE `privilegegroup` (
  `groupId` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `privilegegroup` */

insert  into `privilegegroup`(`groupId`,`name`) values ('9e76b7e3-3c96-4c24-98b6-730830690483','评分查看权限'),('a53470e0-62e4-434d-9852-77206fd49383','系统权限组');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `scoreId` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `user_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`scoreId`),
  KEY `FK4C04E725BF958F5` (`user_no`),
  CONSTRAINT `FK4C04E725BF958F5` FOREIGN KEY (`user_no`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

/*Data for the table `score` */

insert  into `score`(`scoreId`,`score`,`user_no`) values (1,100,1),(2,90,2);

/*Table structure for table `systemprivilege` */

DROP TABLE IF EXISTS `systemprivilege`;

CREATE TABLE `systemprivilege` (
  `module` varchar(20) NOT NULL,
  `privilege` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`module`,`privilege`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `systemprivilege` */

insert  into `systemprivilege`(`module`,`privilege`,`name`) values ('chart','view','任务进度查看权限'),('dept','add','部门添加权限'),('dept','delete','部门删除权限'),('dept','edit','部门修改权限'),('dept','view','部门查看权限'),('privilege','view','权限列表查看权限'),('privilegeGroup','add','权限组列表添加权限'),('privilegeGroup','delete','权限组列表删除权限'),('privilegeGroup','edit','权限组列表修改权限'),('privilegeGroup','view','权限组列表查看权限'),('score','add','给予评分权限'),('score','view','评分查看权限'),('user','delete','用户删除权限'),('user','privilegeSet','用户权限设置权限'),('user','view','用户查看权限');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userno` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `identity` int(11) NOT NULL,
  `password` varchar(32) NOT NULL,
  `realname` varchar(20) NOT NULL,
  `userUM` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `dept_no` int(11) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`userno`),
  UNIQUE KEY `userUM` (`userUM`),
  KEY `FK285FEBAAC9FD85` (`parentid`),
  KEY `FK285FEB6E3DBA2F` (`dept_no`),
  CONSTRAINT `FK285FEB6E3DBA2F` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`deptID`),
  CONSTRAINT `FK285FEBAAC9FD85` FOREIGN KEY (`parentid`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=gbk;

/*Data for the table `user` */

insert  into `user`(`userno`,`email`,`gender`,`identity`,`password`,`realname`,`userUM`,`username`,`dept_no`,`parentid`) values (1,'youth_liyao@qq.com','MAN',1,'liyao','李耀','liyao001','liyao',1,NULL),(2,NULL,'WOMEN',2,'user1','王VJ','UM1','user1',1,1),(3,NULL,'MAN',2,'user2','赵NP','UM2','user2',1,1),(4,NULL,'WOMEN',2,'user3','李ZP','UM3','user3',1,1),(5,NULL,'MAN',2,'user4','李ZC','UM4','user4',1,1),(6,NULL,'WOMEN',2,'user5','张HA','UM5','user5',1,1),(7,NULL,'MAN',2,'user6','唐WY','UM6','user6',1,1),(8,NULL,'WOMEN',2,'user7','唐OE','UM7','user7',1,1),(9,NULL,'MAN',3,'user8','唐SE','UM8','user8',1,2),(10,NULL,'WOMEN',3,'user9','张BP','UM9','user9',1,2),(11,NULL,'MAN',3,'user10','王WJ','UM10','user10',1,2),(12,NULL,'WOMEN',3,'user11','王ZU','UM11','user11',1,2),(13,NULL,'MAN',3,'user12','唐DH','UM12','user12',1,2),(14,NULL,'WOMEN',3,'user13','胡OA','UM13','user13',1,2),(15,NULL,'MAN',3,'user14','胡LS','UM14','user14',1,2),(16,NULL,'WOMEN',1,'user15','唐JK','UM15','user15',NULL,NULL),(17,NULL,'MAN',3,'user16','赵VH','UM16','user16',NULL,NULL),(18,NULL,'WOMEN',1,'user17','胡RV','UM17','user17',NULL,NULL),(19,NULL,'MAN',3,'user18','李WQ','UM18','user18',NULL,NULL),(20,NULL,'WOMEN',2,'user19','唐CX','UM19','user19',NULL,NULL),(21,NULL,'MAN',2,'user20','胡KU','UM20','user20',NULL,NULL),(22,NULL,'WOMEN',1,'user21','王NM','UM21','user21',NULL,NULL),(23,NULL,'MAN',2,'user22','李DO','UM22','user22',NULL,NULL),(24,NULL,'WOMEN',2,'user23','胡ZI','UM23','user23',NULL,NULL),(25,NULL,'MAN',3,'user24','胡IT','UM24','user24',NULL,NULL),(26,NULL,'WOMEN',1,'user25','张MX','UM25','user25',NULL,NULL),(27,NULL,'MAN',1,'user26','胡XR','UM26','user26',NULL,NULL),(28,NULL,'WOMEN',3,'user27','胡TJ','UM27','user27',NULL,NULL),(29,NULL,'MAN',1,'user28','赵PK','UM28','user28',NULL,NULL),(30,NULL,'WOMEN',3,'user29','张LZ','UM29','user29',NULL,NULL),(31,NULL,'MAN',2,'user30','唐DL','UM30','user30',NULL,NULL),(32,NULL,'WOMEN',3,'user31','李KU','UM31','user31',NULL,NULL),(33,NULL,'MAN',2,'user32','张KW','UM32','user32',NULL,NULL),(34,NULL,'WOMEN',2,'user33','李YV','UM33','user33',NULL,NULL),(35,NULL,'MAN',2,'user34','李KD','UM34','user34',NULL,NULL),(36,NULL,'WOMEN',2,'user35','赵EB','UM35','user35',NULL,NULL),(37,NULL,'MAN',1,'user36','张YI','UM36','user36',NULL,NULL),(38,NULL,'WOMEN',1,'user37','张EF','UM37','user37',NULL,NULL),(39,NULL,'MAN',2,'user38','胡GZ','UM38','user38',NULL,NULL),(40,NULL,'WOMEN',1,'user39','唐CR','UM39','user39',NULL,NULL),(41,NULL,'MAN',2,'user40','王YU','UM40','user40',NULL,NULL),(42,NULL,'WOMEN',1,'user41','李LX','UM41','user41',NULL,NULL),(43,NULL,'MAN',2,'user42','李CW','UM42','user42',NULL,NULL),(44,NULL,'WOMEN',3,'user43','李JG','UM43','user43',NULL,NULL),(45,NULL,'MAN',1,'user44','张MW','UM44','user44',NULL,NULL),(46,NULL,'WOMEN',1,'user45','唐CI','UM45','user45',NULL,NULL),(47,NULL,'MAN',3,'user46','李KE','UM46','user46',NULL,NULL),(48,NULL,'WOMEN',1,'user47','李IS','UM47','user47',NULL,NULL),(49,NULL,'MAN',3,'user48','李IP','UM48','user48',NULL,NULL),(50,NULL,'WOMEN',2,'user49','赵NH','UM49','user49',NULL,NULL),(51,NULL,'MAN',3,'user50','胡YC','UM50','user50',NULL,NULL);

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `user_Id` int(11) NOT NULL,
  `group_Id` varchar(36) NOT NULL,
  PRIMARY KEY (`user_Id`,`group_Id`),
  KEY `FK72A9010B5BF9584F` (`user_Id`),
  KEY `FK72A9010B88016CA` (`group_Id`),
  CONSTRAINT `FK72A9010B5BF9584F` FOREIGN KEY (`user_Id`) REFERENCES `user` (`userno`),
  CONSTRAINT `FK72A9010B88016CA` FOREIGN KEY (`group_Id`) REFERENCES `privilegegroup` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `user_group` */

insert  into `user_group`(`user_Id`,`group_Id`) values (1,'a53470e0-62e4-434d-9852-77206fd49383'),(2,'9e76b7e3-3c96-4c24-98b6-730830690483');

/*Table structure for table `work` */

DROP TABLE IF EXISTS `work`;

CREATE TABLE `work` (
  `workId` int(11) NOT NULL AUTO_INCREMENT,
  `begintime` date DEFAULT NULL,
  `completetime` date DEFAULT NULL,
  `grand` varchar(10) NOT NULL,
  `info` varchar(50) DEFAULT NULL,
  `playtime` date NOT NULL,
  `progress` int(11) DEFAULT NULL,
  `sta` varchar(5) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `workname` varchar(50) NOT NULL,
  `dept_no` int(11) DEFAULT NULL,
  `score_no` int(11) DEFAULT NULL,
  `user_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`workId`),
  KEY `FK293B315BF958F5` (`user_no`),
  KEY `FK293B3127A65A9E` (`score_no`),
  KEY `FK293B316E3DBA2F` (`dept_no`),
  CONSTRAINT `FK293B3127A65A9E` FOREIGN KEY (`score_no`) REFERENCES `score` (`scoreId`),
  CONSTRAINT `FK293B315BF958F5` FOREIGN KEY (`user_no`) REFERENCES `user` (`userno`),
  CONSTRAINT `FK293B316E3DBA2F` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`deptID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

/*Data for the table `work` */

insert  into `work`(`workId`,`begintime`,`completetime`,`grand`,`info`,`playtime`,`progress`,`sta`,`type`,`workname`,`dept_no`,`score_no`,`user_no`) values (1,NULL,'2015-06-10','Important','李耀','2015-06-25',100,'ING','huiyi','A项目的启动',1,1,1),(2,NULL,'2015-06-09','Important','李耀','2015-06-19',100,'ING','tongzhi','用户模块设计',1,2,2),(3,NULL,'2015-06-11','Important','李耀','2015-06-10',100,NULL,'tongzhi','产品定位',1,NULL,3),(4,NULL,NULL,'Simple','李耀','2015-06-18',80,NULL,'qita','项目接口设计',1,NULL,4),(5,NULL,NULL,'Important','李耀','2015-06-17',90,'YES','huodong','集成测试',1,NULL,6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
