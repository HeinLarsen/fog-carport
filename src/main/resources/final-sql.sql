-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport` ;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `carport` ;

-- -----------------------------------------------------
-- Table `carport`.`material_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`material_type` ;

CREATE TABLE IF NOT EXISTS `carport`.`material_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `material` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`packaging`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`packaging` ;

CREATE TABLE IF NOT EXISTS `carport`.`packaging` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`type` ;

CREATE TABLE IF NOT EXISTS `carport`.`type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`material` ;

CREATE TABLE IF NOT EXISTS `carport`.`material` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `length` INT NULL DEFAULT NULL,
  `width` INT NULL DEFAULT NULL,
  `height` INT NULL DEFAULT NULL,
  `diameter` INT NULL DEFAULT NULL,
  `description` VARCHAR(45) NOT NULL,
  `quantity` INT NULL,
  `price` DOUBLE NOT NULL,
  `type` INT NOT NULL,
  `packaging` INT NOT NULL,
  `material_type_id` INT NOT NULL,
  PRIMARY KEY (`id`, `type`, `packaging`, `material_type_id`),
  INDEX `fk_material_packaging1_idx` (`packaging` ASC) VISIBLE,
  INDEX `fk_material_type1_idx` (`type` ASC) VISIBLE,
  INDEX `fk_material_material_type1_idx` (`material_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_material_material_type1`
    FOREIGN KEY (`material_type_id`)
    REFERENCES `carport`.`material_type` (`id`),
  CONSTRAINT `fk_material_packaging1`
    FOREIGN KEY (`packaging`)
    REFERENCES `carport`.`packaging` (`id`),
  CONSTRAINT `fk_material_type1`
    FOREIGN KEY (`type`)
    REFERENCES `carport`.`type` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`membership` ;

CREATE TABLE IF NOT EXISTS `carport`.`membership` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`role` ;

CREATE TABLE IF NOT EXISTS `carport`.`role` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`zip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`zip` ;

CREATE TABLE IF NOT EXISTS `carport`.`zip` (
  `zip` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`zip`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`user` ;

CREATE TABLE IF NOT EXISTS `carport`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` INT NOT NULL,
  `role_id` INT NOT NULL,
  `membership_id` INT NOT NULL,
  `zip` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_membership1_idx` (`membership_id` ASC) VISIBLE,
  INDEX `fk_user_zip1_idx` (`zip` ASC) VISIBLE,
  CONSTRAINT `fk_user_membership1`
    FOREIGN KEY (`membership_id`)
    REFERENCES `carport`.`membership` (`id`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `carport`.`role` (`id`),
  CONSTRAINT `fk_user_zip1`
    FOREIGN KEY (`zip`)
    REFERENCES `carport`.`zip` (`zip`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order` ;

CREATE TABLE IF NOT EXISTS `carport`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('PENDING', 'APPROVED', 'CANCELlED') NULL DEFAULT 'PENDING',
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `id`
    FOREIGN KEY (`user_id`)
    REFERENCES `carport`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`order_item` ;

CREATE TABLE IF NOT EXISTS `carport`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` INT NOT NULL,
  `type` INT NOT NULL,
  `total_price` DOUBLE NOT NULL,
  `order_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  `material_type_id` INT NOT NULL,
  PRIMARY KEY (`id`, `material_id`, `material_type_id`),
  INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_item_material1_idx` (`material_id` ASC, `material_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`id`),
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `carport`.`order` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;






/* setup test database */
CREATE DATABASE IF NOT EXISTS `carport_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport_test`;
CREATE TABLE carport_test.user LIKE carport.user;
CREATE TABLE carport_test.zip LIKE carport.zip;
CREATE TABLE carport_test.role LIKE carport.role;
CREATE TABLE carport_test.membership LIKE carport.membership;
CREATE TABLE carport_test.order LIKE carport.order;
CREATE TABLE carport_test.packaging LIKE carport.packaging;
CREATE TABLE carport_test.type LIKE carport.type;
CREATE TABLE carport_test.material LIKE carport.material;
CREATE TABLE carport_test.order_item LIKE carport.order_item;
CREATE TABLE carport_test.material_type LIKE carport.material_type;

