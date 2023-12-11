MERGE INTO user_info VALUES (101, 'email@bk.ru', 'password', 'ROLE_USER');
MERGE INTO user_info VALUES (102, 'email2@bk.ru', 'password2', 'ROLE_USER');

MERGE INTO users VALUES (101, true, 'firstName1', 'lastName1','88888888',101);
MERGE INTO users VALUES (102, true, 'firstName2', 'lastName2','888888882',102);