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


INSERT INTO Facility (name, description, created_at, address, city, total_rating, active) VALUES ('Teretana', 'Najjaca teretana','2024-05-22','Novi Sad', 'Novi Sad','10',true);

INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 1);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 2);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 3);
INSERT INTO facility_disciplines (facility_id, discipline_id) VALUES (1, 4);

INSERT INTO Image (image) VALUES ('image_path_1');
INSERT INTO Image (image) VALUES ('image_path_2');
INSERT INTO Image (image) VALUES ('image_path_3');

INSERT INTO user_image (user_id, image_id) VALUES (1, 1);
INSERT INTO user_image (user_id, image_id) VALUES (2, 2);
INSERT INTO user_image (user_id, image_id) VALUES (3, 3);




-- CREATE TABLE account_request (
--        id BIGINT PRIMARY KEY AUTO_INCREMENT,
--        password VARCHAR(255),
--        name VARCHAR(100),
--        surname VARCHAR(100),
--        email VARCHAR(100),
--        phone_number VARCHAR(20),
--        address VARCHAR(255),
--        created_at DATE,
--        birthday DATE,
--        city VARCHAR(100),
--        zip_code VARCHAR(10),
--        status VARCHAR(10)
-- );



