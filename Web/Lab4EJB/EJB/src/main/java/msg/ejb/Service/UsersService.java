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

    public void saveUser(UsersEntity user) {
        try {
            usersDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UsersEntity user) {
        try {
            usersDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UsersEntity> findAllUsers() {
        return (ArrayList<UsersEntity>) usersDao.findAll();
    }

}
