CREATE DATABASE  IF NOT EXISTS `bookworm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookworm`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: bookworm
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (8,' Richard A. Spencer'),(2,'Connie Ann Kirk'),(7,'Dan Brown'),(9,'David Macey'),(3,'Farha Ghannam'),(4,'Hanna Farha'),(5,'J. K. Rowling'),(12,'Paul G. Hiebert'),(10,'Peggy Gifford'),(1,'Richard A. Spencer'),(6,'Sam Farha'),(11,'Stan Berenstain');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) NOT NULL,
  `title` varchar(500) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `image_link` varchar(900) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  KEY `author_idx` (`author_id`),
  KEY `category_idx` (`category_id`),
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'9780307345769','The Da Vinci Code Travel Journal',7,7,'http://books.google.com/books/content?id=RJTPuVpEYpIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(2,'9781400079148','The Lost Symbol',7,8,'http://books.google.com/books/content?id=tCqI98qnVrcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(3,'9781524715847','The Da Vinci Code (The Young Adult Adaptation)',7,9,'http://books.google.com/books/content?id=TB0ZDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(4,'9780244003449','The Pale Blue Dot',7,5,'http://books.google.com/books/content?id=W-okDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(5,'9780313322051','J.K. Rowling',2,1,'http://books.google.com/books/content?id=GJgbW9c9mpwC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(6,'9780804787918','Live and Die Like a Man',3,6,'http://books.google.com/books/content?id=sVoTAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(7,'9781304266019','Facing & Defeating My Leukemia',4,3,'http://books.google.com/books/content?id=uJ46DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(8,'9781476621418','Harry Potter and the Classical World',8,4,'http://books.google.com/books/content?id=7HgwCgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(9,'9781617499203','Farha on Omaha',6,2,'http://books.google.com/books/content?id=cVcxgPfUy0AC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(10,'9781844678488','Frantz Fanon',9,1,'http://books.google.com/books/content?id=BdVRpzeA47YC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(11,'9780375891076','Moxy Maxwell Does Not Love Stuart Little',10,3,'http://books.google.com/books/content?id=qlc4PO2SR5cC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(12,'9780001712874','The Berenstains\' B Book',11,3,'http://books.google.com/books/content?id=rgbRAgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api'),(13,'9780816657872','Konduru',12,6,'http://books.google.com/books/content?id=HWhG7o_Uw3oC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `quantity` int(10) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `userdetails` int(11) NOT NULL,
  `paystatus` varchar(20) NOT NULL DEFAULT 'PENDING',
  `modeofpay` varchar(20) NOT NULL DEFAULT 'CASH',
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userdetails_idx` (`userdetails`),
  CONSTRAINT `userdetails_fk` FOREIGN KEY (`userdetails`) REFERENCES `userdetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,200,2,'2017-12-08 00:00:00',1,'COMPLETED','CASH','2017-12-08 00:00:00'),(2,180,2,'2017-12-10 00:00:00',2,'COMPLETED','CASH','2017-12-10 00:00:00'),(3,150,1,'2017-12-10 00:00:00',2,'COMPLETED','CASH','2017-12-10 00:00:00'),(4,109.25,2,'2017-12-10 00:00:00',4,'COMPLETED','CASH','2017-12-10 00:00:00'),(5,80,2,'2017-12-10 00:00:00',2,'COMPLETED','CASH','2017-12-10 00:00:00'),(6,45,1,'2017-12-10 00:00:00',4,'PENDING','CASH',NULL),(7,200,2,'2017-12-10 00:00:00',2,'PENDING','CASH','2017-12-10 00:00:00');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Biography & Autobiography'),(8,'Fiction'),(2,'Games'),(3,'Juvenile Fiction'),(4,'Literary Criticism'),(5,'Others'),(6,'Social Science'),(7,'Travel'),(9,'Young Adult Fiction');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (3,'Delivery Date'),(4,'HI Charlie'),(5,'Delivery Date'),(6,'Need 5 copies');
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_members`
--

DROP TABLE IF EXISTS `conversation_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `last_view` int(25) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_idx_idx` (`user_id`),
  KEY `c_idx_idx` (`conversation_id`),
  CONSTRAINT `c_idx` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_idx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_members`
--

