CREATE DATABASE  IF NOT EXISTS `courseonline` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `courseonline`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: courseonline
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `content` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (2,'Tin tức 1','image1.jpg','Nội dung tin tức 1','2023-12-27 18:32:35'),(3,'Tin tức 2','image2.jpg','Nội dung tin tức 2','2023-12-27 18:32:35'),(4,'Tin tức 3','image3.jpg','Nội dung tin tức 3','2023-12-27 18:32:35'),(5,'Tin tức 4','image4.jpg','Nội dung tin tức 4','2023-12-27 18:32:35'),(6,'Tin tức 5','image5.jpg','Nội dung tin tức 5','2023-12-27 18:32:35'),(7,'Tin tức 6','image6.jpg','Nội dung tin tức 6','2023-12-27 18:32:35'),(8,'Tin tức 7','image7.jpg','Nội dung tin tức 7','2023-12-27 18:32:35'),(9,'Tin tức 8','image8.jpg','Nội dung tin tức 8','2023-12-27 18:32:35'),(10,'Tin tức 9','image9.jpg','Nội dung tin tức 9','2023-12-27 18:32:35'),(11,'Tin tức 10','image10.jpg','Nội dung tin tức 10','2023-12-27 18:32:35'),(12,'Tin tức 11','image11.jpg','Nội dung tin tức 11','2023-12-27 18:32:35'),(13,'Tin tức 12','image12.jpg','Nội dung tin tức 12','2023-12-27 18:32:35'),(14,'Tin tức 13','image13.jpg','Nội dung tin tức 13','2023-12-27 18:32:35'),(15,'Tin tức 14','image14.jpg','Nội dung tin tức 14','2023-12-27 18:32:35'),(16,'Tin tức 15','image15.jpg','Nội dung tin tức 15','2023-12-27 18:32:35'),(17,'Tin tức 16','image16.jpg','Nội dung tin tức 16','2023-12-27 18:32:35'),(18,'Tin tức 17','image17.jpg','Nội dung tin tức 17','2023-12-27 18:32:35');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartsession`
--

DROP TABLE IF EXISTS `cartsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartsession` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `updatedate` datetime(6) DEFAULT NULL,
  `productid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productid` (`productid`),
  KEY `userid` (`userid`),
  CONSTRAINT `cartsession_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `courses` (`id`),
  CONSTRAINT `cartsession_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartsession`
--

