package com.example.Third_Lab;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import java.sql.*;
import java.util.ArrayList;

@javax.faces.bean.ManagedBean(name = "sQLInputReader")
@ApplicationScoped
public class SQLInputReader {

    @ManagedProperty(value = "#{sQLConnector}")
    private SQLConnector connector;

    public ArrayList<PointBean> sqlCollect(ArrayList<PointBean> points) {

        try (Connection con = connector.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM points")) {
            while (rs.next()) {
                double x = rs.getDouble("X");
                double y = rs.getDouble("Y");
                double r = rs.getDouble("R");
                Timestamp date = rs.getTimestamp("Date");

                PointBean point = new PointBean();
                point.setX(x);
                point.setY(y);
                point.setR(r);
                point.setDate(date);
                points.add(point);

            }
            System.out.println("Reading completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return points;
    }

    public SQLConnector getConnector() {
        return connector;
    }

    public void setConnector(SQLConnector connector) {
        this.connector = connector;
    }
}

