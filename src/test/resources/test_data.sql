insert into author(name)
values ('Анджей Сапковский'), ('Говард Лавкрафт');

insert into genre(name)
values ('фэнтези'), ('ужасы');

insert into book(name, author_id, genre_id)
values ('Последнее желание', 1, 1), ('Меч Предназначения', 1, 1), ('Кровь эльфов', 1, 1),
       ('Зов Ктулху', 2, 2), ('Данвический ужас', 2, 2);


