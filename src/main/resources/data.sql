INSERT INTO author(name) values('Анджей Сапковский');
INSERT INTO author(name) values('Говард Лавкрафт');

INSERT INTO genre(name) values('фэнтези');
INSERT INTO genre(name) values('ужасы');

INSERT INTO book(name, author_id, genre_id) values ('Последнее желание', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Меч Предназначения', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Кровь эльфов', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Зов Ктулху', 2, 2);
INSERT INTO book(name, author_id, genre_id) values ('Данвический ужас', 2, 2);