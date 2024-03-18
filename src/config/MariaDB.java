package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
    private Connection connection;
    private String user = "root";
    private String pass = "root";
    private String uri = "jdbc:mariadb://127.0.0.1:3307/store";

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(uri, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
