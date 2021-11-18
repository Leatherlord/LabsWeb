package com.example.Third_Lab;


import java.sql.*;
import java.util.ArrayList;

public class SQLSaver {

//    private static final String url = "secret";
//    private static final String user = "secret";
//    private static final String password = "secret";

    private static final String url = "secret";
    private static final String user = "secret";
    private static final String password = "secret";

    public void sqlWrite(ArrayList<PointBean> points) {

        try (Connection con = new SQLConnector().connect(url, user, password);
             Statement stmt = con.createStatement();
             ) {

            for (PointBean i : points) {
                PreparedStatement statement = con.prepareStatement("insert into points values (?, ?, ?, ?, ?);");
                statement.setDouble(1, i.getX());
                statement.setDouble(2, i.getY());
                statement.setDouble(3, i.getR());
                statement.setBoolean(4, i.getResult());
                statement.setTimestamp(5, i.getDate());
                statement.execute();

            }

        } catch (SQLException e) {
            System.out.println("Problem");
        }
    }
}

