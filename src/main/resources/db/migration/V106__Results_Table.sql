CREATE TABLE IF NOT EXISTS hc_school_tests_sc.results
(
    id          SERIAL PRIMARY KEY,                                                                -- Уникальный идентификатор результата
    test_id     BIGINT NOT NULL REFERENCES hc_school_tests_sc.school_tests (id) ON DELETE CASCADE, -- Внешний ключ на тесты
    student_id  BIGINT NOT NULL REFERENCES hc_school_tests_sc.users(id) ON DELETE  CASCADE,                                                                   -- ID ученика (внешний ключ, если нужно)
    score       INT    NOT NULL,                                                                   -- Общий набранный балл
    rank_score  INT,                                                                               -- Набранный рейтинговый балл
    started_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                                               -- Время начала теста
    finished_at TIMESTAMP                                                                          -- Время завершения теста
);
