package hibernate.lesson4.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GeneralDAO {

    private static SessionFactory sessionFactory;

    public static <E> E save(E e) throws HibernateException {
        try (Session session = createSession().openSession()) {

            session.getTransaction().begin();
            session.save(e);
            session.getTransaction().commit();

        } catch (HibernateException i) {
            System.err.println("Save is failed");
            System.err.println(i.getMessage());
            throw i;
        }
        return e;
    }

    public static <E> E update(E e) throws HibernateException{
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.update(e);
            session.getTransaction().commit();

        } catch (HibernateException i) {
            System.err.println("Update is failed");
            System.err.println(i.getMessage());
            throw i;
        }
        return e;
    }

    public static <E> E delete(E e) throws HibernateException {
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.delete(e);
            session.getTransaction().commit();

        } catch (HibernateException i) {
            System.err.println("Delete is failed");
            System.err.println(i.getMessage());
            throw i;
        }
        return e;
    }

    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
