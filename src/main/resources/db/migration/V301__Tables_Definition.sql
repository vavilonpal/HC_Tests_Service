CREATE SCHEMA IF NOT EXISTS hc_school_tests_sc;

CREATE TABLE hc_school_tests_sc.school_subjects (
                                 id serial PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL unique 
);

CREATE TABLE if not exists hc_school_tests_sc.questions
(
    id             SERIAL PRIMARY KEY,
    school_subj_id bigint NOT NULL references hc_school_tests_sc.school_subjects(id),
    teacher_id     bigint not null,
    description    TEXT         NOT NULL,
    answer_type    varchar(55)  NOT NULL,
    answer         TEXT         ,
    type           VARCHAR(50)  NOT NULL,                       -- Тип вопроса (например, "single_choice", "multiple_choice", "text")
    difficulty     SMALLINT CHECK (difficulty BETWEEN 1 AND 5), -- Сложность (1-легкий, 5-сложный)
    test_points SMALLINT DEFAULT 1 CHECK (hc_school_tests_sc.questions.test_points > 0), -- Баллы за правильный ответ
    rank_points int,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP          -- Дата создания
);

CREATE TABLE if not exists hc_school_tests_sc.school_tests
(
    id             SERIAL PRIMARY KEY,                          -- Уникальный идентификатор теста
    title          VARCHAR(255) NOT NULL,                       -- Название теста
    teacher_id     BIGINT       NOT NULL,
    type           VARCHAR(50) NOT NULL,                       -- тип теста
    school_subj_id bigint NOT NULL references hc_school_tests_sc.school_subjects(id),                       -- Предмет
    complexity     VARCHAR(50), -- Средняя сложность теста
    class_level    INT,                       -- Класс/уровень
    description    TEXT,                                        -- Описание теста
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- Дата создания
    duration       SMALLINT                                     -- Время прохождения в минутах
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
    result_id bigint references hc_school_tests_sc.results(id),
    question_id bigint not null  references hc_school_tests_sc.questions(id),
    student_answer text,
    score_points int
)


