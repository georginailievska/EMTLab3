-- MATERIALIZED VIEW: accommodations_by_host
CREATE MATERIALIZED VIEW accommodations_by_host AS
SELECT
    h.id AS host_id,
    CONCAT(h.name, ' ', h.surname) AS name,
    COUNT(a.id) AS accommodation_count
FROM host h
         LEFT JOIN accommodation a ON h.id = a.host_id
GROUP BY h.id, h.name, h.surname;

-- MATERIALIZED VIEW: hosts_by_country
CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT c.name AS country, COUNT(*) AS host_count
FROM host h
         JOIN country c ON h.country_id = c.id
GROUP BY c.name;