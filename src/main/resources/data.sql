INSERT INTO author(name) values('Анджей Сапковский');
INSERT INTO author(name) values('Говард Лавкрафт');

INSERT INTO genre(name) values('фэнтези');
INSERT INTO genre(name) values('ужасы');

INSERT INTO book(name, author_id, genre_id) values ('Последнее желание', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Меч Предназначения', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Кровь эльфов', 1, 1);
INSERT INTO book(name, author_id, genre_id) values ('Зов Ктулху', 2, 2);
INSERT INTO book(name, author_id, genre_id) values ('Данвический ужас', 2, 2);

INSERT INTO comment(comment, book_id) values ('очень классная книга', 1);
INSERT INTO comment(comment, book_id) values ('читал оторваться не мог', 2);
INSERT INTO comment(comment, book_id) values ('не читал, но одобряю', 4);
INSERT INTO comment(comment, book_id) values ('название внушает уважение', 5);
INSERT INTO comment(comment, book_id) values ('из-за названия купил, но пока не читал', 3);
INSERT INTO comment(comment, book_id) values ('не понравилось, игра лучше', 2);
