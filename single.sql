CREATE DATABASE  IF NOT EXISTS `ipededata` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `ipededata`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: ipededata
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ta_pedido_tem_item`
--

DROP TABLE IF EXISTS `ta_pedido_tem_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `ta_pedido_tem_item` (
  `fk_idt_id_pedido` int(11) NOT NULL,
  `fk_idt_id_item` int(11) NOT NULL,
  `qtd_item` int(11) NOT NULL,
  PRIMARY KEY (`fk_idt_id_pedido`,`fk_idt_id_item`),
  KEY `fk_tb_pedido_has_tb_item_tb_item1_idx` (`fk_idt_id_item`),
  KEY `fk_tb_pedido_has_tb_item_tb_pedido1_idx` (`fk_idt_id_pedido`),
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_item1` FOREIGN KEY (`fk_idt_id_item`) REFERENCES `tb_item` (`idt_id_item`),
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_pedido1` FOREIGN KEY (`fk_idt_id_pedido`) REFERENCES `tb_pedido` (`idt_id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ta_pedido_tem_item`
--

LOCK TABLES `ta_pedido_tem_item` WRITE;
/*!40000 ALTER TABLE `ta_pedido_tem_item` DISABLE KEYS */;
INSERT INTO `ta_pedido_tem_item` VALUES (4,4,3),(170,3,4),(172,5,10),(173,3,6),(174,5,10),(175,4,2),(176,3,3),(177,3,6),(178,3,3),(178,4,4),(179,3,5),(180,5,10),(182,3,5),(184,3,31),(184,4,11),(185,5,62),(186,5,7),(187,4,15),(189,5,17),(190,5,10);
/*!40000 ALTER TABLE `ta_pedido_tem_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tb_cliente` (
  `idt_id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nme_primeiro_nome` varchar(15) NOT NULL,
  `nme_ultimo_nome` varchar(20) NOT NULL,
  `cpf_cliente` varchar(11) NOT NULL,
  `eml_email` varchar(45) NOT NULL,
  `cel_celular` varchar(11) NOT NULL,
  `pwd_senha` varchar(250) DEFAULT NULL,
  `ddd_ddd` int(2) NOT NULL,
  `url_foto_cliente` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idt_id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (4,'Alisson','Marques','00000000000','arthur@email.com','999999999','SenhaArthru',61,NULL),(5,'Thiago','Faria','11111111111','thiago@email.com','988888888','SenhaThiago',61,NULL),(7,'Arthur','Marques','05570144478','arthuremail@email.com','888888','SenhaTester',61,'Ta no seu coração'),(8,'Julia','Maria','11444778597','juliaemail@email.com','988663322','SenhaJuliar',61,NULL),(9,'Teste','Encriptado','00000000000','encriptado@email.com','999999999','$2a$10$Y.qo7RzKy/uqeXhGoyFsducNR7dG3rK7BdPJ/cQNqy26j2/Lt.CGS',61,NULL),(10,'Thiago','Faria','11111111111','thiago@email.com','988888888','$2a$10$WdbPzGU39uzWIyO3WGsN...BouJ7077bohCbcm69KBAg5QjS9yi1e',61,NULL),(11,'Thiago','Faria','11111111111','thiago2@email.com','988888888','$2a$10$00QjOBmx1MVLwpq3qu1DQuCFZmoqRb2zmEqjr7HN85Jnec53p5JxS',61,NULL),(12,'Lucitânia','Rosendo','11111122299','lucitanio@email.com','999999999','$2a$10$mxETt8t2N1bO4EF1rOXZneDxiVAX6JLHn8SRnPjM5Td63B6cIDgOG',61,NULL);
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item`
--

DROP TABLE IF EXISTS `tb_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tb_item` (
  `idt_id_item` int(11) NOT NULL AUTO_INCREMENT,
  `nme_nome_item` varchar(45) NOT NULL,
  `dsc_descricao_item` varchar(350) NOT NULL,
  `vlr_valor_item` decimal(6,2) NOT NULL,
  `flg_disponibilidade_item` tinyint(2) NOT NULL,
  `url_foto_item` varchar(300) DEFAULT NULL,
  `fk_idt_id_restaurante` int(11) NOT NULL,
  PRIMARY KEY (`idt_id_item`,`fk_idt_id_restaurante`),
  KEY `fk_tb_item_tb_restaurante1_idx` (`fk_idt_id_restaurante`),
  CONSTRAINT `fk_tb_item_tb_restaurante1` FOREIGN KEY (`fk_idt_id_restaurante`) REFERENCES `tb_restaurante` (`idt_id_restaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item`
--

LOCK TABLES `tb_item` WRITE;
/*!40000 ALTER TABLE `tb_item` DISABLE KEYS */;
INSERT INTO `tb_item` VALUES (3,'Frango Empanado','Empanado de Frango. Receita especial do Subway®. Servido em pão fresquinho, frango empanado, queijo e vegetais à sua escolha. Por tempo limitado!',4.99,1,NULL,4),(4,'B.M.T.®','O sanduíche que vai acabar com toda a sua fome. Servido em pão fresquinho, com fatias de salame, peperoni, presunto, vegetais e condimentos à sua escolha.',9.99,1,NULL,4),(5,'Frango Empanado','Empanado de Frango. Receita especial do Subway®. Servido em pão fresquinho, frango empanado, queijo e vegetais à sua escolha. Por tempo limitado!',4.99,1,NULL,6);
/*!40000 ALTER TABLE `tb_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pedido`
--

DROP TABLE IF EXISTS `tb_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tb_pedido` (
  `idt_id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `vlr_valor_total` decimal(6,2) DEFAULT NULL,
  `dta_data_pedido` datetime DEFAULT NULL,
  `sts_situacao_pagamento` varchar(8) DEFAULT NULL,
  `fk_idt_id_cliente` int(11) NOT NULL,
  `fk_idt_id_restaurante` int(11) NOT NULL,
  PRIMARY KEY (`idt_id_pedido`,`fk_idt_id_cliente`,`fk_idt_id_restaurante`),
  KEY `fk_tb_pedido_tb_cliente_idx` (`fk_idt_id_cliente`),
  KEY `fk_tb_pedido_tb_restaurante1_idx` (`fk_idt_id_restaurante`),
  CONSTRAINT `fk_tb_pedido_tb_cliente` FOREIGN KEY (`fk_idt_id_cliente`) REFERENCES `tb_cliente` (`idt_id_cliente`),
  CONSTRAINT `fk_tb_pedido_tb_restaurante1` FOREIGN KEY (`fk_idt_id_restaurante`) REFERENCES `tb_restaurante` (`idt_id_restaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pedido`
--

LOCK TABLES `tb_pedido` WRITE;
/*!40000 ALTER TABLE `tb_pedido` DISABLE KEYS */;
INSERT INTO `tb_pedido` VALUES (4,23.97,'2018-10-15 13:51:41','Pendente',5,4),(7,NULL,'2018-10-17 15:08:36','Pendente',4,4),(135,24.95,'2018-11-23 08:34:12','Pago',11,4),(170,19.96,'2018-11-24 11:44:39','Pago',9,4),(172,49.90,'2018-11-24 11:49:42','Pago',9,6),(173,29.94,'2018-11-24 11:51:45','Pago',9,4),(174,49.90,'2018-11-24 11:57:03','Pago',9,6),(175,19.98,'2018-11-24 11:58:08','Pago',9,4),(176,14.97,'2018-11-24 12:03:49','Pago',12,4),(177,29.94,'2018-11-24 12:13:23','Pago',12,4),(178,54.93,'2018-11-24 13:41:56','Pago',12,4),(179,24.95,'2018-11-24 14:28:13','Pago',12,4),(180,49.90,'2018-11-24 14:32:35','Pago',12,6),(182,24.95,'2018-11-24 17:59:16','Pago',12,4),(184,264.58,'2018-11-24 18:00:29','Pago',12,4),(185,309.38,'2018-11-24 18:01:08','Pago',12,6),(186,34.93,'2018-11-24 18:06:43','Pago',12,6),(187,149.85,'2018-11-24 18:07:51','Pago',12,4),(189,84.83,'2018-11-24 18:12:05','Pago',12,6),(190,49.90,'2018-11-24 18:21:16','Pago',9,6),(192,NULL,'2018-11-24 18:22:11','Pendente',9,4);
/*!40000 ALTER TABLE `tb_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_restaurante`
--

DROP TABLE IF EXISTS `tb_restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tb_restaurante` (
  `idt_id_restaurante` int(11) NOT NULL AUTO_INCREMENT,
  `end_endereco` varchar(45) NOT NULL,
  `nme_nome_restaurante` varchar(45) NOT NULL,
  `end_cidade` varchar(45) NOT NULL,
  `cep_restaurante` varchar(8) NOT NULL,
  `pwd_senha_restaurante` varchar(45) NOT NULL,
  `cpj_cnpj_restaurante` varchar(45) NOT NULL,
  `lat_latitude` float NOT NULL,
  `lgt_longitude` float NOT NULL,
  `url_foto_restaurante` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idt_id_restaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_restaurante`
--

LOCK TABLES `tb_restaurante` WRITE;
/*!40000 ALTER TABLE `tb_restaurante` DISABLE KEYS */;
INSERT INTO `tb_restaurante` VALUES (4,'CSB 2 Lotes 01 a 04','Sunbway','Taguatinga','72015901','SenhaSubway','85.686.265/0001-48',-15.8277,-48.0917,NULL),(5,'Qs 1, Rua 210, Lote 40','McDonalds','Taguatinga','71950904','SenhaMcDonalds','63.865.822/0001-55',-45.222,-50.777,NULL),(6,'CSB 2 Lotes 01 a 04','Giraffas','Taguatinga','72015901','SenhaGiraffas','85.686.25/0001-48',-15.8277,-48.0917,NULL);
/*!40000 ALTER TABLE `tb_restaurante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-24 20:00:29
