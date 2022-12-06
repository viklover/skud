create table card
(
    id      bigint primary key auto_increment,
    card_id bigint not null
);

create table event
(
    id         integer primary key auto_increment,
    student_id varchar(8)  not null,
    timestamp  bigint,
    date       varchar(9),
    event_type varchar(64) not null
);

create table student
(
    id         varchar(8) primary key,
    card_id    bigint unique,
    first_name varchar(100) not null,
    last_name  varchar(100) not null
);

create table parent
(
    id               bigint primary key auto_increment,
    first_name       varchar(100)       not null,
    last_name        varchar(100)       not null,
    telephone_number varchar(12) unique not null
);

create table students_parents
(
    id         bigint primary key auto_increment,
    student_id varchar(8) not null,
    parent_id  bigint     not null,
    unique key (student_id, parent_id),
    foreign key (student_id) references student (id) on delete cascade,
    foreign key (parent_id) references parent (id) on delete cascade
);