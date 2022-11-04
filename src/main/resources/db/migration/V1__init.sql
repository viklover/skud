create table card
(
    id integer primary key auto_increment not null
);

create table event
(
    id         integer primary key auto_increment not null,
    card_id    integer                            not null,
    date       bigint,
    event_type varchar(64)                        not null,
    foreign key (card_id) references card (id) on delete cascade
);

create table student
(
    id         varchar(8) primary key not null,
    card_id    integer unique,
    first_name varchar(100)           not null,
    last_name  varchar(100)           not null,
    foreign key (card_id) references card (id) on delete set null
);

create table parent
(
    id               bigint primary key auto_increment not null,
    first_name       varchar(100)                      not null,
    last_name        varchar(100)                      not null,
    telephone_number varchar(12)                       not null
);

create table students_parents
(
    id         bigint primary key auto_increment not null,
    student_id varchar(8)                        not null,
    parent_id  bigint                            not null,
    unique key (student_id, parent_id)
);

create table notification_settings
(
    id        bigint primary key auto_increment not null,
    parent_id bigint                            not null,
    foreign key (parent_id) references parent (id) on delete cascade
);

