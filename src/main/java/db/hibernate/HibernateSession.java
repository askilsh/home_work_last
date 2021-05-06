package db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateSession {
    private static Session session = null;
    private static SessionFactory sf;

    public static Session getSession(final Class annotated) {
        sf = buildSessionFactory(annotated);
        session = sf.openSession();
        return session;
    }

    private static SessionFactory buildSessionFactory(final Class annotated) {
        return new Configuration().
                configure().
                addAnnotatedClass(annotated).
                buildSessionFactory();
    }

    public static boolean saveObj(final Session sess, final Object obj) {
        Transaction transaction = sess.beginTransaction();
        try {
            sess.save(obj);
            transaction.commit();
            return true;
        } catch (javax.persistence.PersistenceException e) {
            return false;
        }
    }
}
