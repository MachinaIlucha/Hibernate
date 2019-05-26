package hibernate.lesson4.Repository;

import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserRepository {

    private static SessionFactory sessionFactory;

    private String sql1 = "FROM User WHERE userName = :name and password = :password";

    public User registerUser(User user) {
        //save user to db(file)
        UserDAO.save(user);
        return user;
    }

    public void login(String userName, String password) {

        Transaction tr = null;

        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery(sql1);
            query.setParameter("name", userName);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            hibernate.lesson4.model.Session.setUser(user);

        } catch (HibernateException e) {
            System.err.println("Book method is failed");
            System.err.println(e.getMessage());
            e.printStackTrace();

            if (tr != null)
                tr.rollback();
        }
        System.out.println("You are logged in");
    }

    public void logout() {
        hibernate.lesson4.model.Session.setUser(null);
    }

    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
