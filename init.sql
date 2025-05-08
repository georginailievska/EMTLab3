-- Create 'country' table
CREATE TABLE country (
 id BIGSERIAL PRIMARY KEY,
 name VARCHAR(255) NOT NULL,
 continent VARCHAR(255) NOT NULL
);

-- Create 'host' table
CREATE TABLE host (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  country_id BIGINT NOT NULL,
  FOREIGN KEY (country_id) REFERENCES country(id)
);

-- Create 'accommodation' table
CREATE TABLE accommodation (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   category VARCHAR NOT NULL,
   num_rooms INT NOT NULL,
   rented BOOLEAN NOT NULL,
   host_id BIGINT NOT NULL,
   FOREIGN KEY (host_id) REFERENCES host(id)
);

-- Create 'users' table
CREATE TABLE users (
   username VARCHAR(255) PRIMARY KEY,
   password VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   surname VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL
);

-- Create 'temporary_reservation' table
CREATE TABLE temporary_reservation (
   id BIGSERIAL PRIMARY KEY,
   user_username VARCHAR(255) NOT NULL,
   accommodation_id BIGINT NOT NULL,
   host_id BIGINT NOT NULL,
   confirmed BOOLEAN NOT NULL,
   FOREIGN KEY (user_username) REFERENCES users(username),
   FOREIGN KEY (accommodation_id) REFERENCES accommodation(id),
   FOREIGN KEY (host_id) REFERENCES host(id)
);

