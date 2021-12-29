package msg.ejb.DAO;

import msg.ejb.PointsEntity;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;

@Singleton
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class PointsDAO {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    public void save(PointsEntity pointsEntity) throws Exception {
        userTransaction.begin();
        em.persist(pointsEntity);
        userTransaction.commit();
    }

    public void delete(PointsEntity pointsEntity) throws Exception {
        userTransaction.begin();
        em.remove(em.merge(pointsEntity));
        userTransaction.commit();
    }

    public List<PointsEntity> findAll() {
        Query query = em.createQuery("SELECT p from PointsEntity p");
        return query.getResultList();
    }
}
