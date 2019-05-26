package hibernate.lesson4.Repository;

import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class HotelRepository {

    private static SessionFactory sessionFactory;

    private String sql1 = "FROM Hotel WHERE name = :name";
    private String sql2 = "FROM Hotel WHERE city = :city";

    public Collection findHotelByName(String name) {

        Transaction tr;
        List<Hotel> hotelsByName = null;

        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery(sql1);
            query.setParameter("name", name);
            hotelsByName = session.createQuery(sql1).list();

        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());
        }
        return hotelsByName;
    }

    public Collection findHotelByCity(String city) {

        Transaction tr;
        List<Hotel> hotelsByCity = null;

        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery(sql2);
            query.setParameter("city", city);
            hotelsByCity = session.createQuery(sql2).list();

        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());
        }
        return hotelsByCity;
    }

    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
