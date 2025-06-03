CREATE TABLE IF NOT EXISTS hc_school_tests_sc.answers
(
    id             SERIAL PRIMARY KEY,
    student_id     BIGINT NOT NULL  REFERENCES hc_school_tests_sc.users(id) ON DELETE CASCADE,
    result_id      BIGINT REFERENCES hc_school_tests_sc.results (id),
    question_id    BIGINT NOT NULL REFERENCES hc_school_tests_sc.questions (id),
    student_answer TEXT,
    score_points   INT,
    rank_points    INT,
    created_at     TIMESTAMP DEFAULT now(),
    updated_at     TIMESTAMP

);