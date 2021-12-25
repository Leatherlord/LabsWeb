package msg.ejb.Service;

import msg.ejb.DAO.UsersDAO;
import msg.ejb.UsersEntity;

import java.util.ArrayList;

public class UsersService {

    private final UsersDAO usersDao = new UsersDAO();

    public UsersService() {
    }

    public UsersEntity findUser(long id) {
        return usersDao.findById(id);
    }

    public void saveUser(UsersEntity user) {
        usersDao.save(user);
    }

    public void deleteUser(UsersEntity user) {
        usersDao.delete(user);
    }

    public void updateUser(UsersEntity user) {
        usersDao.update(user);
    }

    public ArrayList<UsersEntity> findAllUsers() {
        return (ArrayList<UsersEntity>) usersDao.findAll();
    }

}
