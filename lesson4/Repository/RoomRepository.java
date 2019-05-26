package hibernate.lesson4.Repository;

import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class RoomRepository {

    private String sql1 = "FROM Room WHERE numberOfGuests = :numberOfGuests and price = :price and breakfastIncluded = :breakfastIncluded and petsAllowed = :petsAllowed and dateAvailableFrom = :dateAvailableFrom";

    private static SessionFactory sessionFactory;

    public Collection findRooms(Filter filter) {

        Transaction tr = null;
        List<Room> rooms = null;

        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery(sql1);
            query.setParameter("numberOfGuests", filter.getNumberOfGuests());
            query.setParameter("price", filter.getPrice());
            query.setParameter("breakfastIncluded", filter.isBreakfastIncluded());
            query.setParameter("petsAllowed", filter.isPetsAllowed());
            query.setParameter("dateAvailableFrom", filter.getDateAvailableFrom());
            rooms = session.createQuery(sql1).list();

        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return rooms;
    }

    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
