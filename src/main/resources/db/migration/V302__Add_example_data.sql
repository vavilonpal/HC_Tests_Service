INSERT INTO hc_school_tests_sc.school_subjects (name) VALUES
                                                          ('Mathematics'),
                                                          ('Physics'),
                                                          ('Chemistry'),
                                                          ('Biology'),
                                                          ('History'),
                                                          ('Geography'),
                                                          ('English'),
                                                          ('Computer Science'),
                                                          ('Art'),
                                                          ('Music');

INSERT INTO hc_school_tests_sc.school_tests (title, teacher_id, type, school_subj_id, complexity, class_level, description, duration)
VALUES
    ('Math Basics', 1, 'PRACTICE', 1, 'EASY', 5, 'Basic math questions', 30),
    ('Physics Intro', 2, 'EXAM', 2, 'MEDIUM', 7, 'Fundamentals of physics', 45),
    ('Chemistry 101', 3, 'PRACTICE', 3, 'EASY', 6, 'Introduction to chemistry', 30),
    ('Biology Cells', 4, 'EXAM', 4, 'HARD', 9, 'Deep dive into cell biology', 60),
    ('World History', 5, 'PRACTICE', 5, 'MEDIUM', 8, 'Major historical events', 40),
    ('Geography Facts', 6, 'TRAINING', 6, 'EASY', 5, 'World geography quiz', 30),
    ('Grammar Test', 7, 'PRACTICE', 7, 'MEDIUM', 7, 'English grammar rules', 40),
    ('CS Basics', 8, 'TRAINING', 8, 'HARD', 10, 'Fundamentals of programming', 60),
    ('Art Appreciation', 9, 'PRACTICE', 9, 'EASY', 6, 'Understanding art styles', 30),
    ('Music Theory', 10, 'TRAINING', 10, 'MEDIUM', 8, 'Basics of musical notation', 45);


INSERT INTO hc_school_tests_sc.questions (teacher_id, answer_id,test_id, school_subj_id, description, check_type, type, difficulty, test_points, rank_points)
VALUES
    (1, 1,1, 1, 'What is 2+2?', true, 'single_choice', 1, 1, 10),
    (2, 2,1, 2, 'What is Newton’s First Law?', true, 'text', 3, 2, 20),
    (3, 3,2, 3, 'What is the chemical formula of water?', true, 'single_choice', 1, 1, 10),
    (4, 4,2, 4, 'What is the main function of the liver?', false, 'multiple_choice', 2, 2, 15),
    (5, 5,3, 5, 'Who was the first President of the USA?', true, 'single_choice', 2, 2, 15),
    (6, 6,3, 6, 'What is the capital of France?', true, 'single_choice', 1, 1, 10),
    (7, 7,4, 7, 'Define past perfect tense.', true, 'text', 3, 3, 20),
    (8, 8,4, 8, 'What does HTML stand for?', true, 'text', 2, 2, 15),
    (9, 9,5, 9, 'Who painted the Mona Lisa?', true, 'single_choice', 2, 2, 15),
    (10, 10,6, 10, 'What is the key signature of C Major?', true, 'single_choice', 3, 3, 20);

INSERT INTO hc_school_tests_sc.results (test_id, student_id, score, rank_score)
VALUES
    (1, 101, 80, 10),
    (2, 102, 75, 9),
    (3, 103, 90, 12),
    (4, 104, 60, 7),
    (5, 105, 85, 11),
    (6, 106, 70, 8),
    (7, 107, 95, 13),
    (8, 108, 50, 6),
    (9, 109, 88, 10),
    (10, 110, 65, 7);

INSERT INTO hc_school_tests_sc.answers (question_id, student_answer, score_points)
VALUES
    (1, '4', 1),
    (2, 'An object remains at rest...', 2),
    (3, 'H2O', 1),
    (4, 'Detoxifies blood', 2),
    (5, 'George Washington', 2),
    (6, 'Paris', 1),
    (7, 'Had eaten', 3),
    (8, 'HyperText Markup Language', 2),
    (9, 'Leonardo da Vinci', 2),
    (10, 'No sharps or flats', 3);
