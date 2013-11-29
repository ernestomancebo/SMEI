CREATE DATABASE  IF NOT EXISTS `smei` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `smei`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: smei
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `espacios`
--

DROP TABLE IF EXISTS `espacios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `espacios` (
  `idEspacio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `capacidadDePersonas` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idEspacio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espacios`
--

LOCK TABLES `espacios` WRITE;
/*!40000 ALTER TABLE `espacios` DISABLE KEYS */;
INSERT INTO `espacios` VALUES (1,'Salon A',15,1,'Primer salon'),(2,'Salon Principal',101,0,'Utilizado por la directiva'),(3,'Cubiculo Especial',3,1,'Para estudiantes meritorios.'),(4,'Salon Sombras',23,1,'Algo muy oscuro'),(5,'Salon Dr. Luis Nunez',20,1,'Salon de conferencias'),(6,'Salon Hayser Beltre',30,1,'Salon habilitado para estudiantes de ISI, ISC e ISO');
/*!40000 ALTER TABLE `espacios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados_reservaciones`
--

DROP TABLE IF EXISTS `estados_reservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados_reservaciones` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_reservaciones`
--

LOCK TABLES `estados_reservaciones` WRITE;
/*!40000 ALTER TABLE `estados_reservaciones` DISABLE KEYS */;
INSERT INTO `estados_reservaciones` VALUES (1,'Cancelada'),(2,'Completada'),(3,'Pendiente');
/*!40000 ALTER TABLE `estados_reservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificaciones` (
  `idNotificacion` int(11) NOT NULL AUTO_INCREMENT,
  `contenido_por_defecto` varchar(500) NOT NULL,
  `contenido_personalizado` varchar(500) DEFAULT NULL,
  `titulo_por_defecto` varchar(50) NOT NULL,
  `titulo_personalizado` varchar(50) DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `habilitada` tinyint(1) NOT NULL,
  PRIMARY KEY (`idNotificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificaciones`
--

LOCK TABLES `notificaciones` WRITE;
/*!40000 ALTER TABLE `notificaciones` DISABLE KEYS */;
INSERT INTO `notificaciones` VALUES (1,'Se ha creado un nuevo usuario en SMEI.\\nCreado por:\\n@@usuario.\\nDetalle:\\n@@detalle','Responsable:\n@@usuario.\n\nDetalle:\n@@detalle','Nuevo Usuario en SMEI',NULL,'CREAR_USUARIO',1),(3,'Se ha actualizado un usuario en SMEI.\n Responsable:\n@@usuario.\nDetalle:\n@@detalle','Cambio en usuario\n\nResponsable:\n@@usuario.\n\nDetalle:\n@@detalle','Usuario actualizado en SMEI',NULL,'ACTUALIZAR_USUARIO',1),(4,'Su contraseña es:\n@@detalle','Su nueva contraseña en SMEI es:\n@@detalle','Nueva contraseña en SMEI',NULL,'NUEVA_CONTRASEÑA',1),(5,'Su contraseña en SMEI es:\n@@detalle\nPara actualizar, dirigirse a \'Modificar contraseña\' en SMEI.',NULL,'Contraseña en SMEI',NULL,'OLVIDE_CONTRASEÑA',1),(6,'Se ha creado una nueva reserva en SMEI.\nCreado por:\n@@usuario.\nDetalle:\n@@detalle','Atiende Josermando!!\n\nCreado por:\n@@usuario.\n\nDetalle:\n@@detalle','Nueva reserva en SMEI',NULL,'CREAR_RESERVA',1),(7,'Se ha actualizado una reserva en SMEI.\nActualizada por:\n@@usuario.\nDetalle:\n@@detalle',NULL,'Reserva actualizada en SMEI',NULL,'ACTUALIZAR_RESERVA',1),(8,'Se ha cancelado una reserva en SMEI.\nCancelada por:\n@@usuario.\nDetalle:\n@@detalle',NULL,'Reserva Cancelada',NULL,'CANCELAR_RESERVA',1),(9,'Se ha creado un nuevo espacio en SMEI.\nCreado por:\n@@usuario.\nDetalle:\n@@detalle','Se ha creado un nuevo espacio en SMEI.\nCreado por:\n@@usuario.\n\nDetalle:\n@@detalle','Nuevo espacio en SMEI',NULL,'CREAR_ESPACIO',1),(10,'Se ha actualizado un espacio en SMEI.\nActualizado por:\n@@usuario.\nDetalle:\n@@detalle',NULL,'Espacio actualizado en SMEI',NULL,'ACTUALIZAR_ESPACIO',1);
/*!40000 ALTER TABLE `notificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservaciones`
--

DROP TABLE IF EXISTS `reservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservaciones` (
  `idReservacion` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `idEspacio` int(11) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `cantidadDePersonas` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `idEstado` int(11) DEFAULT '3',
  PRIMARY KEY (`idReservacion`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idEspacio` (`idEspacio`),
  KEY `idEstado` (`idEstado`),
  CONSTRAINT `reservaciones_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `reservaciones_ibfk_2` FOREIGN KEY (`idEspacio`) REFERENCES `espacios` (`idEspacio`),
  CONSTRAINT `reservaciones_ibfk_3` FOREIGN KEY (`idEstado`) REFERENCES `estados_reservaciones` (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservaciones`
--

LOCK TABLES `reservaciones` WRITE;
/*!40000 ALTER TABLE `reservaciones` DISABLE KEYS */;
INSERT INTO `reservaciones` VALUES (1,1,1,NULL,'2013-11-10 12:32:12','2014-11-14 12:00:00','2014-11-14 13:00:00',1,'aaa',1),(2,1,1,'2013-11-10 11:03:32','2013-11-10 17:18:40','2013-11-11 01:00:00','2013-11-11 13:00:00',10,'ahkh',2),(3,1,1,'2013-11-10 11:09:08','2013-11-10 17:15:20','2013-11-12 12:00:00','2013-11-13 00:00:00',12,'asda',2),(4,1,3,'2013-11-10 11:35:23','2013-11-10 22:04:46','2013-11-10 23:00:00','2013-11-10 23:15:00',1,'ads',2),(5,1,1,'2013-11-10 17:22:43','2013-11-10 17:22:43','2013-11-11 01:00:30','2013-11-11 13:00:30',12,'qwe',2),(6,1,1,'2013-11-10 17:22:59','2013-11-10 17:22:59','2013-11-13 01:00:00','2013-11-13 13:15:00',12,'aa',2),(7,1,1,'2013-11-10 21:06:14','2013-11-10 21:06:14','2013-11-15 01:00:12','2013-11-15 02:00:12',1,'11',2),(8,1,1,'2013-11-14 18:10:51','2013-11-14 18:17:24','2013-11-14 18:20:00','2013-11-14 18:40:00',10,'Prueba en clase!!',2),(9,1,4,'2013-11-14 18:27:24','2013-11-14 18:27:24','2013-11-14 18:30:26','2013-11-14 18:35:26',10,'Prueba',2),(10,1,5,'2013-11-16 17:02:02','2013-11-16 17:02:02','2013-11-17 14:00:00','2013-11-17 14:15:00',2,'Testing',2),(11,1,4,'2013-11-21 17:41:44','2013-11-21 17:41:44','2013-11-21 18:00:00','2013-11-21 18:35:00',12,'asd',2),(12,1,5,'2013-11-22 17:10:22','2013-11-22 17:10:22','2013-11-22 18:00:00','2013-11-22 18:15:00',20,'Prueba!!',2),(13,1,6,'2013-11-23 23:06:29','2013-11-23 23:08:23','2013-11-24 14:00:00','2013-11-24 14:30:00',25,'Reunion para trabajar proyecto final.',2),(14,3,1,'2013-11-23 23:50:27','2013-11-23 23:50:27','2013-11-24 14:00:00','2013-11-24 14:05:00',12,'Una reunion extracurricular',2);
/*!40000 ALTER TABLE `reservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Apoyo'),(3,'Consulta');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idRol` int(11) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `identificacion` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'Ernesto','aaa','8888-8888','ernesmancebo@gmail.com','888-888-8888',1),(2,3,'Wilkys','W0001z','0000-0002','w@gmail.com','999-000-0000',1),(3,2,'Eduardo','E8888o','8888-8800','ernesmancebo@gmail.com','888-888-8888',1),(4,2,'Yogguie','Y9999e','9999-9999','yy@g.com','000-000-0000',0),(5,2,'Ramon','R1111n','2222-1111','aa@g.co','999-999-0000',0),(6,2,'Wilkis','W3333s','8888-3333','wilkysrro@gmail.com','000-000-0000',0),(7,2,'Luis Nuñez','L0000z','0000-0000','luisok@gmail.com','999-999-9999',1),(8,2,'Jose','J1111e','1111-1111','jose@unapec.edu.do','809-621-0025',1),(9,3,'Jose','J4444e','4444-4444','scalator.5@hotmail.com','999-000-1111',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-28 18:07:28
