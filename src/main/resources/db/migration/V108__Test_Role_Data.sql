INSERT INTO hc_school_tests_sc.roles (name) VALUES
                                          ('ADMIN'),
                                          ('TEACHER'),
                                          ('STUDENT')
ON CONFLICT (name) DO NOTHING;