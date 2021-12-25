package msg.ejb.DAO;

import msg.ejb.Factory.HibernateSessionFactoryUtil;
import msg.ejb.UsersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO {

    public UsersEntity findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersEntity user = session.get(UsersEntity.class, id);
        session.close();
        return user;
    }

    public void save(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(UsersEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<UsersEntity> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UsersEntity> users = (List<UsersEntity>) session.createQuery("From UsersEntity").list();
        session.close();
        return users;
    }
}