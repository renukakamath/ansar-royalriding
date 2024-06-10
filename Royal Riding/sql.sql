/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - royal_riding_ansar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`royal_riding_ansar` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `royal_riding_ansar`;

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,10,4,'hai','2022-12-13'),
(2,10,4,'hello','2022-12-13'),
(3,10,5,'heello','2022-12-13'),
(4,11,5,'hai','2022-12-13'),
(5,11,5,'hai','2022-12-13'),
(6,11,4,'hello','2022-12-13');

/*Table structure for table `club` */

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `club_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `club` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `latitude` varchar(200) DEFAULT NULL,
  `longitude` varchar(200) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`club_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `club` */

insert  into `club`(`club_id`,`login_id`,`club`,`phone`,`email`,`image`,`latitude`,`longitude`,`status`) values 
(1,8,'club','9495795304','cc1@g.com','static/image/c2c5f44d-1b7d-4aa1-9e0b-3ae04ccd900dcute.jpg (2).jpg','9.980113414997506','76.28887552913757','Approve'),
(2,8,'bedkjw','9495795304','@sdkjlk','static/image/c2c5f44d-1b7d-4aa1-9e0b-3ae04ccd900dcute.jpg (2).jpg','9.980113414997506','9.980113414997506','Approve'),
(3,12,'reshma','reshma','reshma@gmail.com','static/image/672530b6-7554-435a-a2be-5c1eab893c27background-images-hd-1.webp','9.973854470707526','76.28269571956726','reject');

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) DEFAULT NULL,
  `event` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `event` */

insert  into `event`(`event_id`,`club_id`,`event`,`place`,`latitude`,`longitude`) values 
(1,1,'event1','ernakulam1','9.969180438842457','76.28321070369812'),
(3,1,'event','kerala','9.97813747594594','76.28904719051452');

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotalname` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `photo` varchar(1000) DEFAULT NULL,
  `hotelrent` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `hotel` */

insert  into `hotel`(`hotel_id`,`hotalname`,`phone`,`email`,`photo`,`hotelrent`,`longitude`,`latitude`) values 
(2,'hotels name1','1234567891','hos@gmail.com1','static/image/e24f9512-1b55-4089-bc5f-0f11f9261526bike.jpg','5001','76.29402537044616','9.982659928389289'),
(3,'hotels name','9999999999','student@gmail.com','static/image/1a2fd8aa-5135-4883-89e7-a87e1ffe95f7motorbike-front-side-bike-logo-fast-ride-symbol-2J54CKY.jpg','5000','76.29402537044616','9.982659928389289');

/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `trip_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `images` */

insert  into `images`(`image_id`,`trip_id`,`user_id`,`image`) values 
(1,4,1,'static/image/a4a836d7-f229-4773-aa6d-aa162f3e963cabc.jpg'),
(2,4,1,'static/image/02b3e7eb-149f-42db-be9c-585dd4eead88abc.jpg'),
(3,4,1,'static/image/b9c8415e-7eb3-400a-a83e-09ac1187c2e5abc.jpg'),
(4,4,1,'static/image/5cd45a70-9d2e-4666-8fe4-269282876629abc.jpg'),
(5,4,1,'static/image/3e3497ff-5f1a-4b63-bf20-45a3ee4dc4dbabc.jpg');

/*Table structure for table `joinclub` */

DROP TABLE IF EXISTS `joinclub`;

