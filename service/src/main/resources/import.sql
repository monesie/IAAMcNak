
INSERT INTO USER (USERNAME, hashedpw, century, email, firstname, lastname, role) VALUES ('testuser', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'Test@test.de', 'testvorname', 'testNachname', 1);
INSERT INTO USER (USERNAME, hashedpw, century, email, firstname, lastname, role) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'admin@test.de', 'testvorname', 'testNachname', 2);
INSERT INTO USER (USERNAME, hashedpw, century, email, firstname, lastname, role) VALUES ('student', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'admin@test.de', 'testvorname', 'testNachname', 0);
INSERT INTO USER (USERNAME, hashedpw, century, email, firstname, lastname, role) VALUES ('student2', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'admin@test.de', 'testvorname', 'testNachname', 0);
INSERT INTO USER (userName, hashedpw, century, email, firstName, lastName, role) VALUES ('12924', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'marten.moraal@nordakademie.de', 'Marten', 'Moraal', 2);
INSERT INTO USER (userName, hashedpw, firstName, lastName, email, role) VALUES ('jsauer', '21232f297a57a5a743894a0e4a801fc3', 'Joachim', 'Sauer', 'joachim.sauer@nordakademie.de', 1);
INSERT INTO USER (userName, hashedpw, firstName, lastName, email, role) VALUES ('lbraubach', '21232f297a57a5a743894a0e4a801fc3', 'Lars', 'Braubach', 'lars.braubach@nordakademie.de', 1);
INSERT INTO USER (userName, hashedpw, firstName, lastName, email, role) VALUES ('mmusterdozent', '21232f297a57a5a743894a0e4a801fc3', 'Max', 'Musterdozent', 'max.musterstudent@nordakademie.de', 1);
INSERT INTO USER (userName, hashedpw, firstName, lastName, email, role) VALUES ('teacher', '21232f297a57a5a743894a0e4a801fc3', 'Max', 'Musterdozent', 'max.musterstudent@nordakademie.de', 1);
INSERT INTO USER (USERNAME, hashedpw, century, email, firstname, lastname, role) VALUES ('nico', '21232f297a57a5a743894a0e4a801fc3', 'I12b', 'admin@test.de', 'testvorname', 'testNachname', 0);
INSERT INTO USER (userName, hashedpw, firstName, lastName, email, role) VALUES ('teacher2', '21232f297a57a5a743894a0e4a801fc3', 'Max', 'Musterdozent', 'max.musterstudent@nordakademie.de', 1);

INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (1, 'Russisch', 0.5, 30, current_date(), 1, 'Stufe1', current_date(), true, 50, current_date(), 'jsauer', 0  );
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (2, 'Japanisch', 1, 31, current_date(), 1, 'Stufe2', current_date(), true, 50, current_date(), 'jsauer', 0);
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (3, 'Business-Knigge', 0.5, 30, current_date(), 1, 'Umgangsformen', current_date(), true, 50, current_date(), 'jsauer', 0 );
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (4, 'Rhetorik1', 0.5, 30, current_date(), 1, 'Sprechtechniken', current_date(), true, 50, current_date(), 'teacher', 0   );
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (5, 'BalancedScoreCard', 0.5, 30, current_date(), 1, 'Konzeption und Aufbau', current_date(), true, 50, current_date(), 'teacher', 2   );
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (6, 'Zeit- und Selbstmanagement', 0.5, 30, current_date(), 1, 'Belastung richtig bewerten', current_date(), true, 50, current_date(), 'teacher', 0  );
INSERT INTO EXAMDATA (ID, coursename, creditpoint, duration, enddate, evaluationrule, EXAMNAME, lastcourseday, minuspoints, passlimit, startdate, creator_username, status) VALUES  (7, 'Zeit- und Selbstmanagement', 0.5, 30, current_date(), 1, 'Belastung richtig bewerten', current_date(), true, 50, current_date(), 'teacher', 1  );

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (111, 7, 'Wie macht ein Hund?', 1, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (140, 'Knurr', TRUE , NULL , 111);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (141, 'Wuff', TRUE , NULL , 111);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (142, 'Miau', FALSE , NULL , 111);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (112, 7, 'Wie macht ein Hund?', 1, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (143, 'Knurr', TRUE , NULL , 112);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (144, 'Wuff', TRUE , NULL , 112);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (145, 'Miau', FALSE , NULL , 112);
INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, oneTimePassword) VALUES (100, 'student', 7, '1234');


INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, oneTimePassword) VALUES (1, 'student', 1, '1234');
INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, oneTimePassword) VALUES (3, 'student', 3, '1234');
INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, oneTimePassword) VALUES (5, 'student', 6, '1234');

INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, startDate, oneTimePassword, passed) VALUES (2, 'student', 2, current_date(), '1234', TRUE );
INSERT INTO EXAM (ID, PARTICIPANT_USERNAME, EXAMDATA_ID, startDate, oneTimePassword, passed) VALUES (4, 'student', 5, current_date(), '1234', FALSE );

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (11, 3, 'Wie macht ein Hund?', 1, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (40, 'Knurr', TRUE , NULL , 11);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (41, 'Wuff', TRUE , NULL , 11);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (42, 'Miau', FALSE , NULL , 11);

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (12, 3, 'Wie macht eine Katze', 1, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (43, 'Schnurr', TRUE , NULL , 12);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (44, 'Blubb', FALSE , NULL , 12);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (45, 'Piep', FALSE , NULL , 12);

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (13, 3, 'Wie viele Reifen hat ein Standard-Auto?', 0, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (46, '1', FALSE , NULL , 13);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (47, '2', FALSE , NULL , 13);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (48, '4', TRUE , NULL , 13);

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (14, 3, 'Die Gabel liegt auf dem Tisch immer [1].', 2, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (49, 'links;seitlich', TRUE, '1', 14);

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (15, 3, 'Die Gabel liegt auf dem Tisch immer [1] neben dem [2]', 2, 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (50, 'links;seitlich', TRUE, '1', 15);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (51, 'Teller;Essen', TRUE, '2', 15);



Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (1, 1, 'questiona', 1, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (2, 1, 'questionb', 1, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (3, 1, 'questionc', 0, 1);

Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (4, 1, 'Dies ist ein [1]', 2, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (5, 1, 'Dieser [1] hat zwei [2]', 2, 1);


Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (6, 4, 'questiona', 1, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (7, 4, 'questionb', 1, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (8, 4, 'questionc', 0, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (9, 4, 'Dies ist ein [1]', 2, 1);
Insert INTO QUESTION (ID, EXAMDATA_ID, TEXT, questionType, points) VALUES (10, 4, 'Dieser [1] hat zwei [2]', 2, 1);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (1, 'AntwortA', TRUE , NULL , 3);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (2, 'AntwortB', FALSE , NULL , 3);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (3, 'AntwortC', FALSE , NULL , 3);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (4, 'AntwortA', TRUE , NULL , 2);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (5, 'AntwortB', TRUE , NULL , 2);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (6, 'AntwortC', FALSE , NULL , 2);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (7, 'AntwortA', FALSE ,NULL , 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (8, 'AntwortB', TRUE , NULL , 1);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (9, 'AntwortC', TRUE , NULL , 1);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (10, 'Lueckentext,Cloze', TRUE, '1', 4);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (11, 'Lueckentext,Cloze', TRUE, '1', 5);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (12, 'Luecken,Spaces', TRUE, '2', 5);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (13, 'AntwortA', TRUE , NULL , 8);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (14, 'AntwortB', FALSE , NULL , 8);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (15, 'AntwortC', FALSE , NULL , 8);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (16, 'AntwortA', TRUE , NULL , 7);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (17, 'AntwortB', TRUE , NULL , 7);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (18, 'AntwortC', FALSE , NULL , 7);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (19, 'AntwortA', FALSE ,NULL , 6);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (20, 'AntwortB', TRUE , NULL , 6);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (21, 'AntwortC', TRUE , NULL , 6);

INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (22, 'Lueckentext;Cloze', TRUE, '1', 9);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (23, 'Lueckentext;Cloze', TRUE, '1', 10);
INSERT INTO ANSWER (ID, TEXT, SOLUTION, BLANK, QUESTION_ID) VALUES (24, 'Luecken;Spaces', TRUE, '2', 10);
