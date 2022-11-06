create table user_session
(
    id         bigint primary key auto_increment not null,
    platform   varchar(20)                       not null,
    session_id bigint                            not null,
    parent_id  bigint
);