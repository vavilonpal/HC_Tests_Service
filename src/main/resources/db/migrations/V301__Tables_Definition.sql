CREATE TABLE if not exists questions
(
    id             SERIAL PRIMARY KEY,                          -- Уникальный идентификатор вопроса
    teacher_id     BIGINT       NOT NULL,                       -- ID учителя (внешний ключ, если нужно)
    school_subject VARCHAR(100) NOT NULL,                       -- Предмет
    description    TEXT         NOT NULL,                       -- Формулировка вопроса
    answer         TEXT         NOT NULL,                       -- Правильный ответ
    type           VARCHAR(50)  NOT NULL,                       -- Тип вопроса (например, "single_choice", "multiple_choice", "text")
    options        JSONB,                                       -- Варианты ответа в формате JSON
    difficulty     SMALLINT CHECK (difficulty BETWEEN 1 AND 5), -- Сложность (1-легкий, 5-сложный)
    test_points SMALLINT DEFAULT 1 CHECK (questions.test_points > 0), -- Баллы за правильный ответ
    rank_points int,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP          -- Дата создания
);

CREATE TABLE if not exists schoolTests
(
    id             SERIAL PRIMARY KEY,                          -- Уникальный идентификатор теста
    title          VARCHAR(255) NOT NULL,                       -- Название теста
    teacher_id     BIGINT       NOT NULL,
    type           VARCHAR(50) NOT NULL,                       -- тип теста
    school_subject_id bigint references  school_subjects(id),                       -- Предмет
    complexity     VARCHAR(50), -- Средняя сложность теста
    class_level    SMALLINT     NOT NULL,                       -- Класс/уровень
    description    TEXT,                                        -- Описание теста
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- Дата создания
    duration       SMALLINT                                     -- Время прохождения в минутах
);

CREATE TABLE if not exists test_question
(
    id          SERIAL PRIMARY KEY,                                            -- Уникальный идентификатор связи
    test_id     BIGINT   NOT NULL REFERENCES schoolTests (id) ON DELETE CASCADE,     -- Внешний ключ на тесты
    question_id BIGINT   NOT NULL REFERENCES questions (id) ON DELETE CASCADE, -- Внешний ключ на вопросы
    "order"     SMALLINT NOT NULL                                             -- Порядок вопроса в тесте                                                           --Очки дял рейтинговой системы
);

CREATE TABLE if not exists results
(
    id          SERIAL PRIMARY KEY,                                        -- Уникальный идентификатор результата
    test_id     BIGINT   NOT NULL REFERENCES schoolTests (id) ON DELETE CASCADE, -- Внешний ключ на тесты
    student_id  BIGINT   NOT NULL,                                         -- ID ученика (внешний ключ, если нужно)
    attempt     smallint default 1,                                        -- количетсво попыток
    score       SMALLINT NOT NULL,                                         -- Общий набранный балл
    rank_score  SMALLINT NOT NULL,                                         -- Набранный рейтинговый балл
    started_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                       -- Время начала теста
    finished_at TIMESTAMP                                                  -- Время завершения теста
);
CREATE TABLE school_subjects (
                                 id serial PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL
);

