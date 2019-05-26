package hibernate.lesson4.Repository;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.DAO.OrderDAO;
import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class OrderRepository {

    private static SessionFactory sessionFactory;

    private String sql1 = "FROM Order WHERE userOrdered = :user and room = :room";

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws BadRequestExeption {
        User user = UserDAO.findById(userId);
        Room room = RoomDAO.findById(roomId);

        //action
        Order order = null;
        if (new Date().compareTo(dateTo) == 0 || new Date().compareTo(dateTo) == 1) {
            /*save order if it's okay*/
            order.setRoom(room);
            order.setUserOrdered(user);
            order.setDateFrom(dateFrom);
            order.setDateTo(dateTo);
            order.setMoneyPaid(room.getPrice());
            OrderDAO.save(order);
            /*----------------------------------*/
        } else throw new BadRequestExeption("Sorry but this room with id " + roomId + " already booked on this Date");
    }

    public void cancelReservation(long roomId, long userId){
        User user = UserDAO.findById(userId);
        Room room = RoomDAO.findById(roomId);

        Transaction tr = null;

        try (Session session = createSession().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery(sql1);
            query.setParameter("user", user);
            query.setParameter("room", room);
            OrderDAO.delete((Order) query.getSingleResult());

        } catch (HibernateException e) {
            System.err.println("Book method is failed");
            System.err.println(e.getMessage());
            e.printStackTrace();

            if (tr != null)
                tr.rollback();
        }
    }


    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
