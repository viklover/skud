create table session_config
(
    id bigint primary key auto_increment not null
);

create table user_session
(
    id         bigint primary key auto_increment not null,
    platform   varchar(20)                       not null,
    session_id bigint                            not null,
    parent_id  bigint                            not null,
    config_id  bigint                            not null,
    foreign key (parent_id) references parent (id) on delete cascade,
    foreign key (config_id) references session_config (id)
);

create table notification
(
    id              bigint primary key auto_increment not null,
    type            text                              not null,
    content         text                              not null,
    user_session_id bigint                            not null,
    payload         text,
    foreign key (user_session_id) references user_session (id) on delete cascade
);