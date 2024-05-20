create table if not exists users(
    id                  bigint              not null     auto_increment,
    username            varchar(255)        unique,
    password            varchar(255)        not null,
    role                varchar(255)        not null,
    primary key (id)
);

create table if not exists user_profiles(
    id                      bigint              not null     auto_increment,
    user_id                 bigint              not null     unique,
    full_name               varchar(255),
    address                 varchar(255),
    age                     varchar(3),
    phone_number            varchar(15),
    foreign key (user_id)   references users (id) on delete cascade,
    primary key (id)
);

create table if not exists book_categories(
    id                  bigint              not null     auto_increment,
    category            varchar(255),
    primary key (id)
);

create table if not exists authors(
    id                  bigint              not null     auto_increment,
    name                varchar(255),
    primary key (id)
);

create table if not exists user_profile_favorite_book_categories(
    id                              bigint              not null     auto_increment,
    user_profile_id                 bigint              not null,
    book_category_id                bigint              not null,
    foreign key (user_profile_id)   references user_profiles (id) on delete cascade,
    foreign key (book_category_id)  references book_categories (id) on delete cascade,
    primary key (id)
);

create table if not exists user_profile_favorite_authors(
    id                              bigint              not null     auto_increment,
    user_profile_id                 bigint              not null,
    author_id                       bigint              not null,
    foreign key (user_profile_id)   references user_profiles (id) on delete cascade,
    foreign key (author_id)         references authors (id) on delete cascade,
    primary key (id)
);

create table if not exists books(
    id                              bigint              not null     auto_increment,
    user_profile_id                 bigint              not null,
    title                           varchar(255)        not null,
    book_category_id                bigint              not null,
    summary                         varchar(255)        not null,
    foreign key (user_profile_id)   references user_profiles (id) on delete cascade,
    foreign key (book_category_id)  references book_categories (id) on delete cascade,
    primary key (id)
);

create table if not exists book_authors(
    id                              bigint              not null     auto_increment,
    book_id                         bigint              not null,
    author_id                       bigint              not null,
    foreign key (book_id)           references books (id) on delete cascade,
    foreign key (author_id)         references authors (id) on delete cascade,
    primary key (id)
);

create table if not exists book_requesting_users(
    id                              bigint              not null     auto_increment,
    book_id                         bigint              not null,
    user_profile_id                 bigint              not null,
    status                          varchar(255)        not null,
    foreign key (book_id)           references books (id) on delete cascade,
    foreign key (user_profile_id)   references user_profiles (id) on delete cascade,
    primary key (id)
);
