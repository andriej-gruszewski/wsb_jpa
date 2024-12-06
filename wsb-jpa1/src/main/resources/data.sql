INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (1, 'Warsaw', 'ul. Marszałkowska 5', 'Budynek A', '00-001');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (2, 'Kraków', 'ul. Floriańska 12', 'Mieszkanie 3', '31-100');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (3, 'Gdańsk', 'ul. Długie Ogrody 45', 'Piętro 2', '80-766');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (4, 'Wrocław', 'ul. Piłsudskiego 21', 'Lokal 10', '50-078');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (5, 'Poznań', 'ul. Gorzysława 3', 'Mieszkanie 4', '60-101');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (6, 'Łódź', 'ul. Piotrkowska 8', 'Lokal 22', '90-001');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (7, 'Szczecin', 'ul. Mickiewicza 9', 'Budynek B', '70-228');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (8, 'Lublin', 'ul. Wieniawska 15', 'Piętro 3', '20-077');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (9, 'Katowice', 'ul. Stawowa 20', 'Mieszkanie 9', '40-001');
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES (10, 'Bydgoszcz', 'ul. Stawowa 20', 'Mieszkanie 9', '40-001');


INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES (1, 'Anna', 'Kwiatkowska', '+48 22 334 56 78', 'anna.kwiatkowska@tlen.pl', 'DOC001', 'Kardiologia', 1);
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES (2, 'Tomasz', 'Nowak', '+48 500 999 333', 'tomasz.nowak@wp.pl', 'DOC002', 'Neurologia', 2);
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES (3, 'Julia', 'Zawisza', '+48 600 112 233', 'julia.zawisza@interia.pl', 'DOC003', 'Chirurgia', 3);
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES (4, 'Krzysztof', 'Borkowski', '+48 507 467 299', 'krzysztof.borkowski@poczta.onet.pl', 'DOC004', 'Internista', 4);
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES (5, 'Magdalena', 'Olszewska', '+48 789 654 321', 'magdalena.olszewska@gmail.com', 'DOC005', 'Psychiatra', 5);


INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES (1, 'Marek', 'Kwiatkowski', '+48 600 788 990', 'marek.kwiatkowski@wp.pl', 'PAT001', '1985-11-23', 6);
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES (2, 'Katarzyna', 'Mazur', '+48 512 334 556', 'katarzyna.mazur@interia.pl', 'PAT002', '1990-03-15', 7);
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES (3, 'Paweł', 'Nowicki', '+48 707 123 456', 'pawel.nowicki@gmail.com', 'PAT003', '1982-07-30', 8);
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES (4, 'Ewa', 'Jasińska', '+48 510 887 997', 'ewa.jasinska@poczta.onet.pl', 'PAT004', '1992-02-18', 9);
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES (5, 'Michał', 'Wojciechowski', '+48 733 876 543', 'michal.wojciechowski@tlen.pl', 'PAT005', '1980-09-10', 10);



INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES (1, 'Consultation on allergies', '2024-12-05 09:15:45', 1, 1);
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES (2, 'Routine checkup', '2024-11-20 15:45:12', 2, 2);
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES (3, 'Follow-up for treatment', '2024-10-10 10:30:25', 3, 3);
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES (4, 'Emergency consultation', '2024-12-01 17:00:00', 4, 4);
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES (5, 'Consultation for back pain', '2024-11-28 14:20:30', 5, 5);



INSERT INTO medical_treatment (id, description, type, visit_id) VALUES (1, 'Treatment for leg fracture', 'Surgery', 1);
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES (2, 'Blood pressure consultation', 'Consultation', 2);
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES (3, 'Cancer diagnosis', 'Diagnosis', 3);
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES (4, 'Spinal surgery', 'Surgery', 4);
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES (5, 'Knee replacement', 'Surgery', 5);
