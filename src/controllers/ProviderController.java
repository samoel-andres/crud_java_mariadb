package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.ProviderModel;

public class ProviderController extends ProviderModel implements Key {

    public ProviderController(String companyName, String person, BigDecimal contactKey, BigDecimal directionKey) {
        super(companyName, person, contactKey, directionKey);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO providers (company_name, person, contacts_pk_contact, directions_pk_direction) VALUES(?, ?, ?, ?)");
            statement.setString(1, this.getCompanyName());
            statement.setString(2, this.getPerson());
            statement.setString(3, String.valueOf(this.getContactKey()));
            statement.setString(4, String.valueOf(this.getDirectionKey()));
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM providers");
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
                    "UPDATE providers SET company_name = ?, person = ?, contacts_pk_contact = ?, directions_pk_direction = ? WHERE pk_provider = ?");
            statement.setString(1, this.getCompanyName());
            statement.setString(2, this.getPerson());
            statement.setString(3, String.valueOf(this.getContactKey()));
            statement.setString(4, String.valueOf(this.getDirectionKey()));
            statement.setString(5, String.valueOf(key));
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
