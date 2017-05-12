
import models.hbn.GroupsEntity;
import models.hbn.StudentsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by eku on 08.05.17.
 */


public class App
{
    private static SessionFactory factory;
    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf-pu");

    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = factory.openSession();

        session.beginTransaction();
        StudentsEntity student = new StudentsEntity();

        GroupsEntity group = session.find(GroupsEntity.class, 1);

        student.setName("st1");
        student.setAge(123);
        student.setGroup(group);

        session.save(student);
        session.getTransaction().commit();

        System.out.println(emf.createEntityManager().createQuery("from students").getResultList());
    }
}