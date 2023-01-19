CREATE DATABASE medhead;
USE medhead;

CREATE TABLE `patient` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`info` TEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`age` TINYINT(3) UNSIGNED NULL DEFAULT NULL,
	`email` TINYTEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`phone` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `hospital` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
    `position` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
    `totalBeds` INT(11) NOT NULL,
    `bedsAvailable` INT(11) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `emergency` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`idPatient` INT(11) UNSIGNED NOT NULL,
	`idHospital` INT(11) UNSIGNED NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `idPatient` (`idPatient`) USING BTREE,
	INDEX `idHospital` (`idHospital`) USING BTREE
);

CREATE TABLE `doctor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
  `specialization` ENUM('NEUROPATHOLOGY','CARDIOLOGY','IMMUNOLOGY') NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
  `hospitalId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `doctor_hospital` (`hospitalId`) USING BTREE,
  CONSTRAINT `doctor_hospital` FOREIGN KEY (`hospitalId`) REFERENCES `api_medhead`.`hospital` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE `appointment` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`idDoctor` INT(11) UNSIGNED NULL DEFAULT NULL,
	`idPatient` INT(11) UNSIGNED NULL DEFAULT NULL,
	`date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)

CREATE TABLE `specialization` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`specilizationType` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
)

