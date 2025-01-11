-- Добавление данных в таблицу school_subjects
INSERT INTO hc_school_tests_sc.school_subjects (name)
VALUES ('Mathematics'),
       ('Physics'),
       ('Chemistry'),
       ('Biology'),
       ('History'),
       ('Geography'),
       ('Literature'),
       ('English'),
       ('Computer Science'),
       ('Physical Education');

-- Добавление данных в таблицу questions
INSERT INTO hc_school_tests_sc.questions (teacher_id, school_subject, description, answer, type, difficulty,
                                          test_points, rank_points)
VALUES (1, 'Mathematics', 'What is 2+2?', '4', 'single_choice', 1, 1, 10),
       (2, 'Physics', 'What is the speed of light?', '299792458 m/s', 'text', 5, 3, 50),
       (3, 'Chemistry', 'What is the chemical symbol for water?', 'H2O', 'text', 1, 1, 10),
       (4, 'Biology', 'What is the powerhouse of the cell?', 'Mitochondria', 'text', 2, 2, 20),
       (5, 'History', 'Who discovered America?', 'Christopher Columbus', 'single_choice', 3, 2, 30),
       (6, 'Geography', 'What is the capital of France?', 'Paris', 'single_choice', 1, 1, 10),
       (7, 'Literature', 'Who wrote "Hamlet"?', 'William Shakespeare', 'text', 2, 2, 20),
       (8, 'English', 'What is the synonym of "fast"?', 'quick', 'multiple_choice', 1, 1, 10),
       (9, 'Computer Science', 'What does CPU stand for?', 'Central Processing Unit', 'text', 2, 2, 20),
       (10, 'Physical Education', 'How many players are on a soccer team?', '11', 'single_choice', 1, 1, 10);

-- Добавление данных в таблицу school_tests
INSERT INTO hc_school_tests_sc.school_tests (title, teacher_id, type, school_subject_id, complexity, class_level,
                                             description, duration)
VALUES ('Basic Math Test', 1, 'practice', 1, 'easy', 5, 'Basic math questions for beginners.', 30),
       ('Physics Advanced Test', 2, 'exam', 2, 'hard', 10, 'Advanced physics problems.', 60),
       ('Chemistry Basics', 3, 'quiz', 3, 'medium', 7, 'Basic concepts of chemistry.', 45),
       ('Biology Quiz', 4, 'quiz', 4, 'easy', 6, 'Biology quiz for beginners.', 30),
       ('History Test', 5, 'exam', 5, 'medium', 8, 'Test on historical events.', 50),
       ('Geography Challenge', 6, 'challenge', 6, 'easy', 7, 'Questions about world geography.', 40),
       ('Literature Exam', 7, 'exam', 7, 'hard', 10, 'Exam on classic literature.', 60),
       ('English Vocabulary Test', 8, 'quiz', 8, 'easy', 5, 'Test to improve vocabulary.', 30),
       ('Computer Basics', 9, 'practice', 9, 'medium', 7, 'Test on basic computer concepts.', 45),
       ('Sports Rules Test', 10, 'practice', 10, 'easy', 6, 'Test on basic sports rules.', 20);

-- Добавление данных в таблицу results
INSERT INTO hc_school_tests_sc.results (test_id, student_id, attempt, score, rank_score, started_at, finished_at)
VALUES (1, 101, 1, 10, 10, '2025-01-01 10:00:00', '2025-01-01 10:30:00'),
       (2, 102, 1, 45, 50, '2025-01-02 11:00:00', '2025-01-02 12:00:00'),
       (3, 103, 2, 20, 20, '2025-01-03 09:00:00', '2025-01-03 09:45:00'),
       (4, 104, 1, 30, 30, '2025-01-04 10:00:00', '2025-01-04 10:30:00'),
       (5, 105, 1, 25, 30, '2025-01-05 08:00:00', '2025-01-05 08:50:00'),
       (6, 106, 1, 35, 40, '2025-01-06 14:00:00', '2025-01-06 14:40:00'),
       (7, 107, 1, 50, 60, '2025-01-07 15:00:00', '2025-01-07 16:00:00'),
       (8, 108, 2, 15, 10, '2025-01-08 12:00:00', '2025-01-08 12:30:00'),
       (9, 109, 1, 40, 45, '2025-01-09 09:00:00', '2025-01-09 09:45:00'),
       (10, 110, 1, 20, 20, '2025-01-10 11:00:00', '2025-01-10 11:20:00');
