-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema iPedeData
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema iPedeData
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iPedeData` ;
USE `iPedeData` ;

-- -----------------------------------------------------
-- Table `iPedeData`.`tb_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iPedeData`.`tb_cliente` (
  `idt_id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nme_primeiro_nome` VARCHAR(15) NOT NULL,
  `nme_ultimo_nome` VARCHAR(20) NOT NULL,
  `cpf_cliente` VARCHAR(11) NOT NULL,
  `eml_email` VARCHAR(45) NOT NULL,
  `cel_celular` VARCHAR(11) NOT NULL,
  `pwd_senha` VARCHAR(20) NOT NULL,
  `ddd_ddd` INT(2) NOT NULL,
  `url_foto_cliente` VARCHAR(300) NULL,
  PRIMARY KEY (`idt_id_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iPedeData`.`tb_restaurante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iPedeData`.`tb_restaurante` (
  `idt_id_restaurante` INT NOT NULL AUTO_INCREMENT,
  `end_endereco` VARCHAR(45) NOT NULL,
  `nme_nome_restaurante` VARCHAR(45) NOT NULL,
  `end_cidade` VARCHAR(45) NOT NULL,
  `cep_restaurante` VARCHAR(8) NOT NULL,
  `pwd_senha_restaurante` VARCHAR(45) NOT NULL,
  `cpj_cnpj_restaurante` VARCHAR(45) NOT NULL,
  `lat_latitude` FLOAT NOT NULL,
  `lgt_longitude` FLOAT NOT NULL,
  `url_foto_restaurante` VARCHAR(300) NULL,
  PRIMARY KEY (`idt_id_restaurante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iPedeData`.`tb_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iPedeData`.`tb_pedido` (
  `idt_id_pedido` INT NOT NULL AUTO_INCREMENT,
  `vlr_valor_total` DECIMAL(6,2) NULL,
  `dta_data_pedido` DATETIME NULL,
  `sts_situacao_pagamento` VARCHAR(8) NULL,
  `fk_idt_id_cliente` INT NOT NULL,
  `fk_idt_id_restaurante` INT NOT NULL,
  PRIMARY KEY (`idt_id_pedido`, `fk_idt_id_cliente`, `fk_idt_id_restaurante`),
  INDEX `fk_tb_pedido_tb_cliente_idx` (`fk_idt_id_cliente` ASC) VISIBLE,
  INDEX `fk_tb_pedido_tb_restaurante1_idx` (`fk_idt_id_restaurante` ASC) VISIBLE,
  CONSTRAINT `fk_tb_pedido_tb_cliente`
    FOREIGN KEY (`fk_idt_id_cliente`)
    REFERENCES `iPedeData`.`tb_cliente` (`idt_id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_pedido_tb_restaurante1`
    FOREIGN KEY (`fk_idt_id_restaurante`)
    REFERENCES `iPedeData`.`tb_restaurante` (`idt_id_restaurante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iPedeData`.`tb_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iPedeData`.`tb_item` (
  `idt_id_item` INT NOT NULL AUTO_INCREMENT,
  `nme_nome_item` VARCHAR(45) NOT NULL,
  `dsc_descricao_item` VARCHAR(350) NOT NULL,
  `vlr_valor_item` DECIMAL(6,2) NOT NULL,
  `flg_disponibilidade_item` TINYINT(2) NOT NULL,
  `url_foto_item` VARCHAR(300) NULL,
  `fk_idt_id_restaurante` INT NOT NULL,
  PRIMARY KEY (`idt_id_item`, `fk_idt_id_restaurante`),
  INDEX `fk_tb_item_tb_restaurante1_idx` (`fk_idt_id_restaurante` ASC) VISIBLE,
  CONSTRAINT `fk_tb_item_tb_restaurante1`
    FOREIGN KEY (`fk_idt_id_restaurante`)
    REFERENCES `iPedeData`.`tb_restaurante` (`idt_id_restaurante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iPedeData`.`ta_pedido_tem_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iPedeData`.`ta_pedido_tem_item` (
  `fk_idt_id_pedido` INT NOT NULL,
  `fk_idt_id_item` INT NOT NULL,
  `qtd_item` INT NOT NULL,
  INDEX `fk_tb_pedido_has_tb_item_tb_item1_idx` (`fk_idt_id_item` ASC) VISIBLE,
  INDEX `fk_tb_pedido_has_tb_item_tb_pedido1_idx` (`fk_idt_id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_pedido1`
    FOREIGN KEY (`fk_idt_id_pedido`)
    REFERENCES `iPedeData`.`tb_pedido` (`idt_id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_pedido_has_tb_item_tb_item1`
    FOREIGN KEY (`fk_idt_id_item`)
    REFERENCES `iPedeData`.`tb_item` (`idt_id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
