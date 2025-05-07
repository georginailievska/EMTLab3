-- Create 'country' table
CREATE TABLE country (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         continent VARCHAR(255) NOT NULL
);

-- Create 'host' table
CREATE TABLE host (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      country_id INT NOT NULL,
                      FOREIGN KEY (country_id) REFERENCES country(id)
);

-- Create 'category' enum type for accommodation (matching the Java enum values)
CREATE TYPE category_enum AS ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');

-- Create 'accommodation' table
CREATE TABLE accommodation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category category_enum NOT NULL,
    num_rooms INT NOT NULL,
    rented BOOLEAN NOT NULL,
    host_id INT NOT NULL,
    FOREIGN KEY (host_id) REFERENCES host(id)
);

-- Create 'temporary_reservation' table
CREATE TABLE temporary_reservation (
   id SERIAL PRIMARY KEY,
   user_id VARCHAR(255) NOT NULL,
   accommodation_id INT NOT NULL,
   confirmed BOOLEAN NOT NULL,
   FOREIGN KEY (accommodation_id) REFERENCES accommodation(id)
);

-- Create 'users' table (for temporary_reservation)
CREATE TABLE users (
   username VARCHAR(255) PRIMARY KEY,
   password VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   surname VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL
);
