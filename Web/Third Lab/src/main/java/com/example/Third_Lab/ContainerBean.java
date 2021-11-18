package com.example.Third_Lab;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ContainerBean {

    private PointBean pointBean = new PointBean();

    private ArrayList<PointBean> points = new ArrayList<>();

    public ContainerBean() {

        points = new SQLInputReader().sqlCollect(points);
    }

    public void addPoint() {
        points.add(pointBean);
        new SQLSaver().sqlWrite(points);
        pointBean = new PointBean();
    }

    public PointBean getPointBean() {
        return pointBean;
    }

    public ArrayList<PointBean> getPoints() {
        return points;
    }

    public void setPointBean(PointBean pointBean) {
        this.pointBean = pointBean;
    }

    public void setPoints(ArrayList<PointBean> points) {
        this.points = points;
    }
}
