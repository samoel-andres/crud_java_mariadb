package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.MariaDB;
import helpers.Key;
import models.ContactModel;

public class ContactController extends ContactModel implements Key {

    public ContactController(String phoneNumber, String email) {
        super(phoneNumber, email);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO contacts (phone_number, email) VALUES(?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, this.getPhoneNumber());
            statement.setString(2, this.getEmail());
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts");
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
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE contacts SET phone_number = ?, email = ? WHERE pk_contact = ?");
            statement.setString(1, this.getPhoneNumber());
            statement.setString(2, this.getEmail());
            statement.setString(3, String.valueOf(key));
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
