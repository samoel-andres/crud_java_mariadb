package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.UserModel;

public class UserController extends UserModel implements Key {
    private MariaDB mdb = new MariaDB();

    public UserController(String name, String lastname, String dni, String curp, BigDecimal contactKey,
            BigDecimal directionKey) {
        super(name, lastname, dni, curp, contactKey, directionKey);
    }

    public BigDecimal create() {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, lastname, dni, curp, contacts_pk_contact, directions_pk_direction) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, this.getName());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getDni());
            statement.setString(4, this.getCurp());
            statement.setString(5, String.valueOf(this.getContactKey()));
            statement.setString(6, String.valueOf(this.getDirectionKey()));
            statement.executeUpdate();

            BigDecimal key = generatedKey(statement);

            statement.close();
            connection.close();

            return key;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet read() {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = statement.executeQuery();

            statement.close();
            connection.close();

            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(BigDecimal key) {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET name = ?, lastname = ?, dni = ?, curp = ?, contacts_pk_contact = ?, directions_pk_direction = ? WHERE pk_user = ?");
            statement.setString(1, this.getName());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getDni());
            statement.setString(4, this.getCurp());
            statement.setString(5, String.valueOf(this.getContactKey()));
            statement.setString(6, String.valueOf(this.getDirectionKey()));
            statement.setString(7, String.valueOf(key));
            statement.executeUpdate();

            statement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete() {
        return false;
    }

    @Override
    public BigDecimal generatedKey(PreparedStatement statement) {
        ResultSet rs;
        BigDecimal key;
        try {
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getBigDecimal(1);
                rs.close();
                return key;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
