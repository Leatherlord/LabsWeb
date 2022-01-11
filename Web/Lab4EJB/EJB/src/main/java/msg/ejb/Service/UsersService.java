package msg.ejb.Service;

import msg.ejb.DAO.UsersDAO;
import msg.ejb.UsersEntity;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
public class UsersService {

    @EJB
    private UsersDAO usersDao;

    public void saveUser(UsersEntity user) throws Exception {
            usersDao.save(user);
    }

    public void deleteUser(UsersEntity user) throws Exception {
            usersDao.delete(user);
    }

    public UsersEntity findByLogin(String login) throws IndexOutOfBoundsException {
        return usersDao.findByLogin(login);
    }

    public ArrayList<UsersEntity> findAllUsers() {
        return (ArrayList<UsersEntity>) usersDao.findAll();
    }

}
