-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport`;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `carport`;

-- -----------------------------------------------------
-- Table `carport`.`unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`unit`;

CREATE TABLE IF NOT EXISTS `carport`.`unit`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `unit` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = '	';


-- -----------------------------------------------------
-- Table `carport`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`category`;

CREATE TABLE IF NOT EXISTS `carport`.`category`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`wood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`wood`;

CREATE TABLE IF NOT EXISTS `carport`.`wood`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `name`                VARCHAR(45) NULL,
    `width`               INT         NOT NULL,
    `length`              INT         NOT NULL,
    `height`              INT         NOT NULL,
    `is_pressure_treated` TINYINT     NOT NULL DEFAULT 0,
    `category`            INT         NOT NULL,
    `unit`                INT         NOT NULL,
    `price`               DOUBLE      NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_wood_unit_idx` (`unit` ASC) VISIBLE,
    INDEX `fk_wood_category1_idx` (`category` ASC) VISIBLE,
    CONSTRAINT `fk_wood_unit`
        FOREIGN KEY (`unit`)
            REFERENCES `carport`.`unit` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_wood_category1`
        FOREIGN KEY (`category`)
            REFERENCES `carport`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`roof_tile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`roof_tile`;

CREATE TABLE IF NOT EXISTS `carport`.`roof_tile`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NULL,
    `width`  INT         NOT NULL,
    `length` INT         NOT NULL,
    `unit`   INT         NOT NULL,
    `price`  DOUBLE      NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_roof_tile_unit1_idx` (`unit` ASC) VISIBLE,
    CONSTRAINT `fk_roof_tile_unit1`
        FOREIGN KEY (`unit`)
            REFERENCES `carport`.`unit` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`screw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`screw`;

CREATE TABLE IF NOT EXISTS `carport`.`screw`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NULL,
    `diameter` INT         NOT NULL,
    `length`   INT         NOT NULL,
    `unit`     INT         NOT NULL,
    `price`    DOUBLE      NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_roof_tile_unit1_idx` (`unit` ASC) VISIBLE,
    CONSTRAINT `fk_roof_tile_unit10`
        FOREIGN KEY (`unit`)
            REFERENCES `carport`.`unit` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`fitting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`fitting`;

CREATE TABLE IF NOT EXISTS `carport`.`fitting`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NULL,
    `width`  INT         NULL,
    `length` INT         NULL,
    `height` INT         NOT NULL,
    `unit`   INT         NOT NULL,
    `price`  DOUBLE      NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_roof_tile_unit1_idx` (`unit` ASC) VISIBLE,
    CONSTRAINT `fk_roof_tile_unit100`
        FOREIGN KEY (`unit`)
            REFERENCES `carport`.`unit` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`zip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`zip`;

