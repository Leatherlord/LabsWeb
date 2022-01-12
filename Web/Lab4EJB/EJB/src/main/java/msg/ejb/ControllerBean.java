package msg.ejb;

import msg.ejb.Service.PointsService;
import msg.ejb.Service.UsersService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


@Singleton
@TransactionManagement(value= TransactionManagementType.BEAN)
public class ControllerBean {

    @EJB
    private PointsService pointsService;

    @EJB
    private UsersService usersService;

    public void addPoint(PointsEntity point, String login) throws Exception {
        point.setResult(isInArea(point));
        point.setDate(new Timestamp(new Date().getTime()));
        point.setUser_id(usersService.findByLogin(login));
        pointsService.savePoint(point);
    }

    public void addUser(UsersEntity user) throws Exception {
        usersService.saveUser(user);
    }

    public ArrayList<PointsEntity> getPoints(String login) {
        return (ArrayList<PointsEntity>) pointsService.findByUser(usersService.findByLogin(login));
    }

    public ArrayList<UsersEntity> getUsers() {
        return usersService.findAllUsers();
    }

    public boolean isRegistered(String login, String password) {
        UsersEntity user = usersService.findByLogin(login);
        if (user != null) return user.getPassword().equals(password);
        return false;
    }

    private boolean isInArea(PointsEntity pointsEntity) {
        double x = pointsEntity.getX();
        double y = pointsEntity.getY();
        double r = pointsEntity.getR();
        if (r < 0) {
            x = -x;
            y = -y;
            return ((x >= 0 && x <= -r / 2 && ((y <= 0 && y >= 2 * x + r) || ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r/2, 2) && y >= 0))) || (x <= 0 && x >= r && y >= 0 && y <= -r / 2));
        }
        return ((x <= 0 && x >= -r / 2 && ((y >= 0 && y <= 2 * x + r) || ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r/2, 2) && y <= 0))) || (x >= 0 && x <= r && y <= 0 && y >= -r / 2));
    }
}
