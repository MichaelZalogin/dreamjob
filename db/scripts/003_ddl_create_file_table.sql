CREATE TABLE file
(
    id   serial PRIMARY KEY,
    name varchar NOT NULL,
    path varchar NOT NULL UNIQUE
);