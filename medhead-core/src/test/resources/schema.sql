CREATE TABLE IF NOT EXISTS `hospital` (
    `id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR NOT NULL,
    `position` VARCHAR NOT NULL,
    `total_beds` INTEGER NOT NULL,
    `beds_available` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `doctor` (
    `id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `hospital_id` INTEGER DEFAULT NULL,
    `name` VARCHAR DEFAULT NULL,
    `specialization` INTEGER DEFAULT NULL,
    `is_available` BIT DEFAULT NULL COMMENT 'used as a boolean',
    foreign key (hospital_id) references hospital(id)
);

CREATE TABLE IF NOT EXISTS `patient` (
    `id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR NOT NULL,
    `info` TEXT DEFAULT NULL,
    `age` TINYINT DEFAULT NULL,
    `email` TINYTEXT DEFAULT NULL,
    `phone` INTEGER DEFAULT NULL,
    `position` VARCHAR DEFAULT NULL,
    `specialization` INTEGER DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS `emergency` (
    `id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `patient_id` INTEGER NOT NULL,
    `hospital_id` INTEGER DEFAULT NULL,
    foreign key (patient_id) references patient(id),
    foreign key (hospital_id) references hospital(id)
);

CREATE TABLE IF NOT EXISTS `appointment` (
    `id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `doctor_id` INTEGER DEFAULT NULL,
    `patient_id` INTEGER DEFAULT NULL,
    `date` date DEFAULT NULL,
     foreign key (patient_id) references patient(id),
     foreign key (doctor_id) references doctor(id)
);
