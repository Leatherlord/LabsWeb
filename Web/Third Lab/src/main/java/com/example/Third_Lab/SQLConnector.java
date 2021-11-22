package com.example.Third_Lab;

import org.postgresql.ds.common.BaseDataSource;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ManagedBean(name = "sQLConnector")
@ApplicationScoped
public class SQLConnector {

    @Resource(lookup = "java:/PostgresDS")
    private DataSource dataSource;


    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
