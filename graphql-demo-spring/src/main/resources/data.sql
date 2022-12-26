CREATE TABLE Country
(
    code        CHAR(3) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    continent   VARCHAR(255),
    PRIMARY KEY (code)
);

CREATE TABLE Person
(
    id          BIGINT NOT NULL,
    given_names  VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    birthdate   DATE,
    PRIMARY KEY (id)
);

CREATE TABLE Address
(
    id          BIGINT NOT NULL,
    street      VARCHAR(255) NOT NULL,
    number      VARCHAR(255),
    city        VARCHAR(255) NOT NULL,
    person_id    BIGINT NOT NULL,
    country_code CHAR(3),
    PRIMARY KEY (id)
);

ALTER TABLE Address ADD CONSTRAINT Address_Person_fk FOREIGN KEY (person_id) REFERENCES Person(id);
ALTER TABLE Address ADD CONSTRAINT Address_Country_fk FOREIGN KEY (country_code) REFERENCES Country(code);

CREATE TABLE Car
(
    id          BIGINT NOT NULL,
    make        VARCHAR(255) NOT NULL,
    model       VARCHAR(255) NOT NULL,
    product_year      INT,
    color       VARCHAR(255),
    vin         VARCHAR(255),
    person_id    BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE Car ADD CONSTRAINT Car_Person_fk FOREIGN KEY (person_id) REFERENCES Person(id);

INSERT INTO Country (code, name, continent) VALUES ('SVK', 'Slovekia', 'Europe');
INSERT INTO Country (code, name, continent) VALUES ('CZE', 'Czechia', 'Europe');
INSERT INTO Country (code, name, continent) VALUES ('USA', 'United States of America', 'America');
INSERT INTO Country (code, name, continent) VALUES ('CAN', 'Canada', 'America');
INSERT INTO Country (code, name, continent) VALUES ('JAP', 'Japan', 'Asia');
INSERT INTO Country (code, name, continent) VALUES ('AUS', 'Australia', 'Australia');

INSERT INTO Person (id, given_names, surname, birthdate) VALUES ( 1, 'František', 'Dobrota', '1976-10-30');
INSERT INTO Person (id, given_names, surname, birthdate) VALUES ( 2, 'Edmund', 'Peterson', '1962-02-03');
INSERT INTO Person (id, given_names, surname, birthdate) VALUES ( 3, 'Kiichiro', 'Toyoda', '1894-06-11');

INSERT INTO Address (id, street, number, city, person_id, country_code) VALUES ( 1, 'Dlhá', '32', 'Vyšný Komárnik', 1, 'SVK');
INSERT INTO Address (id, street, number, city, person_id, country_code) VALUES ( 2, 'Masarykova', '1A', 'Brno', 1, 'CZE');
INSERT INTO Address (id, street, number, city, person_id, country_code) VALUES ( 3, 'Strange avenue', '13', 'Seatle', 2, 'USA');
INSERT INTO Address (id, street, number, city, person_id, country_code) VALUES ( 4, 'Icy street', '20', 'Calgary', 2, 'CAN');

INSERT INTO Car (id, make, model, product_year, color, vin, person_id) VALUES ( 1, 'Renault', 'Espace', 2004, 'red', NULL, 1 );
INSERT INTO Car (id, make, model, product_year, color, vin, person_id) VALUES ( 2, 'Renault', 'Twingo', 1992, 'green', 'VV12398476', 1 );
INSERT INTO Car (id, make, model, product_year, color, vin, person_id) VALUES ( 4, 'Suzuki', 'SX4', 2012, 'orange', NULL, 3 );
INSERT INTO Car (id, make, model, product_year, color, vin, person_id) VALUES ( 5, 'Toyota', 'Corolla', 1964, 'white', 'TC1283974', 3 );