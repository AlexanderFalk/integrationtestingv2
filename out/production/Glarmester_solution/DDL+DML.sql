CREATE SCHEMA `glarmester`;
USE `glarmester`;

CREATE TABLE `glarmester`.`prices` (
  `name` VARCHAR(25) NOT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`name`));

INSERT INTO `glarmester`.`prices` (`name`, `price`) VALUES 
('glass', '300'), 
('simple', '100'), 
('ornate', '200'), 
('lavish', '350');
