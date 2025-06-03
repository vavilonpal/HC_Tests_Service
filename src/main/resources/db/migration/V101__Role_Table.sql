CREATE SCHEMA IF NOT EXISTS hc_school_tests_sc;

CREATE TABLE IF NOT EXISTS hc_school_tests_sc.roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
)