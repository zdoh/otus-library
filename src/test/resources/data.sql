insert into author(id, name)
values (1, 'Анджей Сапковский'), (2, 'Говард Лавкрафт');

insert into genre(id, name)
values (1, 'фэнтези'), (2, 'ужасы');

insert into book(id, name, author_id, genre_id)
values (1, 'Последнее желание', 1, 1), (2, 'Меч Предназначения', 1, 1), (3, 'Кровь эльфов', 1, 1),
       (4, 'Зов Ктулху', 2, 2), (5, 'Данвический ужас', 2, 2);


