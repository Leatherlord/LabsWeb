package msg.ejb.Service;

import msg.ejb.DAO.PointsDAO;
import msg.ejb.PointsEntity;

import java.util.ArrayList;

public class PointsService {

    private final PointsDAO pointsDAO = new PointsDAO();

    public PointsService() {
    }

    public PointsEntity findPoint(long id) {
        return pointsDAO.findById(id);
    }

    public void savePoint(PointsEntity pointsEntity) {
        pointsDAO.save(pointsEntity);
    }

    public void deletePoint(PointsEntity pointsEntity) {
        pointsDAO.delete(pointsEntity);
    }

    public void updatePoint(PointsEntity pointsEntity) {
        pointsDAO.update(pointsEntity);
    }

    public ArrayList<PointsEntity> findAllPoints() {
        return (ArrayList<PointsEntity>) pointsDAO.findAll();
    }

}
