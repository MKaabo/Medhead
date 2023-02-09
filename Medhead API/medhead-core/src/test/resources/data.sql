INSERT INTO hospital (`id`, `name`, `position`, `total_beds`, `beds_available`) VALUES
    (1, 'Hôpital Nord', '5.362163,43.379383', 150, 5),
    (2, 'Hôpital Montperrin', '5.438660,43.522411', 50, 3),
    (3, 'Hôpital Lariboisière', '2.341291,48.917518', 300, 250),
    (4, 'Hôpital Vaugirard', '2.280501199302833,48.86794770440761', 600, 286),
    (5, 'Centre Hospitalier de Tourcoing', '3.1572596483538806,50.805934725554344', 200, 110),
    (6, 'Centre Hospitalier de Dunkerque', '2.3827235478750013, 51.10697104799068', 20, 5),
    (7, 'Hôpitaux universitaires de Strasbourg', '7.743594610409657,48.58344604062807', 180, 0),
    (8, 'Hôpital Edouard Herriot', '4.873966140508923,45.81929878885463', 1000, 120),
    (9, 'Centre hospitalier Métropole Savoie', '5.895694613481063,45.80781265776782', 180, 0),
    (10, 'Hôpital Rangueil', '1.4455293705867536,43.614261693278266', 1000, 120),
    (11, 'Centre Hospitalier Sèvre et Loire', '-1.4940893252308407,47.20258004614798', 500, 365),
    (12, 'Christchurch Hospital', '172.62524408736607,-43.531889564739515', 1800, 1800);

INSERT INTO doctor (`id`, `hospital_id`, `name`, `specialization`, `is_available`) VALUES
    (1, 1, 'Sean Bean', 2, 1),
    (2, 2, 'Eric Dupont', 0, 1),
    (3, 1, 'Mia Fay', 1, 0);

INSERT INTO patient (`id`, `name`, `info`, `age`, `email`, `phone`, `position`, `specialization`) VALUES
    (1, 'Jean Delaterasse', 'Appointement in December showed hearth weakness', 58, 'jean.delaterasse@gmail.com', 0, '5.461099,43.494378', 1),
    (2, 'Frankie Sinatre', 'Emergency response in January for a brain disease', 82, NULL, 0, '5.439834,43.321871', 2),
    (3, 'Yves Pons', 'Entorse', 21, NULL, NULL, '5.563375;43.449765', NULL);