INSERT INTO person (id, name, age) VALUES
(1, 'Vasya', 29),
(2, 'Petya', 34);
INSERT INTO personal_data (id, login, password, person_id) VALUES
(1,'Sergeev', '1234', 1),
(2, 'Ivanov', '5678', 2);
INSERT INTO community (id, name, description) VALUES
(1,'Football', 'Football like'),
(2,'Dances', 'Dances like');
INSERT INTO comments (id, comment, person_id) VALUES
(1, 'Hello Google', 1),
(2, 'My name is Vasya', 1),
(3, 'Hi, Vasya', 2),
(4, 'But, I am not Google', 2),
(5, 'I am Petya', 2);
INSERT INTO person_communities (community_id, person_id) VALUES
(1,1),
(1,2),
(2,1),
(2,2);


