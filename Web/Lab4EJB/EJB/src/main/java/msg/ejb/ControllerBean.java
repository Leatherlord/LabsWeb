package msg.ejb;

import msg.ejb.Service.PointsService;
import msg.ejb.Service.UsersService;

import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


@Singleton
@TransactionManagement(value= TransactionManagementType.BEAN)
public class ControllerBean {

    private final ArrayList<UsersEntity> users;
    private final ArrayList<PointsEntity> points;

    {
        users = new UsersService().findAllUsers();
        points = new PointsService().findAllPoints();
    }

    public void addPoint(PointsEntity point) {
        point.setResult(isInArea(point));
        point.setDate(new Timestamp(new Date().getTime()));
        points.add(point);
        new PointsService().savePoint(point);
    }

    public ArrayList<PointsEntity> getPoints() {
        return points;
    }

    public ArrayList<UsersEntity> getUsers() {
        return users;
    }

    public boolean isRegistered(String login, String password) {
        for (UsersEntity user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
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
