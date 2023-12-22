package org.example.containers;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDataBaseContainer {
    private static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/site";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    @SneakyThrows
    public static Connection getConnection(){
        if (connection == null){
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } else {
            return connection;
        }
    }
}
