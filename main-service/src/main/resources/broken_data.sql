

INSERT INTO categories values (1, 'name', 3, '123_ling');
INSERT INTO categories values (2, 'name', 1, '123_ling');


INSERT INTO attributes values (1, 'diagonal');
INSERT INTO attributes values (2, 'height');
INSERT INTO attributes values (3, 'weight');
INSERT INTO attributes values (4, 'width');
INSERT INTO attributes values (5, 'socket');

INSERT INTO category_attributes values (1, 1, 1, 2, 'ONE_TYPE');
INSERT INTO category_attributes values (2, 2, 2, 3, 'SECOND_TYPE');
INSERT INTO category_attributes values (3, 3, 3, 4, 'THIRD_TYPE');
INSERT INTO category_attributes values (4, 4, 4, 5, 'FOURTH_TYPE');
INSERT INTO category_attributes values (5, 5, 5, 1, 'FIFTH_TYPE');
-- --
insert into model_attributes values (1, 1, 'FirstValue', 1);
insert into model_attributes values (2, 1, 'SecondValue', 2);
insert into model_attributes values (3, 1, 'ThirdValue', 3);
insert into model_attributes values (4, 1, 'ForthValue', 4);
insert into model_attributes values (5, 1, 'FifthValue', 1);