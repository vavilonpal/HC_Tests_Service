
CREATE TABLE IF NOT EXISTS hc_school_tests_sc.users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT NOT NULL ,
    full_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
ALTER TABLE hc_school_tests_sc.users ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES hc_school_tests_sc.roles(id);