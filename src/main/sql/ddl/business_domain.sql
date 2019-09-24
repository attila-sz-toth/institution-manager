INSERT INTO institutionm.roles (id, role) VALUES
(0, 'ADMIN'),
(1, 'EMPLOYEE');

insert into institutionm.institution_types (id, type) VALUES
(0, 'IDOSEK_OTTHONA'),
(1, 'CSALADI_BOLCSODE'),
(2, 'REHABILITACIO'),
(3, 'HAZI_SEGITSEGNYUJTAS'),
(4, 'NEVELOSZULOI_HALOZAT');

INSERT INTO institutionm.care_types (id, care_type) VALUES
(0, 'IDOSEK_OTTHONA'),
(1, 'HAZI_SEGITSEG_NYUJTAS'),
(2, 'CSALADI_BOLCSODE'),
(3, 'SZENVEDELYBETEGEK_REHABILITACIOJA'),
(4, 'CSALADI_BOLCSODE'),
(5, 'CSALADOK_ATMENETI_OTTHONA'),
(6, 'KULSO_FEROHELY'),
(7, 'OTTHONT_NYUJTO_ELLATAS'),
(8, 'KULONLEGES_ELLATAS'),
(9, 'UTOGONDOZOI_ELLATAS');

insert into institutionm.institutions (id, name, type_id) values
(0, 'Budapesti Idosek Otthona', 0),
(1, 'New Jersey Rehabilitation', 2),
(2, 'Kiskunszentborzasztoi Bolcsod', 1);

insert into institutionm.institutions_to_users (institution_id, user_id) VALUES
(0, 37),
(1, 37);
