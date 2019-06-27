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
        return GeneralDAO.save(room);
    }

    public static Room update(Room room) {
        return GeneralDAO.update(room);
    }

    public static Room delete(long roomId) {
        return GeneralDAO.delete(findById(roomId));
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
