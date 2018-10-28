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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ta_pedido_tem_item` (
  `fk_idt_id_pedido` int(11) NOT NULL,
  `fk_idt_id_item` int(11) NOT NULL,
  `qtd_item` int(11) NOT NULL,
  PRIMARY KEY (`fk_idt_id_pedido`,`fk_idt_id_item`),
  KEY `fk_tb_pedido_has_tb_item_tb_item1_idx` (`fk_idt_id_item`),
  KEY `fk_tb_pedido_has_tb_item_tb_pedido1_idx` (`fk_idt_id_pedido`),
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_item1` FOREIGN KEY (`fk_idt_id_item`) REFERENCES `tb_item` (`idt_id_item`),
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_pedido1` FOREIGN KEY (`fk_idt_id_pedido`) REFERENCES `tb_pedido` (`idt_id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ta_pedido_tem_item`
--

LOCK TABLES `ta_pedido_tem_item` WRITE;
/*!40000 ALTER TABLE `ta_pedido_tem_item` DISABLE KEYS */;
INSERT INTO `ta_pedido_tem_item` VALUES (4,4,3),(7,3,2),(7,4,2);
/*!40000 ALTER TABLE `ta_pedido_tem_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_cliente` (
  `idt_id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nme_primeiro_nome` varchar(15) NOT NULL,
  `nme_ultimo_nome` varchar(20) NOT NULL,
  `cpf_cliente` varchar(11) NOT NULL,
  `eml_email` varchar(45) NOT NULL,
  `cel_celular` varchar(11) NOT NULL,
  `pwd_senha` varchar(20) NOT NULL,
  `ddd_ddd` int(2) NOT NULL,
  `url_foto_cliente` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idt_id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (4,'Alisson','Marques','00000000000','arthur@email.com','999999999','SenhaArthru',61,NULL),(5,'Thiago','Faria','11111111111','thiago@email.com','988888888','SenhaThiago',61,NULL),(7,'Arthur','Marques','05570144478','arthuremail@email.com','888888','SenhaTester',61,'Ta no seu coração'),(8,'Julia','Maria','11444778597','juliaemail@email.com','988663322','SenhaJuliar',61,NULL);
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item`
--

DROP TABLE IF EXISTS `tb_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item`
--

LOCK TABLES `tb_item` WRITE;
/*!40000 ALTER TABLE `tb_item` DISABLE KEYS */;
INSERT INTO `tb_item` VALUES (3,'Frango Empanado','Empanado de Frango. Receita especial do Subway®. Servido em pão fresquinho, frango empanado, queijo e vegetais à sua escolha. Por tempo limitado!',4.99,1,NULL,4),(4,'B.M.T.®','O sanduíche que vai acabar com toda a sua fome. Servido em pão fresquinho, com fatias de salame, peperoni, presunto, vegetais e condimentos à sua escolha.',9.99,1,NULL,4);
/*!40000 ALTER TABLE `tb_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pedido`
--

DROP TABLE IF EXISTS `tb_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pedido`
--

LOCK TABLES `tb_pedido` WRITE;
/*!40000 ALTER TABLE `tb_pedido` DISABLE KEYS */;
INSERT INTO `tb_pedido` VALUES (4,23.97,'2018-10-15 13:51:41','Pendente',5,4),(7,NULL,'2018-10-17 15:08:36','Pendente',4,4);
/*!40000 ALTER TABLE `tb_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_restaurante`
--

DROP TABLE IF EXISTS `tb_restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_restaurante`
--

LOCK TABLES `tb_restaurante` WRITE;
/*!40000 ALTER TABLE `tb_restaurante` DISABLE KEYS */;
INSERT INTO `tb_restaurante` VALUES (4,'CSB 2 Lotes 01 a 04','Sunbway','Taguatinga','72015901','SenhaSubway','85.686.265/0001-48',-15.8277,-48.0917,NULL),(5,'Qs 1, Rua 210, Lote 40','McDonalds','Taguatinga','71950904','SenhaMcDonalds','63.865.822/0001-55',-45.222,-50.777,NULL);
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

-- Dump completed on 2018-10-28 16:01:37
