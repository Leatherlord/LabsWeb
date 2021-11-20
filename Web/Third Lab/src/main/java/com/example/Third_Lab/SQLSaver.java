package com.example.Third_Lab;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "sQLSaver")
@ApplicationScoped
public class SQLSaver {

    @ManagedProperty(value = "#{sQLConnector}")
    private SQLConnector connector;

    public void sqlWrite(ArrayList<PointBean> points) {

        try (Connection con = connector.getConnection()) {

            for (PointBean i : points) {
                prepareStatement(i, con);
            }
            System.out.println("Saving completed");
        } catch (SQLException e) {
            System.out.println("Writing problem");
        }
    }

    public void sqlWrite(PointBean point) {

        try (Connection con = connector.getConnection()) {

            prepareStatement(point, con);
            System.out.println("Saving completed");
        } catch (SQLException e) {
            System.out.println("Writing problem");
        }
    }

    private void prepareStatement(PointBean i, Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement("insert into points values (?, ?, ?, ?, ?);");
        statement.setDouble(1, i.getX());
        statement.setDouble(2, i.getY());
        statement.setDouble(3, i.getR());
        statement.setBoolean(4, i.getResult());
        statement.setTimestamp(5, i.getDate());
        statement.execute();
    }

    public SQLConnector getConnector() {
        return connector;
    }

    public void setConnector(SQLConnector connector) {
        this.connector = connector;
    }
}

