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

    public static User save(User user) {
       return GeneralDAO.save(user);
    }

    public static User update(User user) {
       return GeneralDAO.update(user);
    }

    public static User delete(User user) {
        return GeneralDAO.delete(user);
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
