CREATE TABLE vacancy
(
    id            serial PRIMARY KEY,
    title         varchar NOT NULL,
    description   varchar NOT NULL,
    creation_date timestamp,
    visible       boolean NOT NULL,
    city_id       int REFERENCES city (id),
    file_id       int REFERENCES file (id)
);