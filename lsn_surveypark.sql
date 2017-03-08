-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: lsn_surveypark
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `surveyid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK18ylvxhcpynpxr6dkksolaxs1` (`surveyid`),
  CONSTRAINT `FK18ylvxhcpynpxr6dkksolaxs1` FOREIGN KEY (`surveyid`) REFERENCES `surveys` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
INSERT INTO `pages` VALUES (1,'???',NULL,3),(2,'???',NULL,4),(3,'未命名',NULL,5),(4,'未命名',NULL,6),(5,'未命名',NULL,7),(6,'未命名',NULL,8),(7,'未命名',NULL,9),(8,'未命名',NULL,10),(9,'未命名',NULL,11),(10,'未命名',NULL,12),(11,'未命名',NULL,13),(12,'未命名',NULL,14),(13,'未命名',NULL,15),(14,'未命名',NULL,16),(15,'未命名',NULL,17),(16,'未命名',NULL,18),(17,'未命名',NULL,19),(18,'未命名',NULL,20),(19,'未命名',NULL,21),(20,'未命名',NULL,22),(21,'未命名',NULL,23),(22,'未命名',NULL,24),(23,'未命名',NULL,25),(24,'未命名',NULL,26),(25,'未命名',NULL,27),(26,'未命名',NULL,28),(27,'未命名',NULL,29),(28,'未命名',NULL,30),(29,'未命名',NULL,31),(30,'未命名',NULL,32),(31,'未命名',NULL,33),(32,'未命名',NULL,34),(33,'未命名',NULL,35),(34,'未命名',NULL,36),(35,'未命名',NULL,37);
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionType` tinyint(1) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `options` varchar(255) DEFAULT NULL,
  `other` tinyint(1) DEFAULT NULL,
  `otherType` tinyint(1) DEFAULT NULL,
  `otherSelectOptions` varchar(255) DEFAULT NULL,
  `matrixRowTitles` varchar(255) DEFAULT NULL,
  `matrixColTitles` varchar(255) DEFAULT NULL,
  `matrixSelectOptions` varchar(255) DEFAULT NULL,
  `pageid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr4eyi3i8it6g1iof14o4aalsk` (`pageid`),
  CONSTRAINT `FKr4eyi3i8it6g1iof14o4aalsk` FOREIGN KEY (`pageid`) REFERENCES `pages` (`id`),
  CONSTRAINT `fk_pageid` FOREIGN KEY (`pageid`) REFERENCES `pages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,11),(2,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,12),(3,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,13),(4,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,14),(5,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,15),(6,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,16),(7,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,17),(8,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,18),(9,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,19),(10,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,20),(11,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,21),(12,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,22),(13,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,23),(14,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,24),(15,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,25),(16,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,26),(17,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,27),(18,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,28),(19,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,29),(20,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,30),(21,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,31),(22,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,32),(23,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,33),(24,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,34),(25,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL,35);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surveys`
--

DROP TABLE IF EXISTS `surveys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `surveys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `pretext` varchar(50) DEFAULT NULL,
  `nexttext` varchar(50) DEFAULT NULL,
  `exittext` varchar(50) DEFAULT NULL,
  `donetext` varchar(50) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7v9t2urcgkl8p8bs2mi5ogksn` (`userid`),
  CONSTRAINT `FK7v9t2urcgkl8p8bs2mi5ogksn` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surveys`
--

LOCK TABLES `surveys` WRITE;
/*!40000 ALTER TABLE `surveys` DISABLE KEYS */;
INSERT INTO `surveys` VALUES (1,'???','???','???','??','??','2017-02-23 16:10:28',NULL),(2,'???','???','???','??','??','2017-02-23 16:11:22',NULL),(3,'???','???','???','??','??','2017-02-26 14:55:01',NULL),(4,'???','???','???','??','??','2017-02-26 15:00:59',NULL),(5,'未命名','上一步','下一步','退出','完成','2017-02-26 15:03:48',NULL),(6,'未命名','上一步','下一步','退出','完成','2017-02-26 15:08:14',NULL),(7,'未命名','上一步','下一步','退出','完成','2017-02-26 15:09:06',6),(8,'未命名','上一步','下一步','退出','完成','2017-03-01 15:56:27',6),(9,'未命名','上一步','下一步','退出','完成','2017-03-01 15:56:46',6),(10,'未命名','上一步','下一步','退出','完成','2017-03-01 15:59:00',6),(11,'未命名','上一步','下一步','退出','完成','2017-03-01 16:02:12',6),(12,'未命名','上一步','下一步','退出','完成','2017-03-01 16:02:31',6),(13,'未命名','上一步','下一步','退出','完成','2017-03-01 16:04:07',6),(14,'未命名','上一步','下一步','退出','完成','2017-03-01 16:04:12',6),(15,'未命名','上一步','下一步','退出','完成','2017-03-01 16:04:35',6),(16,'未命名','上一步','下一步','退出','完成','2017-03-01 16:05:41',6),(17,'未命名','上一步','下一步','退出','完成','2017-03-01 16:05:44',6),(18,'未命名','上一步','下一步','退出','完成','2017-03-01 16:05:45',6),(19,'未命名','上一步','下一步','退出','完成','2017-03-01 16:08:00',6),(20,'未命名','上一步','下一步','退出','完成','2017-03-01 16:10:23',6),(21,'未命名','上一步','下一步','退出','完成','2017-03-01 16:11:11',6),(22,'未命名','上一步','下一步','退出','完成','2017-03-01 16:13:35',6),(23,'未命名','上一步','下一步','退出','完成','2017-03-01 16:14:12',6),(24,'未命名','上一步','下一步','退出','完成','2017-03-01 16:14:15',6),(25,'未命名','上一步','下一步','退出','完成','2017-03-01 16:14:27',6),(26,'未命名','上一步','下一步','退出','完成','2017-03-01 16:15:19',6),(27,'未命名','上一步','下一步','退出','完成','2017-03-01 16:15:34',6),(28,'未命名','上一步','下一步','退出','完成','2017-03-01 16:20:50',6),(29,'未命名','上一步','下一步','退出','完成','2017-03-01 16:23:41',6),(30,'未命名','上一步','下一步','退出','完成','2017-03-01 16:24:16',6),(31,'未命名','上一步','下一步','退出','完成','2017-03-01 16:24:45',6),(32,'未命名','上一步','下一步','退出','完成','2017-03-01 16:24:54',6),(33,'未命名','上一步','下一步','退出','完成','2017-03-01 16:25:13',6),(34,'hello56565','上一步','下一步','退出','完成','2017-03-02 16:55:59',6),(35,'iiii','上一步','下一步','退出','完成','2017-03-05 14:20:00',6),(36,'未命名','上一步','下一步','退出','完成','2017-03-07 15:23:16',6),(37,'未命名','上一步','下一步','退出','完成','2017-03-07 15:23:20',6),(38,'未命名1','上一步','下一步','退出','完成','2017-03-08 15:12:15',6),(39,'未命名0000','上一步','下一步','退出','完成','2017-03-08 15:26:16',6);
/*!40000 ALTER TABLE `surveys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `regdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'q@163.com','123','stone',NULL),(2,'q@163.com','123','stone',NULL),(3,'q@163.com','123','stone',NULL),(4,'q@163.com','123','stone',NULL),(5,'q@163.com','123','stone',NULL),(6,'11','6512BD43D9CAA6E02C990B0A82652DCA','1',NULL),(7,'22','C81E728D9D4C2F636F067F89CC14862C','1',NULL),(8,'33','C81E728D9D4C2F636F067F89CC14862C','1',NULL),(9,'55','C81E728D9D4C2F636F067F89CC14862C','1',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'lsn_surveypark'
--

--
-- Dumping routines for database 'lsn_surveypark'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-09  0:43:50
