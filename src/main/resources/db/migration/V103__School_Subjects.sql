CREATE TABLE IF NOT EXISTS hc_school_tests_sc .school_subjects
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);