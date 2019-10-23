INSERT INTO users (id, email, hashedPassword)
VALUES (1, 'email@test.com', 'hash');

INSERT INTO author(id, first_name, last_name)
VALUES (1, 'Stephen', 'King');

INSERT INTO author(id, first_name, last_name)
VALUES (2, 'Mrs.', 'King');

INSERT INTO tag(name)
VALUES ('BOUGHT'),
VALUES ('WISH_LIST');

INSERT INTO book(id, original_title, norwegian_title, medium)
VALUES (1, 'The Shining', 'Ondskapens hotell', 'PAPIR');

INSERT INTO book(id, original_title, norwegian_title, medium)
VALUES (2, '11/22/63', '11/22/63', 'EBOK');

INSERT INTO tagging(tag_name, user_id, book_id)
VALUES ('BOUGHT', 1, 1);

INSERT INTO tagging(tag_name, user_id, book_id)
VALUES ('WISH_LIST', 1, 2);

INSERT INTO book_authors(books_id, authors_id)
VALUES (1, 1);

INSERT INTO book_authors(books_id, authors_id)
VALUES (1, 2);

INSERT INTO book_authors(books_id, authors_id)
VALUES (2, 1);