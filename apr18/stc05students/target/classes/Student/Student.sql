CREATE TABLE study_group
(
  id serial NOT NULL,
  name character varying(255),
  CONSTRAINT pk_study_group_id PRIMARY KEY (id)
)

CREATE TABLE student
(
  id serial NOT NULL,
  name character varying(255),
  age integer,
  group_id integer DEFAULT 0,
  CONSTRAINT pk_student_id PRIMARY KEY (id),
  CONSTRAINT fk_student__study_group FOREIGN KEY (group_id)
      REFERENCES study_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE lesson
(
  id serial NOT NULL,
  study_group_id integer NOT NULL,
  lesson_date timestamp without time zone NOT NULL,
  room integer NOT NULL,
  description character varying(2000),
  CONSTRAINT fk_lesson PRIMARY KEY (id),
  CONSTRAINT fk_lesson_study_group FOREIGN KEY (study_group_id)
      REFERENCES study_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE journal
(
  id serial NOT NULL,
  lesson_id integer NOT NULL,
  student_id integer NOT NULL,
  CONSTRAINT pk_journal_id PRIMARY KEY (id),
  CONSTRAINT fk_journal_lesson FOREIGN KEY (lesson_id)
      REFERENCES lesson (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_journal_student FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
