CREATE TABLE users
(
    id             serial              NOT NULL PRIMARY KEY,
    email          character varying(128) NOT NULL,
    created        timestamp              NOT NULL default current_timestamp,
    updated        timestamp              NOT NULL default current_timestamp,
    hashedPassword character varying(256) NOT NULL,
    deleted        timestamp
);

CREATE TABLE author
(
    id        serial             NOT NULL PRIMARY KEY,
    first_name character varying(64) NOT NULL,
    last_name  character varying(64) NOT NULL
);

CREATE TABLE tag
(
    name    character varying(128) PRIMARY KEY,
    deleted timestamp
);

CREATE TYPE medium AS ENUM ('PAPIR', 'EBOK', 'LYDBOK');

CREATE TABLE book
(
    id              serial              NOT NULL PRIMARY KEY,
    original_title  character varying(132) NOT NULL,
    norwegian_title character varying(132),
    language character varying(64),
    medium medium,
    reading_order numeric(7) DEFAULT 0 NOT NULL
);

CREATE TABLE book_tag
(
    tag_name    character varying(128) NOT NULL REFERENCES tag(name),
 --   user_id int NOT NULL REFERENCES users(id),
    book_id int NOT NULL REFERENCES book(id),
 --   tagged timestamp NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY(book_id, tag_name)
);

CREATE TABLE book_authors
(
    books_id int NOT NULL REFERENCES book(id),
    authors_id int NOT NULL references author(id),
    PRIMARY KEY (books_id, authors_id)
)
