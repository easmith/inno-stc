
INSERT INTO study_group(name) VALUES ('stc01');
INSERT INTO study_group(name) VALUES ('stc02');
INSERT INTO study_group(name) VALUES ('stc03');
INSERT INTO study_group(name) VALUES ('stc04');

INSERT INTO student(name, age, group_id) VALUES ('shildt','123', '1');
INSERT INTO student(name, age, group_id) VALUES ('artem','37', '2');
INSERT INTO student(name, age, group_id) VALUES ('ivan','12', '3');
INSERT INTO student(name, age, group_id) VALUES ('aslan','32', '4');

INSERT INTO lesson(study_group_id, lesson_date, room, description) VALUES (1, '2017-01-08 04:05:06', 1 , 'first');
INSERT INTO lesson(study_group_id, lesson_date, room, description) VALUES (2, '2017-01-09 04:05:06', 2 , 'first');
INSERT INTO lesson(study_group_id, lesson_date, room, description) VALUES (1, '2017-01-12 04:05:06', 3 , 'second');


INSERT INTO journal( lesson_id, student_id) VALUES (1, 1);
INSERT INTO journal( lesson_id, student_id) VALUES (1, 2);
INSERT INTO journal( lesson_id, student_id) VALUES (1, 3);
INSERT INTO journal( lesson_id, student_id) VALUES (1, 4);
INSERT INTO journal( lesson_id, student_id) VALUES (2, 1);
INSERT INTO journal( lesson_id, student_id) VALUES (2, 2);
