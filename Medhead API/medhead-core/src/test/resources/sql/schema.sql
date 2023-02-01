CREATE TABLE `patient` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
    `info` TEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `age` TINYINT(3) UNSIGNED NULL DEFAULT NULL,
    `email` TINYTEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `phone` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
    `position` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `specialization` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `hospital` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
    `position` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
    `total_beds` INT(11) NOT NULL,
    `beds_available` INT(11) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `emergency` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `patient_id` INT(11) UNSIGNED NOT NULL,
    `hospital_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idPatient` (`patient_id`) USING BTREE,
    INDEX `idHospital` (`hospital_id`) USING BTREE
);

CREATE TABLE `doctor` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `hospital_id` INT(11) NULL DEFAULT NULL,
    `name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `specialization` INT(11) NULL DEFAULT NULL,
    `is_available` BIT(1) NULL DEFAULT NULL COMMENT 'used as a boolean',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `doctor_hospital` (`hospital_id`) USING BTREE,
    CONSTRAINT `doctor_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `medhead`.`hospital` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `appointment` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `doctor_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    `patient_id` INT(11) UNSIGNED NULL DEFAULT NULL,
    `date` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `specialization` (
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `specilizationType` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
    PRIMARY KEY (`id`) USING BTREE
);