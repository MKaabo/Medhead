INSERT INTO `hospital` (`id`, `name`, `position`, `total_beds`, `beds_available`)
VALUES  (1, 'Hôpital Nord', '5.362163,43.379383', 150, 5),
        (2, 'Hôpital Montperrin', '5.438660,43.522411', 50, 3);
INSERT INTO `doctor` (`id`, `hospital_id`, `name`, `specialization`, `is_available`)
VALUES  (1, 1, 'Sean Bean', 2, b'1'),
        (2, 2, 'Eric Dupont', 0, b'1'),
        (3, 1, 'Mia Fay', 1, b'0');
INSERT INTO `patient` (`id`, `name`, `info`, `age`, `email`, `phone`, `position`, `specialization`)
VALUES  (1, 'Jean Delaterasse', 'Appointement in December showed hearth weakness', 58, 'jean.delaterasse@gmail.com', 0, '5.461099,43.494378', 1),
        (2, 'Frankie Sinatre', 'Emergency response in January for a brain disease', 82, NULL, 0, '5.439834,43.321871', 2),
        (3, 'Yves Pons', 'Entorse', 21, NULL, NULL, '5.563375,43.449765', NULL);