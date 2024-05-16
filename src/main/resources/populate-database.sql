# username: test, password: test
insert ignore into users (id, username, password, role)
    values (1, 'test', '$2a$10$ku.wChLUJ8oe8mxS/fa52eBaParyGO5B.y9F44gor85IAo1dyqA/q', 'USER');

insert ignore into user_profiles (id, user_id, full_name, address, age, phone_number)
    values (1, 1, 'Mpampis Mpampoounis', 'Stavrou Niarxou 13, Ioannina', '24', '698000000');

insert ignore into book_categories (id, category)
    values (1, 'Art'),
           (2, 'Comic'),
           (3, 'Fantasy'),
           (4, 'Fiction'),
           (5, 'Biographies'),
           (6, 'History'),
           (7, 'Science'),
           (8, 'Literature'),
           (9, 'Adventure'),
           (10, 'Crime'),
           (11, 'Other');

insert ignore into authors (id, name)
    values  (1, 'Brandon Sanderson'),
            (2, 'Agatha Cristie');

insert ignore into user_profile_favorite_book_categories (id, user_profile_id, book_category_id)
    values (1, 1, 3),
           (2, 1, 10);

insert ignore into user_profile_favorite_authors (id, user_profile_id, author_id)
    values (1, 1, 1),
           (2, 1, 2);

