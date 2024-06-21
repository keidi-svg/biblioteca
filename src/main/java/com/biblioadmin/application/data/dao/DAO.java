package com.biblioadmin.application.data.dao;

import java.sql.Connection;

public abstract class DAO {

    private Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }
}
