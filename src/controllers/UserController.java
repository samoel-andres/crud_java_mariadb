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

    public UserController(String name, String lastname, String dni, String curp, BigDecimal contactKey,
            BigDecimal directionKey) {
        super(name, lastname, dni, curp, contactKey, directionKey);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
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
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT users.pk_user AS 'User ID', users.name AS 'User name', users.lastname AS 'User lastname', users.dni AS 'User DNI', users.curp AS 'User CURP', users.contacts_pk_contact AS 'Contact key', users.directions_pk_direction AS 'Address key', directions.street AS 'User address', directions.ext_num AS 'User exterior number', directions.int_num AS 'User interior number', directions.delegation AS 'User delegation', directions.country AS 'User country', contacts.phone_number AS 'User phone',  contacts.email AS 'User mail' FROM users INNER JOIN directions ON users.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON users.contacts_pk_contact = contacts.pk_contact");
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
            Connection connection = new MariaDB().connect();
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

    public boolean delete(BigDecimal key) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE pk_user = ?");
            statement.setString(1, String.valueOf(key));
            statement.executeUpdate();

            statement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
