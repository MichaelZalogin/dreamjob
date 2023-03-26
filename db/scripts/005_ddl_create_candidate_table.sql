CREATE TABLE candidate
(
    id            serial PRIMARY KEY,
    name         varchar NOT NULL,
    description   varchar NOT NULL,
    creation_date timestamp,
    city_id       int REFERENCES city(id),
    file_id       int REFERENCES file(id)
);