package msg.ejb.DAO;


import msg.ejb.UsersEntity;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class UsersDAO {

    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    public void save(UsersEntity user) throws Exception {
        userTransaction.begin();
        em.persist(user);
        userTransaction.commit();
    }

    public void delete(UsersEntity user) throws Exception {
        userTransaction.begin();
        em.remove(em.merge(user));
        userTransaction.commit();
    }

    public UsersEntity findByLogin(String login) {
        UsersEntity usersEntity;
        try {
            usersEntity = (UsersEntity) em.createQuery("select u from UsersEntity u where u.login = :login")
                    .setParameter("login", login).getResultList().get(0);
            return usersEntity;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public List<UsersEntity> findAll() {
        Query query = em.createQuery("SELECT u from UsersEntity u");
        return query.getResultList();
    }
}