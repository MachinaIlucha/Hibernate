package hibernate.lesson4.DAO;

import hibernate.lesson4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserDAO {

    private static SessionFactory sessionFactory;

    public static void save(User user) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static User update(User user) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static User delete(User user) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static User findById(Long id) {
        //create session/tr
        Transaction tr = null;
        User user = null;
        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM User WHERE id = :id");
            query.setParameter("id", id);
            user = (User) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return user;
    }


    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
