create table if not exists users(
    id                  bigint              not null     auto_increment,
    username            varchar(255)        unique,
    password            varchar(255)        not null,
    role                varchar(255)        not null,
    primary key (id)
);