CREATE TABLE IF NOT EXISTS `carport`.`zip`
(
    `zip`  INT         NOT NULL AUTO_INCREMENT,
    `city` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`zip`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`membership`;

CREATE TABLE IF NOT EXISTS `carport`.`membership`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `membership` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`role`;

CREATE TABLE IF NOT EXISTS `carport`.`role`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`user`;

CREATE TABLE IF NOT EXISTS `carport`.`user`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `first_name`   VARCHAR(45) NOT NULL,
    `last_name`    VARCHAR(45) NOT NULL,
    `email`        VARCHAR(45) NOT NULL,
    `password`     VARCHAR(45) NOT NULL,
    `address`      VARCHAR(45) NOT NULL,
    `phone_number` INT         NOT NULL,
    `created`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `zip`          INT         NOT NULL,
    `membership`   INT         NOT NULL DEFAULT 1,
    `role`         INT         NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    INDEX `fk_user_zip1_idx` (`zip` ASC) VISIBLE,
    INDEX `fk_user_memership1_idx` (`membership` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role` ASC) VISIBLE,
    CONSTRAINT `fk_user_zip1`
        FOREIGN KEY (`zip`)
            REFERENCES `carport`.`zip` (`zip`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_memership1`
        FOREIGN KEY (`membership`)
            REFERENCES `carport`.`membership` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_role1`
        FOREIGN KEY (`role`)
            REFERENCES `carport`.`role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order`;

CREATE TABLE IF NOT EXISTS `carport`.`order`
(
    `id`      INT                                       NOT NULL AUTO_INCREMENT,
    `user_id` INT                                       NOT NULL,
    `length`  INT                                       NOT NULL,
    `width`   INT                                       NOT NULL,
    `shed`    TINYINT                                   NOT NULL DEFAULT 0,
    `created` TIMESTAMP                                 NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`  ENUM ('PENDING', 'APPROVED', 'CANCELlED') NOT NULL DEFAULT 'PENDING',
    PRIMARY KEY (`id`),
    INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `carport`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`order_item_wood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order_item_wood`;

CREATE TABLE IF NOT EXISTS `carport`.`order_item_wood`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `order_id`    INT         NOT NULL,
    `quantity`    INT         NOT NULL,
    `description` VARCHAR(45) NULL,
    `price`       DOUBLE      NOT NULL,
    `item_id`     INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_order_item_wood_wood1_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_item_order1`
        FOREIGN KEY (`order_id`)
            REFERENCES `carport`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_item_wood_wood1`
        FOREIGN KEY (`item_id`)
            REFERENCES `carport`.`wood` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`order_item_roof_tile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order_item_roof_tile`;

CREATE TABLE IF NOT EXISTS `carport`.`order_item_roof_tile`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `order_id`    INT         NOT NULL,
    `quantity`    INT         NOT NULL,
    `description` VARCHAR(45) NULL,
    `price`       DOUBLE      NOT NULL,
    `item_id`     INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_order_item_roof_tile_roof_tile1_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_item_order10`
        FOREIGN KEY (`order_id`)
            REFERENCES `carport`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_item_roof_tile_roof_tile1`
        FOREIGN KEY (`item_id`)
            REFERENCES `carport`.`roof_tile` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`order_item_screw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order_item_screw`;

CREATE TABLE IF NOT EXISTS `carport`.`order_item_screw`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `order_id`    INT         NOT NULL,
    `quantity`    INT         NOT NULL,
    `description` VARCHAR(45) NULL,
    `price`       DOUBLE      NOT NULL,
    `item_id`     INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_order_item_screw_screw1_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_item_order100`
        FOREIGN KEY (`order_id`)
            REFERENCES `carport`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_item_screw_screw1`
        FOREIGN KEY (`item_id`)
            REFERENCES `carport`.`screw` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`order_item_fitting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order_item_fitting`;

CREATE TABLE IF NOT EXISTS `carport`.`order_item_fitting`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `order_id`    INT         NOT NULL,
    `quantity`    INT         NOT NULL,
    `description` VARCHAR(45) NULL,
    `price`       DOUBLE      NOT NULL,
    `item_id`     INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_order_item_fitting_fitting1_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_item_order1000`
        FOREIGN KEY (`order_id`)
            REFERENCES `carport`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_item_fitting_fitting1`
        FOREIGN KEY (`item_id`)
            REFERENCES `carport`.`fitting` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
create database if not exists carport_test;
create table if not exists carport_test.category like carport.category;
create table if not exists carport_test.fitting like carport.fitting;
create table  if not exists carport_test.membership like carport.membership;
create table  if not exists carport_test.order like carport.order;
create table if not exists carport_test.order_item_fitting like carport.order_item_fitting;
create table if not exists carport_test.order_item_roof_tile like carport.order_item_roof_tile;
create table if not exists carport_test.order_item_screw like carport.order_item_screw;
create table if not exists carport_test.order_item_wood like carport.order_item_wood;
create table if not exists carport_test.role like carport.role;
create table if not exists carport_test.roof_tile like carport.roof_tile;
create table if not exists carport_test.screw like carport.screw;
create table if not exists carport_test.unit like carport.unit;
create table if not exists carport_test.user like carport.user;
create table if not exists carport_test.wood like carport.wood;
create table if not exists carport_test.zip like carport.zip;


    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
