create table author(
    id int auto_increment,
    name varchar(255),
    primary key (id)
);

create table genre(
    id int auto_increment,
    name varchar(255),
    primary key (id)
);

create table book(
    id int auto_increment,
    name varchar(255),
    author_id int references author (id),
    genre_id int references genre (id),
    primary key (id)
);
