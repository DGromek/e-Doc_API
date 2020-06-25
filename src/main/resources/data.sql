INSERT INTO patient(pesel, first_name, last_name, password, postal_code, city, street, house_nr, email, phone_number) VALUES
('68072503271', 'Adrian', 'Grzelczyk', '$2y$12$QUGnEfrCGvHcbZGlpXUW.eaGygpqDsdgGGo3HunGqnHlM6dzSkXFu', '05-271', 'Warszawa', 'Al. Jana Pawła II', '76 m.34', 'aGrzelczyk@onet.pl', '893 271 312'),
('55021121379', 'Grażyna', 'Zawadzka', '$2y$12$QUGnEfrCGvHcbZGlpXUW.eaGygpqDsdgGGo3HunGqnHlM6dzSkXFu', '90-162', 'Łódź', 'ul. Kardynała Stefana Wyszyńskiego', '42A', 'kicia21@wp.pl', '571 921 901'),
('99052101841', 'Karol', 'Krzynowicz', '$2y$12$QUGnEfrCGvHcbZGlpXUW.eaGygpqDsdgGGo3HunGqnHlM6dzSkXFu', '12-654', 'Stara Wieś', 'Stara Wieś', '231', 'KKrzyno99@gmail.com', '231 716 800');

INSERT INTO doctor(first_name, last_name, speciality) VALUES
('Jan', 'Kowalczyk', 'Ginekolog'),
('Dariusz', 'Strzykała', 'Internista'),
('Marek', 'Gormiński', 'Pediatra'),
('Mateusz', 'Stanisławowski', 'Pediatra'),
('Adam', 'Nagorski', 'Kardiolog'),
('Karolina', 'Macążek', 'Chirurg'),
('Sylwester', 'Fritz', 'Okulista');

INSERT INTO clinic(name, postal_code, city, street, house_nr, flat_nr, phone_number) VALUES
('Wojewódzkie Wielospecjalistyczne Centrum Onkologii i Traumatologii im. M. Kopernika w Łodzi', '90-001', 'Łódź', 'Pabianicka', '61', NULL, '426895000'),
('Centrum Medyczne im. dr. L. Rydygiera Sp. z o.o.', '90-001', 'Łódź', 'Doktora Seweryna Sterlinga', '13', NULL, '426323465');

INSERT INTO appointment(date_of_appointment, clinic_id, doctor_id, patient_pesel, status) VALUES
('2020-06-02 13:30', 1, 2, '68072503271', 'DONE'),
('2020-12-01 12:15', 2, 4, '68072503271', 'PENDING'),
('2020-08-12 08:00', 1, 3, '68072503271', 'CANCELED');

INSERT INTO schedule(fr_begin, fr_end, mo_begin, mo_end, sa_begin, sa_end, su_begin, su_end, th_begin, th_end, tu_begin, tu_end, we_begin, we_end, clinic_id, doctor_id) VALUES
('8:00', '16:00', '14:00', '18:00', '8:00', '8:00', '8:00', '8:00', '12:00', '16:30', '15:30', '17:30', '8:00', '16:00', 1, 1),
('8:00', '16:00', '14:00', '18:00', '8:00', '8:00', '8:00', '8:00', '12:00', '16:30', '15:30', '17:30', '8:00', '16:00', 1, 2),
('9:00', '17:00', '9:00', '17:00', '8:00', '8:00', '8:00', '8:00', '16:00', '16:00', '16:00', '17:00', '10:00', '18:00', 1, 3);

