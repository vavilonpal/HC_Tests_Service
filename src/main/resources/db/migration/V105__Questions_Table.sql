CREATE TABLE IF NOT EXISTS hc_school_tests_sc.questions
(
    id             SERIAL PRIMARY KEY,
    teacher_id     BIGINT      NOT NULL REFERENCES hc_school_tests_sc.users(id),
    test_id        BIGINT      NOT NULL REFERENCES  hc_school_tests_sc.school_tests(id),
    school_subj_id BIGINT      NOT NULL REFERENCES hc_school_tests_sc.school_subjects (id),
    answer         JSONB,
    description    TEXT        NOT NULL,
    check_type     BOOLEAN     NOT NULL,
    type           VARCHAR(50) NOT NULL,                                                     -- Тип вопроса (например, "single_choice", "multiple_choice", "text")
    difficulty     SMALLINT CHECK (difficulty BETWEEN 1 AND 5),                              -- Сложность (1-легкий, 5-сложный)
    test_points    SMALLINT  DEFAULT 1 CHECK (hc_school_tests_sc.questions.test_points > 0), -- Баллы за правильный ответ
    rank_points    INT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                                      -- Дата создания
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
