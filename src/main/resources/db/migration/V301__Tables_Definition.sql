CREATE SCHEMA IF NOT EXISTS hc_school_tests_sc;

CREATE TABLE hc_school_tests_sc.school_subjects (
                                 id serial PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL unique 
);

CREATE TABLE if not exists hc_school_tests_sc.school_tests
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    teacher_id     BIGINT       NOT NULL,
    type           VARCHAR(50) NOT NULL,
    school_subj_id bigint NOT NULL references hc_school_tests_sc.school_subjects(id),                       -- Предмет
    complexity     VARCHAR(50),
    class_level    INT,
    description    TEXT,
    duration       SMALLINT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE if not exists hc_school_tests_sc.questions
(
    id             SERIAL PRIMARY KEY,
    teacher_id     bigint not null,
    answer_id     bigint not null ,
    test_id       bigint not null ,
    school_subj_id bigint NOT NULL references hc_school_tests_sc.school_subjects(id),
    description    TEXT         NOT NULL,
    check_type        BOOLEAN      NOT NULL,
    type           VARCHAR(50)  NOT NULL,                       -- Тип вопроса (например, "single_choice", "multiple_choice", "text")
    difficulty     SMALLINT CHECK (difficulty BETWEEN 1 AND 5), -- Сложность (1-легкий, 5-сложный)
    test_points SMALLINT DEFAULT 1 CHECK (hc_school_tests_sc.questions.test_points > 0), -- Баллы за правильный ответ
    rank_points int,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- Дата создания
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE if not exists hc_school_tests_sc.results
(
    id          SERIAL PRIMARY KEY,                                        -- Уникальный идентификатор результата
    test_id     BIGINT   NOT NULL REFERENCES hc_school_tests_sc.school_tests (id) ON DELETE CASCADE, -- Внешний ключ на тесты
    student_id  BIGINT   NOT NULL,                                         -- ID ученика (внешний ключ, если нужно)
    score       INT NOT NULL,                                         -- Общий набранный балл
    rank_score  INT,                                         -- Набранный рейтинговый балл
    started_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                       -- Время начала теста
    finished_at TIMESTAMP                                                  -- Время завершения теста
);


CREATE TABLE IF NOT EXISTS hc_school_tests_sc.answers
(
    id serial primary key,
    --result_id bigint references hc_school_tests_sc.results(id),
    question_id bigint not null  references hc_school_tests_sc.questions(id),
    student_answer text,
    score_points int
);




