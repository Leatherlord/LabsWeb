package com.example.Third_Lab;

import java.sql.*;
import java.util.ArrayList;

public class SQLInputReader {

//    private static final String url = "secret";
//    private static final String user = "secret";
//    private static final String password = "secret";

    private static final String url = "secret";
    private static final String user = "secret";
    private static final String password = "secret";

    public ArrayList<PointBean> sqlCollect(ArrayList<PointBean> points) {

        try (Connection con = new SQLConnector().connect(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM points")) {
            while (rs.next()) {
                double x = rs.getDouble("X");
                double y = rs.getDouble("Y");
                double r = rs.getDouble("R");
//                boolean result = rs.getBoolean("Result");
                Timestamp date = rs.getTimestamp("Date");

                PointBean point = new PointBean();
                point.setX(x);
                point.setY(y);
                point.setR(r);
                point.setDate(date);
                points.add(point);

            }
            System.out.println("All good");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return points;
    }


}

