
import models.hbn.Student;
import org.hibernate.Session;
import persistance.HibernateUtil;

/**
 * Created by eku on 08.05.17.
 */


public class App
{
    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Student student = new Student();

        student.setName("st1");
        student.setAge(123);

        session.save(student);
        session.getTransaction().commit();
    }
}