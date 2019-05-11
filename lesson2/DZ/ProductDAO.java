package hibernate.lesson2.DZ;

import hibernate.lesson2.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static Product findById(Long id) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        Product product = null;
        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE id = :id");
            query.setParameter("id", id);
            product = (Product) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return product;
    }

    public static Product findByName(String name) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        Product product = null;
        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE name = :name");
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();


            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return product;
    }

    public static List<Product> findByContainedName(String name) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        List<Product> list = null;

        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE name LIKE :name");
            query.setParameter("name", "%" + name + "%");
            list = query.list();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public static List<Product> findByPrice(int price, int delta) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        List<Product> list = null;

        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE price between :minprice and :maxprice");
            query.setParameter("minprice", price - delta);
            query.setParameter("maxprice", price + delta);
            list = query.list();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public static List<Product> findByNameSortedAsc(String name) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        List<Product> list = null;

        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE name LIKE :name ORDER BY name ASC");
            query.setParameter("name", "%" + name + "%");
            list = query.list();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public static List<Product> findByNameSortedDesc(String name) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        List<Product> list = null;

        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE name LIKE :name ORDER BY name DESC");
            query.setParameter("name", "%" + name + "%");
            list = query.list();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public static List<Product> findByPriceSortedDesc(int price, int delta) {
        //create session/tr
        Session session = null;
        Transaction tr = null;
        List<Product> list = null;

        try {
            session = createSession().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            Query query = session.createQuery("FROM Product WHERE price between :minprice and :maxprice ORDER BY price DESC");
            query.setParameter("minprice", price - delta);
            query.setParameter("maxprice", price + delta);
            list = query.list();

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Find method is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public static SessionFactory createSession() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}