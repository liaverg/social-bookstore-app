insert ignore into users (id, username, password, role)
    values (1, 'supplier', '$2a$10$pjrjTSwMl6zTh3blhIrDx.LsoIJ0xQwyKrsnjaeuYqAlcizyFrkX.', 'USER'),
           (2, 'requester1', '$2a$10$cVgpo7AFBe2KRc66rVNg3eco1472HPhwjtKwybasA3oa2bByJzXE.', 'USER'),
           (3, 'requester2', '$2a$10$isJOHKPlu9.UNJzKszdYSu4SbTAhsTxuHgHco3gj0eVtFI/8A5lpq', 'USER');

insert ignore into user_profiles (id, user_id, full_name, address, age, phone_number)
    values (1, 1, 'Mpampis Book Offerer', 'Stavrou Niarxou 13, Ioannina', '35', '698000000'),
           (2, 2, 'Maria Book Requester', 'Nikou Niarxou 13, Ioannina', '24', '698000001'),
           (3, 3, 'Giorgos Book Requester', 'Nikou Niarxou 13, Ioannina', '28', '698000002');

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
           (11, 'Romance'),
           (12, 'Other');

insert ignore into authors (id, name)
    values  (1, 'Brandon Sanderson'),
            (2, 'Agatha Cristie'),
            (3, 'Jane Austen'),
            (4, 'J. R. R. Tolkien');

insert ignore into user_profile_favorite_book_categories (id, user_profile_id, book_category_id)
    values (1, 1, 3),
           (2, 1, 10),
           (3, 1, 11),
           (4, 2, 3),
           (5, 2, 11),
           (6, 3, 3),
           (7, 3, 10);

insert ignore into user_profile_favorite_authors (id, user_profile_id, author_id)
    values (1, 1, 1),
           (2, 1, 3),
           (3, 1, 4),
           (4, 2, 1),
           (5, 2, 3),
           (6, 2, 4),
           (7, 3, 1),
           (8, 3, 4);

insert ignore into books (id, user_profile_id, title, book_category_id, summary)
    values (1, 1, 'Snapshot', 10, 'The Snapshot Project has been inherited by an independent city-state called New Clipperton, which enables it to recreate a day up to 10 days in the past and used to investigate the crimes on that date.'),
           (2, 1, 'Mistborn', 3, 'A secret group of Allomancers attempts to overthrow a dystopian empire and establish themselves in a world covered by ash.'),
           (3, 1, 'Pride and Prejudice', 11, 'Mr Bennet, owner of the Longbourn estate in Hertfordshire, has five daughters, but his property is entailed and can only be passed to a male heir.'),
           (4, 1, 'Persuasion', 11, 'Anne and Captain Wentworth where formerly engaged and meet again after a separation lasting almost eight years, setting the scene for a second, well-considered chance at love and marriage for Anne.'),
           (5, 1, 'Lost Chronicles of Newday', 4, 'The city of Newday has entered the cyberpunk era of the Simarilion empire. Will each inhabitants manage to survive the new changes?');

insert ignore into book_authors (id, book_id, author_id)
    values (1, 1, 1),
           (2, 2, 1),
           (3, 3, 1),
           (4, 4, 3),
           (5, 5, 2),
           (6, 5, 4);

insert ignore into book_requesting_users(id, book_id, user_profile_id, status)
    values (1, 1, 2, 'PENDING'),
           (2, 1, 3, 'PENDING'),
           (3, 4, 2, 'PENDING'),
           (4, 5, 2, 'PENDING'),
           (5, 5, 3, 'PENDING');
