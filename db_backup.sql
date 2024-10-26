-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: localhost    Database: admissiondb
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admissionuser`
--

DROP TABLE IF EXISTS `admissionuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admissionuser` (
  `username1` varchar(20) DEFAULT NULL,
  `password1` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admissionuser`
--

LOCK TABLES `admissionuser` WRITE;
/*!40000 ALTER TABLE `admissionuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `admissionuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applications` (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `program_id` int NOT NULL,
  `status` enum('received','under_review','accepted','rejected') NOT NULL DEFAULT 'received',
  PRIMARY KEY (`application_id`),
  KEY `student_id` (`student_id`),
  KEY `program_id` (`program_id`),
  CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `applications_ibfk_2` FOREIGN KEY (`program_id`) REFERENCES `programs` (`program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications1`
--

DROP TABLE IF EXISTS `applications1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applications1` (
  `student_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` enum('Approved','Rejected','Under Review') DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications1`
--

LOCK TABLES `applications1` WRITE;
/*!40000 ALTER TABLE `applications1` DISABLE KEYS */;
INSERT INTO `applications1` VALUES (1,'Alice','Approved'),(2,'Bob','Rejected'),(3,'Charlie','Rejected'),(4,'David','Approved'),(5,'Eve','Rejected'),(6,'Frank','Rejected'),(7,'Grace','Rejected'),(8,'Heidi','Rejected'),(9,'Ivan','Under Review'),(10,'Judy','Approved'),(11,'Kevin','Rejected'),(12,'Laura','Under Review'),(13,'Mallory','Approved'),(14,'Nina','Rejected');
/*!40000 ALTER TABLE `applications1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programs`
--

DROP TABLE IF EXISTS `programs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programs` (
  `program_id` int NOT NULL AUTO_INCREMENT,
  `university_id` int NOT NULL,
  `department` varchar(100) NOT NULL,
  `program_name` varchar(255) NOT NULL,
  `Prerequisities` varchar(40) DEFAULT NULL,
  `availableSlots` int DEFAULT NULL,
  PRIMARY KEY (`program_id`),
  KEY `university_id` (`university_id`),
  CONSTRAINT `programs_ibfk_1` FOREIGN KEY (`university_id`) REFERENCES `universities` (`university_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programs`
--

LOCK TABLES `programs` WRITE;
/*!40000 ALTER TABLE `programs` DISABLE KEYS */;
INSERT INTO `programs` VALUES (5,4,'Your department name','Your program name','MATH 101, PHYS 102',20),(6,5,'Your department name','Your program name','CS 101',30),(7,6,'Your department name','Your program name','EE 200',15);
/*!40000 ALTER TABLE `programs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferstudents`
--

DROP TABLE IF EXISTS `transferstudents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferstudents` (
  `StudentID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `FieldOfStudy` varchar(100) NOT NULL,
  `PreviousUniversity` varchar(100) NOT NULL,
  `GPA` decimal(3,2) NOT NULL,
  `DateOfApplication` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferstudents`
--

LOCK TABLES `transferstudents` WRITE;
/*!40000 ALTER TABLE `transferstudents` DISABLE KEYS */;
INSERT INTO `transferstudents` VALUES (1,'ddgds','gdsgdsggdsgsg','dfsdfds','dsfsdfd',4.00,'2024-06-19 17:39:57');
/*!40000 ALTER TABLE `transferstudents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universities`
--

DROP TABLE IF EXISTS `universities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `universities` (
  `university_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `contactInfo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`university_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universities`
--

LOCK TABLES `universities` WRITE;
/*!40000 ALTER TABLE `universities` DISABLE KEYS */;
INSERT INTO `universities` VALUES (4,'Cambridge University','The Old Schools, Trinity Ln, Cambridge CB2 1TN, United Kingdom','+44 1223 337733'),(5,'University of Oxford','University Offices, Wellington Square, Oxford OX1 2JD, United Kingdom','+44 1865 270000'),(6,'Tsinghua University','Tsinghua University, Haidian District, Beijing, China','+86 10 6277 1214'),(11,'Peking University','Beijing, China','No. 5 Yiheyuan Road, Haidian District, Beijing 100871, China | +86 10 6275 1185 | info@pku.edu.cn');
/*!40000 ALTER TABLE `universities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('student','university_admin') NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','Sami12345','student',NULL),(3,'Samwell','Sami5678','student','Samii@yahoo.com'),(9,'daniman','dani36555','student','dani@gmail.com'),(12,'samuel','sami12345','student','sami@gmail.com'),(13,'nahom','nahom123','student','nahom@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-26 16:22:19