CREATE TABLE `joinclub` (
  `join_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `joindate` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`join_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `joinclub` */

insert  into `joinclub`(`join_id`,`user_id`,`club_id`,`joindate`,`status`) values 
(1,9,1,'2022-12-13','join'),
(2,9,2,'2022-12-15','join');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(11,'user2','user2','User'),
(10,'user1','user1','User'),
(5,'user','club','club'),
(6,'admin','admin','admin'),
(9,'user','user123','User'),
(8,'club','club','club'),
(12,'reshma','reshma','reject'),
(16,'hai','hai','User');

/*Table structure for table `otherplace` */

DROP TABLE IF EXISTS `otherplace`;

CREATE TABLE `otherplace` (
  `otherplace_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`otherplace_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `otherplace` */

insert  into `otherplace`(`otherplace_id`,`place_id`,`name`,`details`,`latitude`,`longitude`,`phone`,`email`) values 
(3,2,'qwerty','qwerty','9.971040197647778','76.28681559261413','1111111111','renuka@gmail.com'),
(6,1,'qwertyuio',' at today morning  9.30 am','9.982406334587749','76.29067797359558','1234567890','renukakamath@gmail.com');

/*Table structure for table `package` */

DROP TABLE IF EXISTS `package`;

CREATE TABLE `package` (
  `package_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_id` int(11) DEFAULT NULL,
  `packagename` varchar(100) DEFAULT NULL,
  `Amount` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`package_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `package` */

insert  into `package`(`package_id`,`place_id`,`packagename`,`Amount`,`details`) values 
(2,2,'package','500','packages');

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `place` */

insert  into `place`(`place_id`,`place`) values 
(1,'ernakulam'),
(2,'kerala'),
(4,'Thammanam');

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `trip_id` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `request` */

insert  into `request`(`request_id`,`user_id`,`trip_id`,`status`) values 
(1,10,4,'requested'),
(2,10,5,'requested'),
(3,11,5,'requested'),
(4,11,4,'requested'),
(5,9,4,'requested'),
(6,9,4,'requested'),
(7,9,4,'requested'),
(8,9,5,'requested'),
(9,9,5,'requested'),
(10,9,4,'requested');

/*Table structure for table `trip` */

DROP TABLE IF EXISTS `trip`;

CREATE TABLE `trip` (
  `trip_id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) DEFAULT NULL,
  `trip` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `from_date` varchar(100) DEFAULT NULL,
  `to_date` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`trip_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `trip` */

insert  into `trip`(`trip_id`,`club_id`,`trip`,`place`,`details`,`from_date`,`to_date`,`amount`) values 
(5,1,'trip','kochi','qwertyui','2022-12-24','2022-12-29','1000'),
(4,1,'trip1','ernakulam1','qwertyui1','2022-12-29','2022-12-22','100'),
(6,1,'trip','ernakaulam','qwertyui','2023-01-26','2023-01-31','100000');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `license` varchar(100) DEFAULT NULL,
  `aadhaar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`license`,`aadhaar`) values 
(1,9,'user','user','ernakulam1','123456781','user1@gmail.com','fsgaiudsgaiu1',NULL),
(2,10,'user1','user2','kdjfb','123456781','user1@gmail.com','fsgaiudsgaiu1',NULL),
(3,11,'user3','user3','ksjb','9879879','KFDJNB','KJDFHV',NULL),
(4,16,'hello','fsshehwi gssjs','Place','1234567890','hai@gmail.com','fsshehwi gssjs','1234567890');

/*Table structure for table `workshop` */

DROP TABLE IF EXISTS `workshop`;

CREATE TABLE `workshop` (
  `workshop_id` int(11) NOT NULL AUTO_INCREMENT,
  `workshop` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`workshop_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `workshop` */

insert  into `workshop`(`workshop_id`,`workshop`,`details`,`image`,`phone`,`latitude`,`longitude`) values 
(13,'workshop1',' at today morning  9.30 am','static/image/26eaa773-9aea-4eee-bcbf-664172f76fbcdownload (3) - Copy.jfif','0987654321','9.960850138586341','76.27067942318054'),
(12,'wfwf','qwertyui','static/image/894f7b3b-be14-441e-9dbe-040c135bc977download (3).jfif','9495795304','9.960850138586341','76.27067942318054'),
(14,'wfwfw','qwertyui','static/image/51a47f28-bad5-4872-a697-6a02d79dfadfbike.jpg','0987654321','9.960850138586341','76.27067942318054');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
