CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY, 
    _name CHARACTER VARYING(50) NOT NULL, 
    age CHARACTER VARYING(50) NOT NULL
);