LOCK TABLES `conversation_members` WRITE;
/*!40000 ALTER TABLE `conversation_members` DISABLE KEYS */;
INSERT INTO `conversation_members` VALUES (5,3,3,0,1512956138),(6,3,2,0,1512955864),(7,4,4,0,1512955970),(8,4,1,0,1512955047),(9,5,4,0,1512955977),(10,5,1,0,1512957839),(11,6,4,0,0),(12,6,2,0,1512956092);
/*!40000 ALTER TABLE `conversation_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `conversation_id` int(11) DEFAULT NULL,
  `date` int(11) DEFAULT NULL,
  `text` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `c_id_idx` (`conversation_id`),
  KEY `u_id_idx` (`user_id`),
  CONSTRAINT `c_id` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (6,2,3,1512954641,'Can you confirm the delivery date?'),(7,1,4,1512955043,'Please ensure delivery within 2 days.\r\nRegards \r\nAdmin'),(8,1,5,1512955951,'Please ensure book delivery of customer with 2 days.\r\nRegards, \r\nAdmin'),(9,2,6,1512956005,'Hi I need 5 copies.'),(10,2,6,1512956036,'When should i expect the books?'),(11,3,3,1512956133,'Yes, you will get it with 3 business days.');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modeofpay`
--

DROP TABLE IF EXISTS `modeofpay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modeofpay` (
  `MODE` varchar(20) NOT NULL DEFAULT 'CC',
  PRIMARY KEY (`MODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modeofpay`
--

LOCK TABLES `modeofpay` WRITE;
/*!40000 ALTER TABLE `modeofpay` DISABLE KEYS */;
INSERT INTO `modeofpay` VALUES ('CASH'),('CC'),('CHEQUE'),('DD'),('WALLET');
/*!40000 ALTER TABLE `modeofpay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart` int(11) NOT NULL,
  `sellerbookdetails` int(11) NOT NULL,
  `bookcount` int(11) NOT NULL DEFAULT '1',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_idx` (`sellerbookdetails`),
  KEY `orderdetails_idx` (`cart`),
  CONSTRAINT `orderdetails_fk7` FOREIGN KEY (`cart`) REFERENCES `cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `seller` FOREIGN KEY (`sellerbookdetails`) REFERENCES `seller_book_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,1,1,2,'2017-12-08 18:48:51','2017-12-08 00:00:00'),(2,2,1,1,'2017-12-10 19:37:28',NULL),(3,2,4,1,'2017-12-10 19:38:16',NULL),(4,3,2,1,'2017-12-10 19:40:24',NULL),(5,4,3,1,'2017-12-10 19:45:07',NULL),(6,5,9,1,'2017-12-10 19:59:12',NULL),(7,4,11,1,'2017-12-10 20:00:03',NULL),(8,6,11,1,'2017-12-10 20:03:35',NULL),(9,5,9,1,'2017-12-10 20:08:26',NULL),(10,7,2,1,'2017-12-10 20:09:12',NULL),(11,7,10,1,'2017-12-10 20:11:16',NULL);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentstatus`
--

DROP TABLE IF EXISTS `paymentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentstatus` (
  `status` varchar(20) NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentstatus`
--

LOCK TABLES `paymentstatus` WRITE;
/*!40000 ALTER TABLE `paymentstatus` DISABLE KEYS */;
INSERT INTO `paymentstatus` VALUES ('COMPLETED'),('IN PROCESS'),('PENDING'),('SCHEDULED');
/*!40000 ALTER TABLE `paymentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `review` varchar(500) DEFAULT NULL,
  `date_rated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `book_id_idx` (`book_id`),
  KEY `username_idx` (`user_id`),
  CONSTRAINT `bookid` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_idx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,4,1,5,'Amazing book! Loved it.','2017-12-08 19:01:43'),(2,2,3,5,'Awesome book!','2017-12-10 19:18:36'),(3,2,2,5,'Must read for all.','2017-12-10 19:58:39'),(4,2,6,4,'Great!','2017-12-10 20:08:58'),(5,2,5,4,'good book','2017-12-10 21:09:20'),(6,2,7,5,'wow','2017-12-10 21:09:45'),(7,4,13,4,'fantastic','2017-12-10 21:10:13'),(8,4,12,5,'Must read for everyone','2017-12-10 21:10:57'),(9,4,11,5,'just wow','2017-12-10 21:11:25'),(10,4,6,3,'okayish','2017-12-10 21:11:46');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_book_detail`
--

DROP TABLE IF EXISTS `seller_book_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_book_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `book_condition` varchar(45) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `book_id_idx` (`book_id`),
  KEY `user_seller_idx` (`user_id`),
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_book_detail`
--

LOCK TABLES `seller_book_detail` WRITE;
/*!40000 ALTER TABLE `seller_book_detail` DISABLE KEYS */;
INSERT INTO `seller_book_detail` VALUES (1,1,1,100,97,'New','2017-12-10 19:38:35'),(2,3,2,150,99,'Used - Very Good','2017-12-10 19:40:30'),(3,4,3,70,49,'Used - Good','2017-12-10 20:00:09'),(4,3,4,80,9,'Used - Acceptable','2017-12-10 19:38:35'),(5,4,5,100,10,'Used - Like New','2017-12-10 19:45:40'),(6,4,6,20,4,'New','2017-12-10 19:46:29'),(7,4,7,45,3,'Used - Good','2017-12-10 19:46:57'),(8,4,8,120,3,'New','2017-12-10 19:47:40'),(9,3,9,40,3,'New','2017-12-10 20:08:36'),(10,3,10,50,4,'New','2017-12-10 19:49:20'),(11,3,11,45,9,'Used - Acceptable','2017-12-10 20:43:17'),(12,3,6,90,6,'New','2017-12-10 20:01:40'),(13,1,12,56,4,'New','2017-12-10 20:16:31'),(14,1,13,90,7,'New','2017-12-10 20:20:13');
/*!40000 ALTER TABLE `seller_book_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraddress`
--

DROP TABLE IF EXISTS `useraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userdetails` int(11) NOT NULL,
  `street1` varchar(45) NOT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `isPrimary` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `userdetails_address_fk` (`userdetails`),
  CONSTRAINT `userdetails_address_fk` FOREIGN KEY (`userdetails`) REFERENCES `userdetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraddress`
--

LOCK TABLES `useraddress` WRITE;
/*!40000 ALTER TABLE `useraddress` DISABLE KEYS */;
INSERT INTO `useraddress` VALUES (1,1,'123','Main Street','Boston','MA','02215','Y'),(2,1,'123','Main Street','New York','NY','02215','N'),(3,2,'345','Main Street','Boston','MA','02215','Y'),(4,3,'456','Main Street','Boston','MA','02215','Y'),(5,4,'678','Main Street','Boston','MA','02215','Y');
/*!40000 ALTER TABLE `useraddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int(11) NOT NULL,
  `displaypicture` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES (1,'Genie','Potter','1999-01-01 00:00:00','farha.j@husky.neu.edu',123456789,NULL),(2,'Alice','Wonderland','2002-02-13 00:00:00','a.wonderland@gmail.com',123334434,NULL),(3,'Bob','Marley','1999-01-01 00:00:00','bob.marley@gmail.com',123234345,NULL),(4,'Charlie','Gracia','1999-01-01 00:00:00','c.gracia@gmail.com',12332234,NULL);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `userrole` varchar(45) NOT NULL DEFAULT 'USER',
  `enabled` varchar(45) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userdetails_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$P6.XNgJOfkrbF/ANLGw7ZO5KjGEE0ejqCuBTfYgLHpHjarnFBa6ka','ADMIN','1','2017-12-08 00:00:00','2017-12-08 00:00:00'),(2,'alice','$2a$10$P6QgitMEKOIMAMN3bzIrwO/iTQM/n48e21prDEDOABKDNsdQ98khC','BUYER','1','2017-12-08 00:00:00','2017-12-08 00:00:00'),(3,'bob','$2a$10$mHWdZAPlviiWg.Vz77YmhOY1pucHGlWeEA9AazP0Es1Bzi8rubXB6','SELLER','1','2017-12-08 00:00:00','2017-12-08 00:00:00'),(4,'charlie','$2a$10$L4dQNk6Y7zc7ycwJN.5gaObtvWpjXyNi3GQ63jfWvnltuulFzjU3u','PRIME','1','2017-12-08 00:00:00','2017-12-08 00:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userdetails` int(11) NOT NULL,
  `sellerbookdetails` int(11) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userdetails_fk1` (`userdetails`),
  KEY `sellerbookdetials_wishlist_fk` (`sellerbookdetails`),
  CONSTRAINT `sellerbookdetials` FOREIGN KEY (`sellerbookdetails`) REFERENCES `seller_book_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userdetails_fk1` FOREIGN KEY (`userdetails`) REFERENCES `userdetails` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,4,1,'2017-12-08 00:00:00'),(2,2,2,'2017-12-10 00:00:00'),(3,4,3,'2017-12-10 00:00:00'),(4,2,6,'2017-12-10 00:00:00'),(5,2,7,'2017-12-10 00:00:00');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11  0:15:52
