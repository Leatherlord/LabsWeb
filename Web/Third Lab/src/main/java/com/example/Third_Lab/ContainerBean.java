package com.example.Third_Lab;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;


@ManagedBean(name = "containerBean")
@ApplicationScoped
public class ContainerBean {

    @ManagedProperty(value = "#{sQLInputReader}")
    private SQLInputReader reader;

    @ManagedProperty(value = "#{sQLSaver}")
    private SQLSaver saver;

    private PointBean pointBean = new PointBean();

    private ArrayList<PointBean> points;


    public void addPoint() {
        if (points == null) {
            points = reader.sqlCollect(new ArrayList<>());
        }
        points.add(pointBean);
        saver.sqlWrite(pointBean);
        pointBean = new PointBean();
    }

    public PointBean getPointBean() {
        return pointBean;
    }

    public void setPointBean(PointBean pointBean) {
        this.pointBean = pointBean;
    }

    public ArrayList<PointBean> getPoints() {
        if (points == null) {
            points = reader.sqlCollect(new ArrayList<>());
        }
        return points;
    }

    public void setPoints(ArrayList<PointBean> points) {
        this.points = points;
    }

    public SQLInputReader getReader() {
        return reader;
    }

    public void setReader(SQLInputReader reader) {
        this.reader = reader;
    }

    public SQLSaver getSaver() {
        return saver;
    }

    public void setSaver(SQLSaver saver) {
        this.saver = saver;
    }
}
