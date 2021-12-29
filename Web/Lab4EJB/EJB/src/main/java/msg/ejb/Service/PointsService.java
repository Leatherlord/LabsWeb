package msg.ejb.Service;

import msg.ejb.DAO.PointsDAO;
import msg.ejb.PointsEntity;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
public class PointsService {

    @EJB
    private PointsDAO pointsDAO;

    public void savePoint(PointsEntity pointsEntity) {
        try {
            pointsDAO.save(pointsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePoint(PointsEntity pointsEntity) {
        try {
            pointsDAO.delete(pointsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<PointsEntity> findAllPoints() {
        return (ArrayList<PointsEntity>) pointsDAO.findAll();
    }

}
