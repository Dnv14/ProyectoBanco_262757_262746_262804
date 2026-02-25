-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto_262757_262746_262804
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` bigint NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(60) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `domicilio` varchar(50) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Julian Gracia','julian24','2002-05-14','Hermosillo, Sonora','123456'),(2,'Diego Navsrrete','nv14','2006-05-25','Obregon, Sonora','987654');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `numeroCuenta` varchar(16) NOT NULL,
  `estado` enum('ACTIVO','INACTIVO') NOT NULL,
  `fechaApertura` date NOT NULL,
  `saldo` bigint NOT NULL,
  `idCliente` bigint NOT NULL,
  PRIMARY KEY (`numeroCuenta`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES ('1000000000000001','ACTIVO','2026-02-17',50000,1),('1000000000000002','ACTIVO','2026-02-17',150000,1),('1000000000000003','INACTIVO','2026-02-17',25000,1),('1000000000000011','ACTIVO','2026-02-17',50000,2),('1000000000000022','ACTIVO','2026-02-17',150000,2),('1000000000000033','INACTIVO','2026-02-17',25000,2);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operaciones` (
  `idOperacion` int NOT NULL AUTO_INCREMENT,
  `monto` bigint NOT NULL,
  `fechaHora` datetime NOT NULL,
  `numeroCuenta` varchar(16) NOT NULL,
  PRIMARY KEY (`idOperacion`),
  KEY `numeroCuenta` (`numeroCuenta`),
  CONSTRAINT `operaciones_ibfk_1` FOREIGN KEY (`numeroCuenta`) REFERENCES `cuentas` (`numeroCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retirosincuentas`
--

DROP TABLE IF EXISTS `retirosincuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retirosincuentas` (
  `idOperacion` int NOT NULL,
  `contrasenia` varchar(8) NOT NULL,
  `folio` int NOT NULL AUTO_INCREMENT,
  `estado` enum('ACTIVO','NO_COBRADO','COBRADO') NOT NULL,
  PRIMARY KEY (`idOperacion`),
  UNIQUE KEY `folio` (`folio`),
  CONSTRAINT `retirosincuentas_ibfk_1` FOREIGN KEY (`idOperacion`) REFERENCES `operaciones` (`idOperacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retirosincuentas`
--

LOCK TABLES `retirosincuentas` WRITE;
/*!40000 ALTER TABLE `retirosincuentas` DISABLE KEYS */;
/*!40000 ALTER TABLE `retirosincuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencias` (
  `idOperacion` int NOT NULL,
  `cuentaDestino` varchar(16) NOT NULL,
  PRIMARY KEY (`idOperacion`),
  KEY `cuentaDestino` (`cuentaDestino`),
  CONSTRAINT `transferencias_ibfk_1` FOREIGN KEY (`idOperacion`) REFERENCES `operaciones` (`idOperacion`),
  CONSTRAINT `transferencias_ibfk_2` FOREIGN KEY (`cuentaDestino`) REFERENCES `cuentas` (`numeroCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-24 11:03:06
