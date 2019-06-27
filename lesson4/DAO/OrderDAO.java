package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OrderDAO {

    private static SessionFactory sessionFactory;

    public static Order save(Order order) {
        return GeneralDAO.save(order);
    }

    public static Order update(Order order) {
        return GeneralDAO.update(order);
    }

    public static Order delete(Order order) {
        return GeneralDAO.delete(order);
    }

    public static Order findById(Long id) {
        //create session/tr
        Transaction tr = null;
        Order order = null;
        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Order WHERE id = :id");
            query.setParameter("id", id);
            order = (Order) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return order;
    }


    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