ALTER TABLE carport.material AUTO_INCREMENT = 1;
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('360', '200', '25', 'understærnbrædder til for og bag ende', '174.43', '1', '1', '1 ');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('540', '200', '25', 'understærnbrædder til siderne', '262.03', '1', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('360', '125', '25', 'overstærnbrædder til forenden', '108.90', '1', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('540', '125', '25', 'oversternbrædder til siderne', '163.35', '1', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('420', '73', '38', 'til z på bagside af dør', '120.13', '2', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('270', '95', '45', 'løsholter til skur gavle', '56.28', '3', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('240', '95', '45', 'løsholter til skur sider', '56.28', '3', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('480', '195', '45', 'Remme i sider, sadles ned i stolper', '205.44', '4', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('600', '195', '45', 'Spær, monteres på rem', '257.70', '4', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('300', '97', '97', 'Stolper nedgraves 90 cm. i jord', '134.85', '5', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('210', '100', '19', 'til beklædning af skur 1 på 2', '20.16', '5', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('540', '100', '19', 'vandbrædt på stern i sider', '51.84', '5', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('360', '100', '19', 'vandbrædt på stern i forende', '34.56', '5', '1', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('600', '300', 'tagplader monteres på spær', '633.00', '6', '1', '3');
INSERT INTO `carport`.`material` (`length`, `width`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('360', '300', 'tagplader monteres på spær', '266.00', '6', '1', '3');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('30', 'skruer til tagplader', '441.00', '7', '2', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('1000', '20', '1', 'til vindkryds og spær', '339.00', '8', '3', '4');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('190', 'til montering af spær på rem', '76.95', '9', '1', '4');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('190', 'til montering af spær på rem', '76.95', '10', '1', '4');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging` , `material_type_id`)
VALUES ('60', '60', '4.5', 'Til montering af stern&vandbrædt', '307.95', '11', '2', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('50', '50', '4', 'Til montering af universalbeslag + hulbånd', '263.95', '12', '2', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('120', '120', '10', 'Til montering af rem på stolper', '464.00', '13', '1', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `height`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('40', '40', '40', '11', 'Til montering af rem på stolper', '149.50', '14', '1', '4');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('70', '70', '4.5', 'til montering af yderste beklædning', '149.95', '11', '2', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('50', '50', '4.5', 'til montering af inderste beklædning', '160.95', '11', '2', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `diameter`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('75', '75', '50', 'Til lås på dør i skur', '183.95', '15', '4', '5');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('390', 'til skurdør', '156.95', '16', '1', '4');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`, `packaging`, `material_type_id`)
VALUES ('35', 'Til montering af løsholter i skur', '13.95', '17', '1', '3');

packaging;

INSERT INTO `carport`.`packaging` (`type`)
VALUES ('stk');
INSERT INTO `carport`.`packaging` (`type`)
VALUES ('pakke');
INSERT INTO `carport`.`packaging` (`type`)
VALUES ('rulle');
INSERT INTO `carport`.`packaging` (`type`)
VALUES ('sæt');

INSERT INTO `carport`.`type` (`type`)
VALUES ('trykimp. brædt');
INSERT INTO `carport`.`type` (`type`)
VALUES ('lægte');
INSERT INTO `carport`.`type` (`type`)
VALUES ('reglar ub.');
INSERT INTO `carport`.`type` (`type`)
VALUES ('spærtræ ubh.');
INSERT INTO `carport`.`type` (`type`)
VALUES ('trykimp. Brædt');
INSERT INTO `carport`.`type` (`type`)
VALUES ('Plastmo Ecolite blåtonet');
INSERT INTO `carport`.`type` (`type`)
VALUES ('Plastmo Trapez Brundskrue');
INSERT INTO `carport`.`type` (`type`)
VALUES ('hulbånd');
INSERT INTO `carport`.`type` (`type`)
VALUES ('universal');
INSERT INTO `carport`.`type` (`type`)
VALUES ('universal');
INSERT INTO `carport`.`type` (`type`)
VALUES ('skruer');
INSERT INTO `carport`.`type` (`type`)
VALUES ('beslagskruer');
INSERT INTO `carport`.`type` (`type`)
VALUES ('bræddebolt');
INSERT INTO `carport`.`type` (`type`)
VALUES ('firkantskiver');
INSERT INTO `carport`.`type` (`type`)
VALUES ('stalddørsgreb');
INSERT INTO `carport`.`type` (`type`)
VALUES ('t hængse');
INSERT INTO `carport`.`type` (`type`)
VALUES ('vinkelbeslag');



insert into carport.membership (id, type)
values (1, 'basic'),
       (2, 'member'),
       (3, 'worker');

insert into carport.role (id, type)
values (1, 'user'),
       (2, 'admin');


insert into carport.zip(zip, city)
VALUES (2800, 'lyngby'),
       (3200, 'helsinge');


insert into carport.user (first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip)
VALUES ('admin', 'admin', 'admin', '1234', 'admin', 1234, 2, 3, 3200),
       ('user', 'user', 'user', '1234', '1', 1234, 1, 1, 3200),
       ('memberuser', 'user', 'user', '1234', 'lærkevej 41', 1234, 1, 2, 2800),
       ('workeruser', 'user', '1234', '1234', 'æøåvej', 1234, 1, 3, 3200);





insert into material_type (material) values
("wood"),
("screw"),
("screwpack")
("roof tile"),
("fitting"),
("door handle");

