CREATE TABLE IF NOT EXISTS hc_school_tests_sc.school_tests
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    teacher_id     BIGINT       NOT NULL,
    type           VARCHAR(50)  NOT NULL,
    school_subj_id BIGINT       NOT NULL REFERENCES hc_school_tests_sc.school_subjects (id), -- Предмет
    complexity     VARCHAR(50),
    class_level    INT,
    description    TEXT,
    duration       SMALLINT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
ALTER TABLE hc_school_tests_sc.school_tests ADD CONSTRAINT fk_tests_teacher FOREIGN KEY (teacher_id) REFERENCES hc_school_tests_sc.users(id);