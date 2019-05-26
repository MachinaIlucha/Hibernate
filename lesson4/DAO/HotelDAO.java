package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HotelDAO {

    private static SessionFactory sessionFactory;

    public static Hotel save(Hotel hotel) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            session.getTransaction().begin();
            session.save(hotel);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return hotel;
    }

    public static Hotel update(Hotel hotel) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.update(hotel);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
        }
        return hotel;
    }

    public static Hotel delete(long hotelId) {
        Hotel hotel = findById(hotelId);

        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.delete(hotel);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
        }
        return hotel;
    }

    public static Hotel findById(Long id) {
        //create session/tr
        Transaction tr = null;
        Hotel hotel = null;
        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Hotel WHERE id = :id");
            query.setParameter("id", id);
            hotel = (Hotel) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return hotel;
    }


    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
