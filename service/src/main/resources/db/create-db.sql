DROP SCHEMA IF EXISTS example_jsonb CASCADE;

DROP ROLE IF EXISTS owner_example_jsonb;

CREATE ROLE owner_example_jsonb WITH
NOLOGIN
NOSUPERUSER
INHERIT
NOCREATEDB
NOCREATEROLE
NOREPLICATION;

GRANT owner_example_jsonb TO postgres;

CREATE SCHEMA example_jsonb AUTHORIZATION owner_example_jsonb;

GRANT ALL ON SCHEMA example_jsonb TO owner_example_jsonb;

CREATE TYPE example_jsonb.genre AS ENUM ('DRAMA', 'ROMANCE', 'GUIDE', 'TRAVEL');

ALTER TYPE example_jsonb.genre OWNER TO owner_example_jsonb;

CREATE TABLE example_jsonb.books
(
  id          uuid,
  title       text NOT NULL,
  genre       example.genre NOT NULL,
  publisher   text NOT NULL,
  star        bigint NOT NULL,
  author      jsonb  NOT NULL DEFAULT '{}',
  PRIMARY KEY (id),
  CONSTRAINT books_check CHECK (star > 0 AND star <= 5));

ALTER TABLE example_jsonb.books OWNER to owner_example_jsonb;
