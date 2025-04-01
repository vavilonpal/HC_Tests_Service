INSERT INTO hc_school_tests_sc.school_subjects (name) VALUES
                                                          ('Математика'),
                                                          ('Физика'),
                                                          ('Химия'),
                                                          ('Биология');

INSERT INTO hc_school_tests_sc.school_tests (title, teacher_id, type, school_subj_id, complexity, class_level, description, duration)
VALUES
    ('Алгебра. Линейные уравнения', 1, 'PRACTICE', 1, 'EASY', 8, 'Тест на знание линейных уравнений', 45),
    ('Кинематика. Законы движения', 2, 'PRACTICE', 2, 'EASY', 10, 'Тест по кинематике', 60),
    ('Основы органической химии', 3, 'PRACTICE', 3, 'EASY', 11, 'Тест на знание химических соединений', 90),
    ('Эволюция и генетика', 4, 'PRACTICE', 4, 'EASY', 9, 'Проверка знаний по биологии', 30);

INSERT INTO hc_school_tests_sc.questions (teacher_id, answer, test_id, school_subj_id, description, check_type, type, difficulty, test_points, rank_points)
VALUES
    (1, '{"correct": ["x = 5"], "incorrect": ["x = 3", "x = 7", "x = 0"]}', 1, 1, 'Решите уравнение: 2x + 3 = 13', true, 'single_choice', 3, 2, 10),
    (2, '{"correct": ["9.8"], "incorrect": ["10", "9.5", "8.9"]}', 2, 2, 'Ускорение свободного падения на Земле', true, 'single_choice', 2, 1, 5),
    (3, '{"correct": ["C6H12O6"], "incorrect": ["C12H22O11", "H2O", "CO2"]}', 3, 3, 'Молекулярная формула глюкозы', true, 'text', 4, 3, 15),
    (4, '{"correct": ["Дарвин"], "incorrect": ["Ламарк", "Линней", "Мендель"]}', 4, 4, 'Кто является основателем теории эволюции?', true, 'single_choice', 2, 1, 5);


INSERT INTO hc_school_tests_sc.results (test_id, student_id, score, rank_score, started_at, finished_at)
VALUES
    (1, 101, 85, 50, '2025-03-27 10:00:00', '2025-03-27 10:45:00'),
    (2, 102, 70, 40, '2025-03-26 09:30:00', '2025-03-26 10:30:00'),
    (3, 103, 90, 60, '2025-03-25 14:00:00', '2025-03-25 15:30:00'),
    (4, 104, 65, 35, '2025-03-24 11:00:00', '2025-03-24 11:30:00');

INSERT INTO hc_school_tests_sc.answers (student_id, result_id, question_id, student_answer, score_points, rank_points)
VALUES
    (101, 1, 1, '{"answer": ["x = 5"]}', 2, 10),
    (102, 2, 2, '{"answer": ["9.8"]}', 1, 5),
    (103, 3, 3, '{"answer": ["C6H12O6"]}', 3, 15),
    (104, 4, 4, '{"answer": ["Дарвин"]}', 1, 5);

