package com.example.Third_Lab;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLSaver {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "vb484732";

//    private static final String url = "jdbc:postgresql://pg/studs";
//    private static final String user = "s312421";
//    private static final String password = "sdn516";

    public void sqlWrite(ArrayList<PointBean> points) {

        try {
            Connection connection = new SQLConnector().connect(url, user, password);
            Statement stmt = connection.createStatement();

            for (PointBean i : points) {
                stmt.executeUpdate("INSERT INTO points (x, y, r, Result, Date)" +
                                " VALUES (" +
                                i.getX() + "', " +
                                i.getY() + "', " +
                                i.getR() + ", '" +
                                i.getResult() + "', '" +
                                i.getDate() + "')");
            }

        } catch (SQLException e) {
            System.out.println("Problem");
        }
    }
}

