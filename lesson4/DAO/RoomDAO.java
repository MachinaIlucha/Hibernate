package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RoomDAO {

    private static SessionFactory sessionFactory;

    public static Room save(Room room) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            session.getTransaction().begin();
            session.save(room);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return room;
    }

    public static Room update(Room room) {
        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.update(room);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
        }
        return room;
    }

    public static Room delete(long roomId) {
        Room room = findById(roomId);
        //create session/tr
        try (Session session = createSession().openSession()) {

            //action
            session.getTransaction().begin();
            session.delete(room);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
        }
        return room;
    }

    public static Room findById(Long id) {
        //create session/tr
        Transaction tr = null;
        Room room = null;
        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Room WHERE id = :id");
            query.setParameter("id", id);
            room = (Room) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        return room;
    }


    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
