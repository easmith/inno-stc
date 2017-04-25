package main;


import model.dao.JournalDao;
import model.dao.LessonDao;
import model.entity.Group;
import model.entity.Journal;
import model.entity.Lesson;
import model.entity.Student;
import model.dao.GroupDao;
import model.dao.StudentDao;
import model.impl.GroupDaoImpl;
import model.impl.JournalDaoImpl;
import model.impl.LessonDaoImpl;
import model.impl.StudentDaoImpl;
import services.DataSourceFactory;

import javax.sql.DataSource;

public class Main {

    private static StudentDao studentDao;
    private static GroupDao groupDao;
    private static LessonDao lessonDao;
    private static JournalDao journalDao;

    public static void main(String[] args) {

        DataSource dataSource = DataSourceFactory.getMyPGDataSource();
        groupDao = new GroupDaoImpl(dataSource);
        studentDao = new StudentDaoImpl(dataSource);
        lessonDao = new LessonDaoImpl(dataSource);
        journalDao = new JournalDaoImpl(dataSource);

       for (Group group : groupDao.findAll()) {
           System.out.println(group);
           System.out.println();
        }

        for (Student student : studentDao.findAll()) {
            System.out.println(student);
            if (student.getGroup() != null) {
                System.out.println(student.getGroup());
            }
            System.out.println();
        }

        for (Lesson lesson : lessonDao.findAll()) {
            System.out.println(lesson);
            if (lesson.getGroup() != null) {
                System.out.println(lesson.getGroup());
            }
            System.out.println();
        }

        for (Journal journal : journalDao.findAll()) {
            System.out.println(journal);
            if (journal.getLesson() != null)
                System.out.println(journal.getLesson());

            if (journal.getStudent() != null)
                System.out.println(journal.getStudent());
            System.out.println();
        }
    }
}
