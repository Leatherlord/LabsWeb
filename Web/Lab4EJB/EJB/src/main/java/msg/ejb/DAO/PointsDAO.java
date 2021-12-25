package msg.ejb.DAO;

import msg.ejb.Factory.HibernateSessionFactoryUtil;
import msg.ejb.PointsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PointsDAO {

    public PointsEntity findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        PointsEntity pointsEntity = session.get(PointsEntity.class, id);
        session.close();
        return pointsEntity;
    }

    public void save(PointsEntity pointsEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(pointsEntity);
        tx1.commit();
        session.close();
    }

    public void update(PointsEntity pointsEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(pointsEntity);
        tx1.commit();
        session.close();
    }

    public void delete(PointsEntity pointsEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(pointsEntity);
        tx1.commit();
        session.close();
    }

    public List<PointsEntity> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<PointsEntity> points = (List<PointsEntity>) session.createQuery("From PointsEntity").list();
        session.close();
        return points;
    }
}
