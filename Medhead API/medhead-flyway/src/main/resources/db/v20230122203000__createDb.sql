CREATE DATABASE medhead;
USE medhead;

CREATE TABLE `patient` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
    `info` TEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `age` TINYINT(3) UNSIGNED NULL DEFAULT NULL,
    `email` TINYTEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `phone` BIGINT(20) UNSIGNED NOT NULL,
    `position` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    PRIMARY KEY (`id`) USING BTREE
)

CREATE TABLE `hospital` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
    `position` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
    `total_beds` INT(11) NOT NULL,
    `beds_available` INT(11) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
)

CREATE TABLE `emergency` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `patient_id` INT(11) UNSIGNED NOT NULL,
    `hospital_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idPatient` (`patient_id`) USING BTREE,
    INDEX `idHospital` (`hospital_id`) USING BTREE
)

CREATE TABLE `doctor` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `specialization` ENUM('NEUROPATHOLOGY','CARDIOLOGY','IMMUNOLOGY') NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `hospital_id` INT(11) NULL DEFAULT NULL,
    `is_available` BIT(1) NULL DEFAULT NULL COMMENT 'used as a boolean',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `doctor_hospital` (`hospital_id`) USING BTREE,
    CONSTRAINT `doctor_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `api_medhead`.`hospital` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE `appointment` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `doctor_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    `patient_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    `date` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
)

CREATE TABLE `specialization` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`specilizationType` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
);


INSERT INTO `doctor` (`id`, `hospital_id`, `name`, `specialization`, `is_available`)
VALUES (1, 1, 'Sean Bean', 'IMMUNOLOGY', b'1'),
       (2, 2, 'Eric Dupont', 'NEUROPATHOLOGY', b'1'),
       (3, 1, 'Mia Fay', 'CARDIOLOGY', b'0');



ALTER TABLE `hospital` DISABLE KEYS ;
INSERT INTO `hospital` (`id`, `name`, `position`, `totalBeds`, `bedsAvailable`)
VALUES(1, 'Hôpital Nord', '43.379383, 5.362163', 150, 5),
      (2, 'Hôpital Montperrin', '43.522411, 5.438660', 50, 3);
ALTER TABLE `hospital` ENABLE KEYS ;

INSERT INTO `patient` (`id`, `name`, `info`, `age`, `email`, `phone`)
VALUES (1, 'Jean Delaterasse', 'Appointement in December showed hearth weakness', 58, 'jean.delaterasse@gmail.com', 0),
       (2, 'Frankie Sinatre', 'Emergency response in January for a brain disease', 82, NULL, 0);