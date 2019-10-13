CREATE TABLE book (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    original_title character varying(132) NOT NULL,
    norwegian_title character varying(132)
--    author_first_name character varying(100) NOT NULL,
--    author_last_name character varying(100) NOT NULL,
--    reading_order numeric(7) DEFAULT 0 NOT NULL
)