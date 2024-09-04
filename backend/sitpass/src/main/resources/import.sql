-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za sve user-e je 123

INSERT INTO ROLE (name) VALUES ('ADMIN');
INSERT INTO ROLE (name) VALUES ('MANAGER');
INSERT INTO ROLE (name) VALUES ('USER');
INSERT INTO USERS (email, password, name, surname, created_at, phone_number, birthday, address, city, zip_code, enabled) VALUES ('admin@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'Admin', '2024-08-13', '066123321', '2000-08-13',  'Admin Address','Novi Sad','21000',true);
INSERT INTO USERS (email, password, name, surname, created_at, phone_number, birthday, address, city, zip_code, enabled) VALUES ('manager@email.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Manager', 'Manager', '2024-08-13', '066123321', '2000-08-13', 'Manager Address', 'Novi Sad','21000',true);
INSERT INTO USERS (email, password, name, surname, created_at, phone_number, birthday, address, city, zip_code, enabled) VALUES ('user@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'User', '2024-08-13', '066123321','2000-08-13', 'User Address', 'Novi Sad','21000',true);

INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='admin@email.com'), 1);
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='manager@email.com'), 2);
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='user@email.com'), 3);

INSERT INTO Discipline (name) VALUES ('Teretana');
INSERT INTO Discipline (name) VALUES ('Aerobik');
INSERT INTO Discipline (name) VALUES ('Funkcionalni trening');
INSERT INTO Discipline (name) VALUES ('Pilates');
INSERT INTO Discipline (name) VALUES ('Zumba');
INSERT INTO Discipline (name) VALUES ('Personalni trening');
INSERT INTO Discipline (name) VALUES ('Individualni trening');
INSERT INTO Discipline (name) VALUES ('Vodjeni trening');
INSERT INTO Discipline (name) VALUES ('Grupni trening');


INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('Iron Republic 2', 'Iron RePublic je više od teretane.','2024-05-22','Novi Sad', 'Novi Sad','0',true);
INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('Ahilej Zemun', 'Najjaca teretana','2024-05-22','Beograd', 'Beograd','9.91',true);
INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('Ahilej Medakovic', 'Najjaca teretana','2024-05-22','Medakovic', 'Beograd','0',false);
INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('Ahilej Novi Sad', 'Najjaca teretana','2024-05-22','Grbavica', 'Novi Sad','9',true);
INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('FitZone', 'Najjaca teretana','2024-05-22','Spens', 'Novi Sad','9',true);

INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 2);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 3);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 4);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (2, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (2, 3);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (2, 4);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (3, 3);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (3, 4);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (3, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (4, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (4, 2);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (4, 5);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (4, 6);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (5, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (5, 7);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (5, 8);


INSERT INTO Image (image) VALUES ('image_path_1');
INSERT INTO Image (image) VALUES ('image_path_2');
INSERT INTO Image (image) VALUES ('image_path_3');
INSERT INTO Image (image) VALUES ('image_path_4');
INSERT INTO Image (image) VALUES ('image_path_5');
INSERT INTO Image (image) VALUES ('image_path_6');
INSERT INTO Image (image) VALUES ('image_path_7');
INSERT INTO Image (image) VALUES ('image_path_8');
INSERT INTO Image (image) VALUES ('image_path_9');
INSERT INTO Image (image) VALUES ('image_path_10');
INSERT INTO Image (image) VALUES ('image_path_11');
INSERT INTO Image (image) VALUES ('image_path_12');
INSERT INTO Image (image) VALUES ('image_path_13');
INSERT INTO Image (image) VALUES ('image_path_14');
INSERT INTO Image (image) VALUES ('image_path_15');


INSERT INTO user_image (user_id, image_id) VALUES (1, 1);
INSERT INTO user_image (user_id, image_id) VALUES (2, 2);
INSERT INTO user_image (user_id, image_id) VALUES (3, 3);

INSERT INTO facility_images (facility_id, image_id) VALUES (1, 4);
INSERT INTO facility_images (facility_id, image_id) VALUES (1, 5);
INSERT INTO facility_images (facility_id, image_id) VALUES (1, 6);
INSERT INTO facility_images (facility_id, image_id) VALUES (2, 7);
INSERT INTO facility_images (facility_id, image_id) VALUES (2, 8);
INSERT INTO facility_images (facility_id, image_id) VALUES (3, 9);
INSERT INTO facility_images (facility_id, image_id) VALUES (3, 10);
INSERT INTO facility_images (facility_id, image_id) VALUES (4, 11);
INSERT INTO facility_images (facility_id, image_id) VALUES (4, 12);
INSERT INTO facility_images (facility_id, image_id) VALUES (5, 13);
INSERT INTO facility_images (facility_id, image_id) VALUES (5, 14);
INSERT INTO facility_images (facility_id, image_id) VALUES (5, 14);


INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',0, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',1, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',2, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',3, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',4, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',5, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',6, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',0, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',1, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',2, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',3, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',4, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',5, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',6, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',0, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',1, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',2, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',3, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',4, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',5, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',6, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',0, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',1, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',2, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',3, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',4, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',5, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',6, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',0, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',1, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',2, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',3, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',4, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',5, '08:00', '22:00');
INSERT INTO work_day (valid_from, day_of_week, from_time, until_time ) VALUES ('2024-05-22',6, '08:00', '22:00');


INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 1);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 2);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 3);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 4);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 5);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 6);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (1, 7);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 8);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 9);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 10);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 11);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 12);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 13);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (2, 14);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 15);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 16);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 17);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 18);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 19);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 20);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (3, 21);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 22);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 23);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 24);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 25);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 26);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 27);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (4, 28);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 29);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 30);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 31);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 32);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 33);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 34);
INSERT INTO facility_workdays (facility_id, workday_id) VALUES (5, 35);

INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (3, '2024-05-22 12:00', '2024-05-22 13:00', 1);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (3, '2024-05-23 12:00', '2024-05-23 13:00', 2);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (3, '2024-05-24 12:00', '2024-05-24 13:00', 2);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (2, '2024-05-24 12:00', '2024-05-24 13:00', 2);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (1, '2024-05-24 12:00', '2024-05-24 13:00', 2);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (1, '2024-05-24 12:00', '2024-05-24 13:00', 4);
INSERT INTO exercises (user_id, from_time, until_time, facility_id) VALUES (1, '2024-05-24 12:00', '2024-05-24 13:00', 5);

INSERT INTO Rate(equipment,stuff,hygiene,space) VALUES(10,10,10,9);
INSERT INTO Rate(equipment,stuff,hygiene,space) VALUES(10,10,10,10);
INSERT INTO Rate(equipment,stuff,hygiene,space) VALUES(10,10,10,10);
INSERT INTO Rate(equipment,stuff,hygiene,space) VALUES(9,9,9,9);
INSERT INTO Rate(equipment,stuff,hygiene,space) VALUES(9,9,9,9);

INSERT INTO Review(user_id, facility_id, created_at, exercise_count,hidden, comment_id) VALUES (3,2,'2024-05-22 15:00', 2, true, null);
INSERT INTO Review(user_id, facility_id, created_at, exercise_count,hidden, comment_id) VALUES (2,2,'2024-05-24 15:00', 1, false, null);
INSERT INTO Review(user_id, facility_id, created_at, exercise_count,hidden, comment_id) VALUES (1,2,'2024-05-24 16:00', 1, false, null);
INSERT INTO Review(user_id, facility_id, created_at, exercise_count,hidden, comment_id) VALUES (1,4,'2024-05-24 16:00', 1, false, null);
INSERT INTO Review(user_id, facility_id, created_at, exercise_count,hidden, comment_id) VALUES (1,5,'2024-05-24 16:00', 1, false, null);

INSERT INTO review_rate(review_id, rate_id) VALUES (1,1);
INSERT INTO review_rate(review_id, rate_id) VALUES (2,2);
INSERT INTO review_rate(review_id, rate_id) VALUES (3,3);
INSERT INTO review_rate(review_id, rate_id) VALUES (4,4);
INSERT INTO review_rate(review_id, rate_id) VALUES (5,5);


INSERT INTO manages(start_time, end_time, user_id, facility_id) VALUES ('2024-05-22','2024-10-22',2,1);
INSERT INTO manages(start_time, end_time, user_id, facility_id) VALUES ('2024-05-22','2024-10-22',2,2);
INSERT INTO manages(start_time, end_time, user_id, facility_id) VALUES ('2024-05-22','2024-10-22',2,4);
INSERT INTO manages(start_time, end_time, user_id, facility_id) VALUES ('2024-05-22','2024-10-22',2,5);

# INSERT INTO comment(text, user_id, review_id) VALUES ('Odlicna teretana',3,1);
