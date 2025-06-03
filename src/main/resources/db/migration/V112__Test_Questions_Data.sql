INSERT INTO hc_school_tests_sc.questions (teacher_id, answer, test_id, school_subj_id, description, check_type, type,
                                          difficulty, test_points, rank_points)
VALUES (1, '{
  "correct": [
    "x = 5"
  ],
  "incorrect": [
    "x = 3",
    "x = 7",
    "x = 0"
  ]
}', 1, 1, 'Решите уравнение: 2x + 3 = 13', true, 'single_choice', 3, 2, 10),
       (2, '{
         "correct": [
           "9.8"
         ],
         "incorrect": [
           "10",
           "9.5",
           "8.9"
         ]
       }', 2, 2, 'Ускорение свободного падения на Земле', true, 'single_choice', 2, 1, 5),
       (3, '{
         "correct": [
           "C6H12O6"
         ],
         "incorrect": [
           "C12H22O11",
           "H2O",
           "CO2"
         ]
       }', 3, 3, 'Молекулярная формула глюкозы', true, 'text', 4, 3, 15),
       (3, '{
         "correct": [
           "Дарвин"
         ],
         "incorrect": [
           "Ламарк",
           "Линней",
           "Мендель"
         ]
       }', 3, 4, 'Кто является основателем теории эволюции?', true, 'single_choice', 2, 1, 5);
