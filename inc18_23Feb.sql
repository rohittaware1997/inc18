-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: inc18
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `pid` int(11) NOT NULL,
  `pdid` int(11) DEFAULT NULL,
  `name1` varchar(255) NOT NULL,
  `name2` varchar(255) NOT NULL,
  `name3` varchar(255) NOT NULL,
  `name4` varchar(255) NOT NULL,
  `name5` varchar(255) NOT NULL,
  `college` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `pdid` (`pdid`),
  CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`pdid`) REFERENCES `project_domain` (`pdid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (3,5,'iii','sss','hhh','aaa','nnn','bits',''),(7,5,'a','','','','','vit','xyz'),(20,2,'twenty','','','','','twe','numbers'),(34,3,'vv','','','','','asklf',''),(444,1,'a','b','c','d','','pict','');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `judges`
--

DROP TABLE IF EXISTS `judges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `judges` (
  `jid` int(11) NOT NULL,
  `jname` varchar(255) NOT NULL,
  `jemail` varchar(255) DEFAULT NULL,
  `jcontact` varchar(10) NOT NULL,
  `max_marks` int(3) DEFAULT '0',
  PRIMARY KEY (`jid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `judges`
--

LOCK TABLES `judges` WRITE;
/*!40000 ALTER TABLE `judges` DISABLE KEYS */;
INSERT INTO `judges` VALUES (1,'Aditya','ad@gmail.com','992',45),(10,'ten','ten@ten.com','100',40),(11,'Aditay','ad@gmail.com','992',46),(12,'Ad','ad@gmail.com','992',48),(32,'Omkesh','om@gmail.com','889',30),(34,'Atharva','ath@gmail.com','7878',0),(67,'Ish','is@gmail.com','666',0);
/*!40000 ALTER TABLE `judges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marks` (
  `jid` int(4) NOT NULL,
  `pdid` int(2) NOT NULL,
  `pid` int(11) NOT NULL,
  `marks` int(3) DEFAULT NULL,
  `percentile` float DEFAULT '0',
  PRIMARY KEY (`jid`,`pdid`,`pid`),
  KEY `pdid` (`pdid`),
  KEY `pid` (`pid`),
  CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`jid`) REFERENCES `judges` (`jid`) ON DELETE CASCADE,
  CONSTRAINT `marks_ibfk_2` FOREIGN KEY (`pdid`) REFERENCES `project_domain` (`pdid`) ON DELETE CASCADE,
  CONSTRAINT `marks_ibfk_3` FOREIGN KEY (`pid`) REFERENCES `groups` (`pid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
INSERT INTO `marks` VALUES (1,3,3,40,88.8889),(1,3,34,12,26.6667),(1,4,444,30,66.6667),(1,5,3,34,75.5556),(1,6,3,45,100),(10,2,20,40,100),(11,2,20,45,97.8261),(11,3,3,46,100),(12,2,20,48,100),(32,2,20,30,100);
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_domain`
--

DROP TABLE IF EXISTS `project_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_domain` (
  `pdid` int(11) NOT NULL,
  `pname` varchar(255) NOT NULL,
  PRIMARY KEY (`pdid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_domain`
--

LOCK TABLES `project_domain` WRITE;
/*!40000 ALTER TABLE `project_domain` DISABLE KEYS */;
INSERT INTO `project_domain` VALUES (1,'a'),(2,'b'),(3,'c'),(4,'d'),(5,'e'),(6,'f'),(7,'g');
/*!40000 ALTER TABLE `project_domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `pdid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `percentile1` float DEFAULT '0',
  `percentile2` float DEFAULT '0',
  `percentile3` float DEFAULT '0',
  `avg_percentile` float DEFAULT '0',
  PRIMARY KEY (`pid`,`pdid`),
  KEY `pdid` (`pdid`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `groups` (`pid`),
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`pdid`) REFERENCES `project_domain` (`pdid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-23 17:43:08
