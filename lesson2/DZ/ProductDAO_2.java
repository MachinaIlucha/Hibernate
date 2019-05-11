package hibernate.lesson2.DZ;

import hibernate.lesson2.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO_2 {

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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE ID = ?");
            sqlQuery.addEntity(Product.class);
            sqlQuery.setParameter(1, id);
            product = (Product) sqlQuery.getSingleResult();


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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE name = ?");
            sqlQuery.addEntity(Product.class);
            sqlQuery.setParameter(1, name);
            product = (Product) sqlQuery.getSingleResult();


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
            Query query = session.createSQLQuery("SELECT * FROM PRODUCT WHERE NAME like :name").addEntity(Product.class);
            list = query.setString("name", name).list();

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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE price between ? and ?").addEntity(Product.class);
            sqlQuery.setInteger(1, price - delta);
            sqlQuery.setInteger(2, price + delta);
            list = sqlQuery.list();

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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE NAME LIKE :name ORDER BY price ASC ").addEntity(Product.class);
            sqlQuery.setString("name", name);
            list = sqlQuery.list();

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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE NAME LIKE :name ORDER BY price DESC").addEntity(Product.class);
            sqlQuery.setString("name", name);
            list = sqlQuery.list();


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
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE price between ? and ? ORDER BY price DESC").addEntity(Product.class);
            sqlQuery.setInteger(1, price - delta);
            sqlQuery.setInteger(2, price + delta);
            list = sqlQuery.list();

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
