CREATE TABLE hc_school_tests_sc.solved_tests (
                              id SERIAL PRIMARY KEY,
                              student_id INT NOT NULL,
                              test_id INT NOT NULL,
                              answers JSONB NOT NULL,
                              solve_time INTERVAL default '0 seconds',
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
