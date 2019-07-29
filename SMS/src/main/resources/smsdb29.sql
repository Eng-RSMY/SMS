-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: own
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profession` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4w905h1321m2ide56ov5efb0` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent`
--

LOCK TABLES `parent` WRITE;
/*!40000 ALTER TABLE `parent` DISABLE KEYS */;
INSERT INTO `parent` VALUES (1,'Farmer',10,'7799171894','Male'),(2,'Farmer',11,'7788445511','Male');
/*!40000 ALTER TABLE `parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) DEFAULT NULL,
  `class` int(11) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `st_id` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqqq09m02bgrk47qoe6fs3ht4w` (`parent_id`),
  KEY `FKk5m148xqefonqw7bgnpm0snwj` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,0,6,'1995-05-10','Male',0,'A','SSSS',2),(2,0,3,'1996-05-10','Male',0,'B','SSSS',4),(3,0,5,'1996-06-10','Male',1,'B','SSSS1',5),(4,0,3,'1994-05-04','Male',2,'C','SSSS1',6),(5,0,2,'1995-09-07','Female',0,'A','SSSS1',7),(6,0,6,'1994-11-06','Male',0,'B','SSSS1',8);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpb6g6pahj1mr2ijg92r7m1xlh` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,0,'1991-06-10',3,'Female','English','7731045477','Telugu',3),(2,0,'1995-05-15',5,'Female','English','6920541279','Science',9);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `fname_lname_unique` (`name`,`last_name`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'dileep@gmail.com','Srisailam','Dileep','$2a$10$wwNQgXr4Lq2VbEw/5SKfhOhc779xBlntgHMrQ3WgtEQUvHj1y0cAy',3),(2,'mahi@gmail.com','Danishetty','Mahesh','$2a$10$pO8dXypiFw9wwFHPjQLxROLXpha5GndQxa49WQUjdo.NHPluu.DPi',5),(3,'sam143@gmail.com','S','Sam','$2a$10$PxuihQpjVNCEVVkg0ln4weT0Z6JVOfh/3qCvnkHD7ofKZOeFyGzYW',2),(4,'naga@gmail.com','Yerramala','Naga','$2a$10$cx3jP42GbevEp2gosu0mLe4vnHYuQvESu2W3VtbOCfdotxWcPatMm',5),(5,'manoj@gmail.com','Srisailam','Manoj','$2a$10$fDR12fQ4Zr02/g9bSpRcGOZDykJzvcsS.GL0j8gErsJX4hcCDa6u6',5),(6,'ashok112@gmail.com','Kappala','Ashok','$2a$10$JYzJaF8/Xv3xuD.sS4XNMOPpVM4d35IAQkltJ8zv.Nwqvcu2/MF5m',5),(7,'kavya12@gmail.com','K','Kavya','$2a$10$6BPS1N94Rr5Wr1H/xlcs2O2gBqibjTe4AioqWFjaU5R21qxu.rFy2',5),(8,'anil.09@gmail.com','Thumu','Anil','$2a$10$lRpC8M8obywgyqs0h3I7Ve3l.Ozmvku.n1UtbMbwJAtF3YpQU.0Ty',5),(9,'hari.chundi@gmail.com','Chundi','Haritha','$2a$10$.p2AB/NlxP02K.Cl.G8qLudZzfe38W1F/0Gsx1z1IBmbtP9WkiHGe',2),(10,'','Srisailam','Manohar','$2a$10$yDEcQV6JNOJq3G08225gdOW3apbxFAe8j4yoMn6wA6mQ5UE0d7Yb.',4),(11,'','Kappala','Seenaiah','$2a$10$4wjpQKGWIQwuicBhiaNpReoY9fvpGoomxYCFCRWhM/9/R5/L2qhTS',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-29 17:19:32
