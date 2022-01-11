package msg.ejb.Service;

import msg.ejb.DAO.PointsDAO;
import msg.ejb.PointsEntity;
import msg.ejb.UsersEntity;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PointsService {

    @EJB
    private PointsDAO pointsDAO;

    public void savePoint(PointsEntity pointsEntity) throws Exception {
            pointsDAO.save(pointsEntity);
    }

    public void deletePoint(PointsEntity pointsEntity) throws Exception {
            pointsDAO.delete(pointsEntity);
    }

    public List<PointsEntity> findByUser(UsersEntity usersEntity) {
        return pointsDAO.findByUser(usersEntity);
    }

    public ArrayList<PointsEntity> findAllPoints() {
        return (ArrayList<PointsEntity>) pointsDAO.findAll();
    }

}
