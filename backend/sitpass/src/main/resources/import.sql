-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za sve user-e je 123

INSERT INTO ROLE (name) VALUES ('ADMIN');
INSERT INTO ROLE (name) VALUES ('MANAGER');
INSERT INTO ROLE (name) VALUES ('USER');
INSERT INTO USERS (email, password, first_name, last_name, phone_number, address, enabled) VALUES ('admin@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'Admin', '1234567890', 'Admin Address', true);
INSERT INTO USERS (email, password, first_name, last_name, phone_number, address, enabled) VALUES ('manager@email.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Manager', 'Manager', '4567890123', 'Manager Address', true);
INSERT INTO USERS (email, password, first_name, last_name, phone_number, address, enabled) VALUES ('user@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'User', '9012345678', 'User Address', true);

INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='admin@email.com'), 1);
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='manager@email.com'), 2);
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT id FROM USERS WHERE email='user@email.com'), 3);