LOCK TABLES `cartsession` WRITE;
/*!40000 ALTER TABLE `cartsession` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartsession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (13,'Lập trình web','2023-12-14 10:12:41'),(14,'Java','2023-12-14 10:12:58'),(17,'Quản lý dự án','2023-12-14 10:14:38'),(18,'Kỹ năng lập trình C, C++','2023-12-14 10:14:45'),(20,'Mobile','2023-12-14 10:36:42'),(21,'Marketing','2023-12-14 10:39:41'),(22,'Toán','2024-01-12 10:08:30'),(23,'Tiếng anh','2024-01-18 11:12:48');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `courseid` int DEFAULT NULL,
  `contents` text,
  `createddate` date DEFAULT NULL,
  `status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (25,13,20,'quas hayyyyyyyy','2023-12-21',_binary ''),(26,13,21,'u la troi, qua tuyet voi','2023-12-29',_binary '\0'),(27,17,20,'ok','2024-01-16',_binary '\0');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursedetail`
--

DROP TABLE IF EXISTS `coursedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursedetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  `courseid` int DEFAULT NULL,
  `chapter` int DEFAULT NULL,
  `url` text,
  `exercise` text,
  `exercisesolution` text,
  `createddate` datetime(6) DEFAULT NULL,
  `updatedate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `coursedetail_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursedetail`
--

LOCK TABLES `coursedetail` WRITE;
/*!40000 ALTER TABLE `coursedetail` DISABLE KEYS */;
INSERT INTO `coursedetail` VALUES (17,'Giới thiệu ngôn ngữ lập trình Python',20,1,'NZj6LI5a9vc?si=TFIZQ8v5Zt3w2UA1',NULL,NULL,'2023-12-14 10:43:53.946000',NULL),(18,'Cài đặt môi trường Python',20,2,'jf-q_dG8WzI?si=h6D-NFyRloKM4pJ1',NULL,NULL,'2023-12-14 10:44:27.145000',NULL),(19,'Chạy file Python',20,3,'QFxqY8qv42E?si=MFwUWfLBYui1IzYB',NULL,NULL,'2023-12-14 10:44:58.140000',NULL),(20,'Comment trong Python',20,4,'t3dERE9T5yg?si=XRLFY-Iic9cL9uvS',NULL,NULL,'2023-12-14 10:45:27.194000',NULL),(21,'Biến(Variable) trong Python',20,5,'nclE18Yl-kA?si=hx44pyoIGxXrMsXz',NULL,NULL,'2023-12-14 10:48:40.109000',NULL),(22,'Kiểu số trong Python',20,6,'IAVvgqDBiv0?si=Eg7dNn1z_6Nok4A0',NULL,NULL,'2023-12-14 10:49:06.961000',NULL),(23,'Kiểu chuỗi trong Python - Phần 1',20,7,'Vb6XWSLPQfg?si=01Cjgwtz9o0wm85C',NULL,NULL,'2023-12-14 10:49:44.865000',NULL),(24,'Kiểu chuỗi trong Python - Phần 2',20,8,'gzWriEOVjU0?si=UCzi9nM9t876Yqb9',NULL,NULL,'2023-12-14 10:50:55.291000',NULL),(25,'Kiểu chuỗi trong Python - Phần 3',20,9,'LRUHnuHljPQ?si=v0RKmyE0ZYCb0GUI',NULL,NULL,'2023-12-14 10:51:32.969000',NULL),(26,'Kiểu chuỗi trong Python - Phần 4',20,10,'q2TNJMBx6GE?si=IROulsFyxYese05E',NULL,NULL,'2023-12-14 10:53:16.866000',NULL),(27,'Kiểu chuỗi trong Python - Phần 5',20,11,'u2Kd3weqPZE?si=Z6ELV-MZkY811a3c',NULL,NULL,'2023-12-14 10:53:44.152000',NULL),(28,'List trong Python - Phần 1',20,12,'UzTE665WXb8?si=wZ8YRQITIJVa1MAy',NULL,NULL,'2023-12-14 10:54:16.208000',NULL),(29,'List trong Python - Phần 2 ',20,13,'9IH3EynbcpU?si=Erog5vVHQb9ZnTNO',NULL,NULL,'2023-12-14 10:54:54.171000',NULL),(30,'Tuple trong Python',20,14,'dDFFCbRGm3o?si=5gLZNknoiR3deRiE',NULL,NULL,'2023-12-14 10:55:35.863000',NULL),(31,'Hashable và Unhashable trong Python',20,15,'gw9zbl2Q7r4?si=iE9AeeG8tsVdp1oT',NULL,NULL,'2023-12-14 10:56:08.611000',NULL),(32,'Set trong Python',20,16,'S-CWHkKiOBs?si=OrR28vdO04KLNCWE',NULL,NULL,'2023-12-14 10:56:42.285000',NULL),(33,' Dict trong Python - Phần 1',20,17,'zFDTmZjJFws?si=aaMWtIu1B2eYGtXG',NULL,NULL,'2023-12-14 10:58:47.919000',NULL),(34,'Tổng quan khóa học',21,0,'opmSs9qMFik?si=BD-TF83xhNqxJL3S',NULL,NULL,'2023-12-14 11:00:07.181000',NULL),(35,'Tổng quan HTML',21,1,'cA_My615iFk?si=J9DuJdvUC1mKweME',NULL,NULL,'2023-12-14 11:01:01.729000',NULL),(36,'Tổng quan CSS',21,2,'z699narBw_A?si=LT6UTpVXiRw9LyBA',NULL,NULL,'2023-12-14 11:01:24.878000',NULL),(37,'Cách dùng màu trong CSS',21,3,'ASpHS7BPZWA?si=OdY1CXc6WeyfF_FR',NULL,NULL,'2023-12-14 11:01:42.934000',NULL),(38,'CSS model box ',21,4,'pnIcgkTDycY?si=ZCYVinGJWdummu9a',NULL,NULL,'2023-12-14 11:02:03.939000',NULL),(39,'ID, class và float',21,5,'Pr58FL8fvK4?si=dVaARGNjQfaHM__M',NULL,NULL,'2023-12-14 11:02:24.105000',NULL),(40,'Position trong CSS',21,6,'4EhbMESZZeo?si=aqmUDEmsdwa8nTaf',NULL,NULL,'2023-12-14 11:02:44.088000',NULL),(41,'Hoàn thiện trang blog',21,7,'nJ7vdNg3PuM?si=dKWJLLVnsZFwKW46',NULL,NULL,'2023-12-14 11:03:09.100000',NULL),(42,'Wireframe và tạo folder',21,8,'0_cNsMduFkU?si=aXC1k7ZsaOMo2AZ9',NULL,NULL,'2023-12-14 11:03:29.388000',NULL),(43,'Setup HTML và CSS',21,9,'axAmEp5s6XA?si=VtCm9S-2-FxLk2rU',NULL,NULL,'2023-12-14 11:04:25.725000',NULL),(44,'Coding header',21,10,'9BpFPfxr1IM?si=m60ChPq2NPyTqyfP',NULL,NULL,'2023-12-14 11:07:30.932000',NULL),(45,'Coding header P2',21,11,'-HtytR5lfDA?si=V0zqinfq0sKkkfma',NULL,NULL,'2023-12-14 11:09:52.082000',NULL),(46,'Giới thiệu về lộ trình học ruby, ruby',22,1,'hIp0iaeDpDU?si=U9tB4UdGo_G6jz7O',NULL,NULL,'2023-12-14 11:11:07.419000',NULL),(47,'Cách cài ruby trên máy tính',22,2,'40QE3xx7W8w?si=zsuVxxd2I_EdlQOB',NULL,NULL,'2023-12-14 11:11:28.636000',NULL),(48,'Quản lý phiên bản ruby',22,3,'zZygHyH1XM0?si=OugDZCASEmdvTI4D',NULL,NULL,'2023-12-14 11:12:04.915000',NULL),(49,'Biến trong ruby',22,4,'En3ED_ZuGTc?si=kulUgJnNcod3LhYI',NULL,NULL,'2023-12-14 11:13:15.857000',NULL),(50,' in ra màn console',22,5,'ef4u-AGBYzc?si=NZBIrwM96DdKiVbl',NULL,NULL,'2023-12-14 11:13:37.555000',NULL),(51,'Nhập dữ liệu từ bàn phím với màn hình console trong ruby',22,6,'qCLHupjGtMk?si=GwkbttLRVdZBduLh',NULL,NULL,'2023-12-14 11:14:03.376000',NULL),(52,'biến trong ruby - các loại biến - phạm vi truy cập của biến ruby',22,7,'nUTzlCLd0pM?si=rbee_lCN1SeQFXhZ',NULL,NULL,'2023-12-14 11:14:50.339000',NULL),(53,' ruby string - kiểu dữ liệu string trong ruby - ruby string datatype',22,8,'q94sTuHcnbE?si=wwlLC9ynVfV4hlr2',NULL,NULL,'2023-12-14 11:15:09.430000',NULL),(54,'ruby string interpolation - ruby nội suy - nội suy trong ruby',22,9,'BEMy2PWAfTE?si=uyEHncw-Q6mqUXWZ',NULL,NULL,'2023-12-14 11:15:30.071000',NULL),(55,'Thao Tác chuỗi.',22,10,'ku9NZncD0h8?si=awe5XxxFrE0ORlO1',NULL,NULL,'2023-12-14 11:15:50.484000',NULL),(56,'Cắt chuỗi ruby | ruby sub method | string substitution',22,11,'dOqCDid6Ct0?si=0ygjE9zwHZci3pP2',NULL,NULL,'2023-12-14 11:16:11.298000',NULL),(57,'Giới thiệu về C++',23,1,'ILr_rU-lISk?si=J8AMdgUNFzR6I6pw',NULL,NULL,'2023-12-14 11:17:06.875000',NULL),(58,'Xây dựng chương trình C++ đầu tiên',23,2,'q8UGqw6Cbnk?si=sW0DfN9mbfiOp7hB',NULL,NULL,'2023-12-14 11:17:35.472000',NULL),(59,'Cấu trúc một chương trình C++',23,3,'IXzH3pYM_bg?si=S929DcFYPYgwXNvZ',NULL,NULL,'2023-12-14 11:17:55.391000',NULL),(60,'Kinh nghiệm về Ghi chú trong C++',23,4,'Z-laY5MmTIE?si=wzZA7N8jfK_LDoOC',NULL,NULL,'2023-12-14 11:18:17.299000',NULL),(61,'Biến trong C++',23,5,'i3nJyEt42Y8?si=AEtBl_H2LahwSe__',NULL,NULL,'2023-12-14 11:18:36.398000',NULL),(62,'Số tự nhiên và Số chấm động trong C++',23,6,'wE3WhKbExN8?si=3PXkgu5S_BSanL5C',NULL,NULL,'2023-12-14 11:18:53.625000',NULL),(63,'Kiểu ký tự trong C++',23,7,'xt5rScAb9lI?si=UF7hW25ZvJXJXXvr',NULL,NULL,'2023-12-14 11:19:11.362000',NULL),(64,'Kiểu Boolean và Câu điều kiện If',23,8,'3-G-MLXj5n0?si=6dG-t3oJebvRd1mi',NULL,NULL,'2023-12-14 11:19:33.506000',NULL),(65,'Nhập, Xuất và Định dạng dữ liệu trong C++',23,9,'Xh-gwtPCicU?si=j2F-sdwtkkao3Mhi',NULL,NULL,'2023-12-14 11:19:56.872000',NULL),(66,'Hằng số trong C++',23,10,'XZsSpuQEn5A?si=BdDZqR7BwrYs-TKI',NULL,NULL,'2023-12-14 11:20:18.546000',NULL),(67,'Giới thiệu về phương pháp luận Agile - Ưu điểm so với Waterfall',24,1,'_LAMT71Xdnc?si=jnQcylgPjf-9HeDX',NULL,NULL,'2023-12-14 11:21:20.588000',NULL),(68,'Tuyên ngôn của Agile',24,2,'tWbzF1Y80CI?si=wTL5NLI9lFVq6KEZ',NULL,NULL,'2023-12-14 11:21:41.678000',NULL),(69,'Định nghĩa về Scrum',24,3,'BEpRWPNxVPM?si=E1nLMJLFvWD0eN6H',NULL,NULL,'2023-12-14 11:22:04.438000',NULL),(70,'Ba trụ cột của Scrum',24,4,'ede3CKkPuG8?si=4QjK2joZawpLh8Zm',NULL,NULL,'2023-12-14 11:22:19.968000',NULL),(71,' Giới thiệu về Nhóm Scrum (Scrum Team) - Các tính chất của Scrum Team',24,5,'eFN10MQqaxY?si=3dkIrEa7_h361Ct2',NULL,NULL,'2023-12-14 11:22:46.958000',NULL),(72,'Product Owner là gì - Vai trò của Product Owner',24,6,'MIHXmI1tjco?si=b6YehlD0mrmh8kmx',NULL,NULL,'2023-12-14 11:23:15.506000',NULL),(73,'Định nghĩa Development Team - Tính chất và vai trò của Development Team',24,7,'uFQb4HK2WjE?si=qE0t9M9iz8PSdfXc',NULL,NULL,'2023-12-14 11:23:38.075000',NULL),(74,'Scrum Master là gì - Tính chất và vai trò của Scrum Master',24,8,'-gFEIZe2c8o?si=dko7y6_quDwEUwLX',NULL,NULL,'2023-12-14 11:23:55.014000',NULL),(75,'Product Backlog - User Story và Epic',24,9,'l-8ULKfaNZA?si=2MlbpqryGPZf553M',NULL,NULL,'2023-12-14 11:24:15.408000',NULL),(76,' Definition of Done - Định nghĩa về sự hoàn thành',24,10,'EixWVpbJFcQ?si=tcTwMuUo5lIah9e0',NULL,NULL,'2023-12-14 11:24:30.415000',NULL),(77,'App đầu tiên',25,1,'_3sQ0nEIR8g?si=7I02-QlfO2GvAm9A',NULL,NULL,'2023-12-14 11:25:02.567000',NULL),(78,'SpaceShip App',25,2,'Pkav5_ZW1Vk?si=GGGbE4-XM5nN0w8C',NULL,NULL,'2023-12-14 11:25:19.974000',NULL),(79,'Biến và Toán tử',25,3,'5P2TNsnUhZc?si=zAJI0FkhOYgekxXD',NULL,NULL,'2023-12-14 11:25:34.978000',NULL),(80,'Xử lý chuỗi (String)',25,4,'ne9HdyTtCWo?si=uyvc1yhCLAltbkXc',NULL,NULL,'2023-12-14 11:25:49.501000',NULL),(81,'Làm việc với số',25,5,'-Z4azPumKuU?si=TjWkqSYt2tj6A8TQ',NULL,NULL,'2023-12-14 11:26:13.455000',NULL),(82,'Boolean và xử lý điều kiện',25,6,'st2DjKWkEhY?si=r9Co7fwWPta3sqLl',NULL,NULL,'2023-12-14 11:26:35.901000',NULL),(83,'Hằng và Biểu thức điều kiện nâng cao',25,7,'kUVAL3cJHzY?si=-RCPnDgdCi9-jnMO',NULL,NULL,'2023-12-14 11:27:14.678000',NULL),(84,'Mảng - Arrays',25,8,'r81m1jDBcuw?si=FZJgYa8LLoPfWQZM',NULL,NULL,'2023-12-14 11:27:39.752000',NULL),(85,'Vòng lặp - Loops',25,9,'pcMX0rWXhac?si=fQwTbQr1nng-9YDO',NULL,NULL,'2023-12-14 11:28:04.084000',NULL),(86,'Dictionary',25,10,'x52UL91N4F0?si=RNJLF24UfCsgsds6',NULL,NULL,'2023-12-14 11:28:20.606000',NULL),(87,'toan 1',29,1,'K9_vVDdYcwQ',NULL,NULL,'2023-12-21 10:17:10.324000',NULL),(88,'Yes sir',29,2,'213123123',NULL,NULL,'2023-12-29 11:16:06.438000',NULL),(89,'ADu vip',30,1,'sadasd',NULL,NULL,'2024-01-12 10:06:20.777000',NULL),(90,'Toán 1 + 1',31,1,'NZj6LI5a9vc?si=TFIZQ8v5Zt3w2UA1',NULL,NULL,'2024-01-16 20:54:55.719000',NULL),(91,'Toán nâng cao',32,1,'mkn36ftfURs?si=dAAiSpoLOSAaf3Fz',NULL,NULL,'2024-01-18 10:27:00.094000',NULL),(92,'Toán 1',32,2,'mkn36ftfURs?si=1W449-zIhv2wME8H',NULL,NULL,'2024-01-18 10:27:33.291000',NULL),(93,'tieng anh  nhap mon',33,1,'NZj6LI5a9vc?si=TFIZQ8v5Zt3w2UA1',NULL,NULL,'2024-01-18 11:14:25.953000',NULL),(94,'tieng anh 2',33,1,'CdZN8PI3MqM?si=Xuu6Hs7g-SxuzN8m',NULL,NULL,'2024-01-18 11:15:44.289000',NULL),(95,'TOAN TAP 1',35,1,'NZj6LI5a9vc?si=TFIZQ8v5Zt3w2UA1',NULL,NULL,'2024-01-18 13:15:29.915000',NULL),(96,'TOAN TAP 2',35,1,'CdZN8PI3MqM?si=Xuu6Hs7g-SxuzN8m',NULL,NULL,'2024-01-18 13:15:39.000000',NULL);
/*!40000 ALTER TABLE `coursedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coursename` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `image` text,
  `descriptions` text,
  `createddate` datetime(6) DEFAULT NULL,
  `updatedate` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `categoryid` int DEFAULT NULL,
  `status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `categoryid` (`categoryid`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `categories` (`id`),
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (20,'Lập trình Python cơ bản',290000,'ae62cb8a.png','Khóa học giúp bạn học lập trình Python từ cơ bản đến nâng cao.','2023-12-14 10:24:00.106000','2024-01-12 10:27:02.381000',6,13,_binary '\0'),(21,'Lập trình Front-end',300000,'694b3acf.png','Học cách phát triển giao diện người dùng cho trang web.','2023-12-14 10:25:35.545000','2024-01-09 10:33:04.992000',17,13,_binary '\0'),(22,'Lập trình Ruby',250000,'3fcc56e1.png','Học lập trình Ruby và phát triển ứng dụng web.','2023-12-14 10:29:18.871000','2024-01-09 10:36:47.681000',6,18,_binary '\0'),(23,'Kỹ năng lập trình C++',230000,'2edc9091.png','Học lập trình C++ từ cơ bản đến nâng cao và phát triển ứng dụng.','2023-12-14 10:31:01.490000','2023-12-14 10:31:01.490000',6,18,_binary '\0'),(24,'Lập trình Python cơ bản',250000,'74402aaa.png','Khóa học giúp bạn học lập trình Python từ cơ bản đến nâng cao.	','2023-12-14 10:34:22.227000','2024-01-12 10:11:27.124000',6,17,_binary '\0'),(25,'Lập trình Swift',270000,'c8d97ecf.png','Học lập trình Swift cho ứng dụng di động trên nền tảng iOS.','2023-12-14 10:37:31.954000','2023-12-14 10:37:31.954000',6,20,_binary '\0'),(26,'Kỹ năng lập trình Java',210000,'4c0bbb34.png','Học lập trình Java từ cơ bản đến nâng cao và phát triển ứng dụng.','2023-12-14 10:38:40.850000','2023-12-14 10:38:40.850000',6,14,_binary '\0'),(27,'Digital Marketing',200000,'11e1b390.png','Học cách tiếp thị trực tuyến và quảng cáo trên mạng xã hội.','2023-12-14 10:40:19.623000','2023-12-14 10:40:19.623000',6,21,_binary '\0'),(28,'Lập trình Android',240000,'f0ba003c.png','Học lập trình Android cho ứng dụng di động trên nền tảng Android.','2023-12-14 10:42:03.332000','2024-01-09 10:41:31.677000',6,20,_binary '\0'),(29,'toan',120000,'ec70cc1.jpg','Toán hay','2023-12-21 10:15:56.423000','2024-01-18 10:22:10.452000',13,22,_binary '\0'),(30,'Toán',450000,'fbb80f26.jpg','toán hay vl','2024-01-12 10:00:51.388000','2024-01-18 12:47:00.412000',17,22,_binary '\0'),(31,'Toán cao cấp',100000,'9007504.jpg','Dạy toán cao ấp','2024-01-16 20:54:38.923000','2024-01-18 10:59:10.017000',17,22,_binary '\0'),(32,'Toán cơ bản - DEMO QUIZ',320000,'2f727bc8.jpg','Toán hay','2024-01-17 14:25:01.051000','2024-01-18 10:59:21.003000',17,22,_binary '\0'),(33,'Tiếng anh cao cấp',125000,'a9e41267.png','tieng anh thay ethan','2024-01-18 11:13:25.761000','2024-01-18 11:23:48.567000',17,23,_binary '\0'),(34,'Tiếng anh 2',200000,'817f241c.png','Tiếng anh 222','2024-01-18 11:22:24.031000','2024-01-18 11:22:24.031000',17,23,_binary '\0'),(35,'TOAN CAO CAP 12',540000,'86cda484.png','TOAN CAO CAP THAY NGUYEN QUOC CHI','2024-01-18 13:14:42.846000','2024-01-18 13:15:05.522000',17,22,_binary '\0');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursevideos`
--

DROP TABLE IF EXISTS `coursevideos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursevideos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chapter` int NOT NULL,
  `url` text,
  `courseid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkjibu6rq3i6vj6k1bc6nmxexa` (`courseid`),
  CONSTRAINT `FKkjibu6rq3i6vj6k1bc6nmxexa` FOREIGN KEY (`courseid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursevideos`
--

LOCK TABLES `coursevideos` WRITE;
/*!40000 ALTER TABLE `coursevideos` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursevideos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `orderid` int DEFAULT NULL,
  `productid` int DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`),
  KEY `productid` (`productid`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (14,250000,9,20,0),(15,250000,10,20,0),(20,300000,12,21,0),(21,250000,12,20,0),(22,230000,12,23,0),(23,250000,12,22,0),(24,300000,13,21,0),(25,250000,13,20,0),(26,230000,13,23,0),(27,250000,13,22,0),(28,300000,14,21,0),(29,250000,14,20,0),(30,120000,15,29,0),(31,120000,16,29,0),(32,250000,17,20,0),(33,300000,18,21,0),(34,250000,19,22,0),(35,250000,20,22,0),(36,270000,20,25,0),(37,250000,20,24,0),(38,230000,20,23,0),(39,250000,21,22,0),(40,270000,21,25,0),(41,250000,21,24,0),(42,320000,23,32,0),(43,125000,24,33,0),(44,200000,25,34,0),(45,290000,26,20,0),(46,290000,27,20,0);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `phonenumber` varchar(10) DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `statusorder` int NOT NULL,
  `voucher` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (9,250000,'2023-12-14 10:46:03.948000','',12,1,NULL),(10,250000,'2023-12-21 13:58:05.604000','0123456789',13,1,NULL),(12,1030000,'2023-12-21 19:02:39.279000','0123456789',15,2,NULL),(13,1030000,'2023-12-21 19:03:53.966000','0123456789',15,1,NULL),(14,550000,'2023-12-29 10:30:49.687000','0252323124',3,1,NULL),(15,120000,'2023-12-29 11:28:07.439000','0252323124',3,1,NULL),(16,120000,'2024-01-11 10:57:42.590000','0328383680',17,1,0),(17,250000,'2024-01-11 11:13:52.415000','0328383680',17,1,0),(18,270000,'2024-01-12 09:56:50.158000','0328383680',17,1,1),(19,250000,'2024-01-12 09:59:20.615000','0328383680',17,0,0),(20,1000000,'2024-01-12 10:56:01.294000','0328383680',17,0,0),(21,770000,'2024-01-12 10:56:19.816000','0328383680',17,1,0),(22,0,'2024-01-16 21:00:18.082000','0328383680',17,0,0),(23,320000,'2024-01-18 10:32:43.800000','0328383680',17,1,0),(24,125000,'2024-01-18 11:16:30.749000','0328383680',17,1,0),(25,200000,'2024-01-18 11:22:42.892000','0328383680',17,1,0),(26,290000,'2024-01-18 13:09:43.403000','0328383680',21,0,0),(27,290000,'2024-01-18 13:09:58.264000','0328383680',21,1,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coursedetailid` int DEFAULT NULL,
  `question` text,
  `answer_a` text,
  `answer_b` text,
  `answer_c` text,
  `answer_d` text,
  `answer` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `coursedetailid` (`coursedetailid`),
  CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`coursedetailid`) REFERENCES `coursedetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (12,87,'Ai là học sinh ngoan','tao','mày','bạn','cả 3','a'),(13,87,'Ai là người tốt','add','ddd','ddđ','ddddd','b'),(15,87,'DSASDVSDVNZVC','adddsdXzcZC','ddddddccc','ddđccccc','ddddcccCSDSDF','c'),(16,87,'DSASDVSDVNZVxzczxczxc','adddsdXzcZCzczc','ddddddccczcz','ddđcccccczczcz','ddddcccCSDSDFzczc','d'),(17,87,'DSAx','adddx','xxxxxx','ddđccccx','ddddcccxSDFx','a'),(20,17,'Ai là tác giả chính của ngôn ngữ lập trình Python?','Guido van Rossum (/)','Larry Page','Linus Torvalds','Mark Zuckerberg','a'),(21,87,'Ai thông minh','Lai','Lai','Lai','Lai','a'),(22,88,'asd','a','a','a','a','a'),(23,89,'Lai ngu nhất','Đúng(đúng)','Đúng','Đúng','Sai','a'),(24,17,'Python là ngôn ngữ lập trình nào?','C++','Java','Python','Ruby','c'),(25,17,'Python có kiểu dữ liệu nào sau đây?','Integer','Float','String','Tất cả đều đúng','d'),(26,17,'Cú pháp in ra màn hình trong Python là gì?','print(\"Hello, World!\") (/)','echo \"Hello, World!\"','console.log(\"Hello, World!\")','printf(\"Hello, World!\")','a'),(27,18,'Cài đặt Python có thể thực hiện bằng cách nào?','Sử dụng trình quản lý gói pip (/)','Tải mã nguồn và biên dịch thủ công','Cài đặt từ ứng dụng App Store','Chỉ có thể cài đặt trên hệ điều hành Linux','a'),(28,18,'Phương tiện nào thường được sử dụng để tạo và quản lý môi trường ảo trong Python?','Conda(/)','Maven','Npm','Gradle','a'),(29,18,'Để kiểm tra xem Python đã được cài đặt thành công hay chưa, bạn có thể sử dụng lệnh nào trong dòng lệnh?','check python','python --version (/)','verify python','test python','b'),(30,19,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(31,19,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(32,19,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(33,20,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(34,20,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(35,20,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(36,21,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(37,21,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(38,21,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(39,22,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(40,22,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(41,22,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(42,23,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(43,23,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(44,23,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(45,24,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(46,24,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(47,24,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(48,25,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(49,25,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(50,25,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(51,26,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(52,26,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(53,26,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(54,27,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(55,27,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(56,27,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(57,28,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(58,28,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(59,28,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(60,29,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(61,29,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(62,29,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(63,30,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(64,30,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(65,30,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(66,31,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(67,31,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(68,31,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(69,32,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(70,32,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(71,32,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(72,33,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(73,33,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(74,33,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(75,34,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(76,34,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(77,34,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(78,35,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(79,35,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(80,35,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(81,36,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(82,36,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(83,36,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(84,37,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(85,37,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(86,37,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(87,38,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(88,38,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(89,38,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(90,39,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(91,39,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(92,39,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(93,40,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(94,40,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(95,40,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(96,41,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(97,41,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(98,41,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(99,42,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(100,42,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(101,42,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(102,43,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(103,43,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(104,43,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(105,44,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(106,44,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(107,44,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(108,45,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(109,45,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(110,45,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(111,46,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(112,46,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(113,46,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(114,47,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(115,47,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(116,47,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(117,48,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(118,48,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(119,48,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(120,49,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(121,49,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(122,49,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(123,50,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(124,50,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(125,50,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(126,51,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(127,51,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(128,51,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(129,52,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(130,52,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(131,52,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(132,53,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(133,53,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(134,53,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(135,54,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(136,54,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(137,54,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(138,55,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(139,55,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(140,55,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(141,56,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(142,56,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(143,56,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(144,57,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(145,57,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(146,57,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(147,58,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(148,58,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(149,58,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(150,59,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(151,59,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(152,59,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(153,60,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(154,60,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(155,60,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(156,61,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(157,61,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(158,61,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(159,62,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(160,62,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(161,62,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(162,63,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(163,63,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(164,63,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(165,64,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(166,64,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(167,64,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(168,65,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(169,65,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(170,65,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(171,66,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(172,66,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(173,66,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(174,67,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(175,67,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(176,67,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(177,68,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(178,68,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(179,68,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(180,69,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(181,69,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(182,69,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(183,70,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(184,70,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(185,70,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(186,71,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(187,71,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(188,71,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(189,72,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(190,72,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(191,72,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(192,73,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(193,73,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(194,73,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(195,74,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(196,74,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(197,74,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(198,75,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(199,75,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(200,75,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(201,76,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(202,76,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(203,76,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(204,77,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(205,77,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(206,77,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(207,78,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(208,78,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(209,78,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(210,79,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(211,79,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(212,79,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(213,80,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(214,80,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(215,80,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(216,81,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(217,81,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(218,81,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(219,82,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(220,82,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(221,82,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(222,83,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(223,83,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(224,83,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(225,84,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(226,84,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(227,84,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(228,85,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(229,85,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(230,85,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(231,86,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(232,86,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(233,86,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(234,87,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(235,87,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(236,87,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(237,88,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(238,88,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(239,88,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(240,89,'Câu hỏi 1','Đáp án A1','Đáp án B1','Đáp án C1','Đáp án D1','A'),(241,89,'Câu hỏi 2','Đáp án A2','Đáp án B2','Đáp án C2','Đáp án D2','B'),(242,89,'Câu hỏi 3','Đáp án A3','Đáp án B3','Đáp án C3','Đáp án D3','C'),(243,90,'1 + 1 =?','1','2','3','4','b'),(244,91,'1 + 1','1','2','3','4','b'),(245,91,'2+2','1','2','3','4','d'),(246,92,'1+1','1+1','1','3','4','a'),(247,92,'4-1','1','2','3','4','c'),(248,92,'8 - 8','1','2','3','0','d'),(249,93,'what your name ?','1','2','3','4','a'),(250,93,'1*1','2','1','4','5','b'),(251,94,'toan laf ig','1','2','3','4','a'),(252,95,'1+1','1','2','34','4','b'),(253,95,'TOAN CAO CAP','2','4','5','5','b');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN','ADMIN'),('TEACHER','TEACHER'),('USER','USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salescourses`
--

DROP TABLE IF EXISTS `salescourses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salescourses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coursesid` int DEFAULT NULL,
  `salepercent` float DEFAULT NULL,
  `createddate` datetime(6) DEFAULT NULL,
  `endsaledate` datetime(6) DEFAULT NULL,
  `statussale` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `coursesid` (`coursesid`),
  CONSTRAINT `salescourses_ibfk_1` FOREIGN KEY (`coursesid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salescourses`
--

LOCK TABLES `salescourses` WRITE;
/*!40000 ALTER TABLE `salescourses` DISABLE KEYS */;
INSERT INTO `salescourses` VALUES (19,30,50,'2024-01-12 10:08:05.317000','2024-01-25 00:00:00.000000',_binary '\0'),(20,31,12,'2024-01-16 20:55:28.446000','2024-02-01 00:00:00.000000',_binary '\0'),(21,21,20,'2024-01-16 20:55:41.463000','2024-01-24 00:00:00.000000',_binary '\0'),(22,30,50,'2024-01-18 10:12:55.899000','2024-01-15 00:00:00.000000',_binary '\0'),(23,32,20,'2024-01-18 10:16:10.007000','2024-02-01 00:00:00.000000',_binary '\0'),(24,33,30,'2024-01-18 13:21:06.715000','2024-01-25 00:00:00.000000',_binary '\0');
/*!40000 ALTER TABLE `salescourses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `courseid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studyresults`
--

DROP TABLE IF EXISTS `studyresults`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studyresults` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detailid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `correct` int DEFAULT NULL,
  `wrong` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detailid` (`detailid`),
  KEY `userid` (`userid`),
  CONSTRAINT `studyresults_ibfk_1` FOREIGN KEY (`detailid`) REFERENCES `coursedetail` (`id`),
  CONSTRAINT `studyresults_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studyresults`
--

LOCK TABLES `studyresults` WRITE;
/*!40000 ALTER TABLE `studyresults` DISABLE KEYS */;
INSERT INTO `studyresults` VALUES (6,87,17,4,5,_binary '\0'),(7,34,17,0,3,_binary '\0'),(8,17,17,4,0,_binary ''),(9,93,17,2,0,_binary ''),(10,94,17,1,0,_binary ''),(11,17,21,2,2,_binary ''),(12,91,17,2,0,_binary '');
/*!40000 ALTER TABLE `studyresults` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `support`
--

DROP TABLE IF EXISTS `support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `support` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  `productid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `createddate` datetime(6) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `updatedate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ubrpll9mf1bxmj7lpm63qoik` (`productid`),
  KEY `FK687s0jight3dt13qirt5l1xlq` (`userid`),
  CONSTRAINT `FK4ubrpll9mf1bxmj7lpm63qoik` FOREIGN KEY (`productid`) REFERENCES `courses` (`id`),
  CONSTRAINT `FK687s0jight3dt13qirt5l1xlq` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `support`
--

LOCK TABLES `support` WRITE;
/*!40000 ALTER TABLE `support` DISABLE KEYS */;
/*!40000 ALTER TABLE `support` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `category` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `image` text,
  `cv` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usedvoucher`
--

DROP TABLE IF EXISTS `usedvoucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usedvoucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdate` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `voucherid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq830gn0ypq59nt15elrupidwi` (`userid`),
  KEY `FKkfpwh890ue0nrupmxk1allute` (`voucherid`),
  CONSTRAINT `FKkfpwh890ue0nrupmxk1allute` FOREIGN KEY (`voucherid`) REFERENCES `voucher` (`id`),
  CONSTRAINT `FKq830gn0ypq59nt15elrupidwi` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usedvoucher`
--

LOCK TABLES `usedvoucher` WRITE;
/*!40000 ALTER TABLE `usedvoucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `usedvoucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercourses`
--

DROP TABLE IF EXISTS `usercourses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercourses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `courseid` int DEFAULT NULL,
  `ownby` int DEFAULT NULL,
  `statusmail` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseid` (`courseid`),
  KEY `ownby` (`ownby`),
  CONSTRAINT `usercourses_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`id`),
  CONSTRAINT `usercourses_ibfk_2` FOREIGN KEY (`ownby`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercourses`
--

LOCK TABLES `usercourses` WRITE;
/*!40000 ALTER TABLE `usercourses` DISABLE KEYS */;
INSERT INTO `usercourses` VALUES (9,20,12,NULL),(10,20,13,NULL),(11,21,15,NULL),(12,20,15,NULL),(13,23,15,NULL),(14,22,15,NULL),(18,29,17,_binary ''),(19,20,17,NULL),(20,21,17,NULL),(21,22,17,NULL),(22,25,17,NULL),(23,24,17,NULL),(24,32,17,NULL),(25,33,17,_binary ''),(26,34,17,NULL),(27,20,21,NULL);
/*!40000 ALTER TABLE `usercourses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useroles`
--

DROP TABLE IF EXISTS `useroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useroles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `roleid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `useroles_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `useroles_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useroles`
--

LOCK TABLES `useroles` WRITE;
/*!40000 ALTER TABLE `useroles` DISABLE KEYS */;
INSERT INTO `useroles` VALUES (1,1,'ADMIN'),(3,3,'TEACHER'),(5,6,'ADMIN'),(10,12,'TEACHER'),(11,13,'ADMIN'),(13,15,'USER'),(15,17,'ADMIN'),(19,21,'USER');
/*!40000 ALTER TABLE `useroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phonenumber` varchar(10) DEFAULT NULL,
  `status` bit(1) DEFAULT b'0',
  `image` varchar(255) DEFAULT NULL,
  `rolestatus` bit(1) DEFAULT NULL,
  `tokenrestpass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'vancuto','123','kieuanhvan','van@gmail.com','019323123',_binary '\0',NULL,NULL,NULL),(3,'hoaianh1033@gmail.com','18c235da83e','hoai anh','hoaianh1033@gmail.com','0888888889',_binary '\0','',NULL,NULL),(6,'longtdps21068@fpt.edu.vn','18c4a390a52','Trần Đức Long (FPL HCM)','longtdps21068@fpt.edu.vn','0123456789',_binary '\0','',NULL,NULL),(12,'longtranduc18@gmail.com','18c4e9e7db2','Long Trần','longtranduc18@gmail.com','0999999999',_binary '\0','',NULL,NULL),(13,'vietlai21112003@gmail.com','18c7afd6f3e','Nguyễn Lai','vietlai21112003@gmail.com','0123456789',_binary '\0','1c434992-fdba-46bd-b3c7-9a564ca9ba28.jpg',NULL,NULL),(15,'lainvps24955@fpt.edu.vn','18c8c2144c8','Nguyen Viet Lai (FPL HCM_K17)','lainvps249558@fpt.edu.vn','0123456789',_binary '\0','',NULL,NULL),(17,'chuongchps24963@fpt.edu.vn','18cec421589','Che Hoang Chuong (FPL HCM_K17)','chuongchps24963@fpt.edu.vn','0328383680',_binary '\0',NULL,NULL,NULL),(21,'vietlai','$2a$10$ImVOU36pqJwus5kHm3Yb6eygoIyNF1/.897mVaF766AIBLqnZ9Qce','nguyenviet lai','lainvps24955@fpt.edu.vn','0328383680',_binary '\0',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createddate` datetime(6) NOT NULL,
  `descript` varchar(255) NOT NULL,
  `enddate` datetime(6) DEFAULT NULL,
  `percent` float NOT NULL,
  `price` float NOT NULL,
  `voucher_code` varchar(255) DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtbipvl1fb77m5cyq8mapy1rh` (`userid`),
  CONSTRAINT `FKjtbipvl1fb77m5cyq8mapy1rh` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'2024-01-09 14:01:51.521000','haha','2024-01-30 00:00:00.000000',10,0,'Y19OtNDC',17),(2,'2024-01-16 20:56:06.201000','haha','2024-02-02 00:00:00.000000',5,0,'PMPFyRlZ',17),(3,'2024-01-18 11:27:28.758000','haha','2024-02-02 00:00:00.000000',0,100,'ozRHo36j',17),(4,'2024-01-18 13:19:38.622000','voucher','2024-01-31 00:00:00.000000',12,0,'0dX9cNQl',17);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-18 13:34:22
