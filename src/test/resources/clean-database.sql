set foreign_key_checks = 0;

truncate table users;
truncate table user_profiles;
truncate table authors;
truncate table book_categories;
truncate table user_profile_favorite_authors;
truncate table user_profile_favorite_book_categories;
truncate table books;
truncate table book_authors;

alter table users auto_increment = 1;
alter table user_profiles auto_increment = 1;
alter table authors auto_increment = 1;
alter table book_categories auto_increment = 1;
alter table user_profile_favorite_authors auto_increment = 1;
alter table user_profile_favorite_book_categories auto_increment = 1;
alter table books auto_increment = 1;
alter table book_authors auto_increment = 1;

set foreign_key_checks = 1;