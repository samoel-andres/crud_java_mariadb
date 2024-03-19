package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.DirectionModel;

public class DirectionController extends DirectionModel implements Key {
    private MariaDB mdb = new MariaDB();

    public DirectionController(String street, int extNum, int intNum, String delegation, String country) {
        super(street, extNum, intNum, delegation, country);
    }

    public BigDecimal create() {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO directions (street, ext_num, int_num, delegation, country) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, this.getStreet());
            statement.setInt(2, this.getExtNum());
            statement.setInt(3, this.getIntNum());
            statement.setString(4, this.getDelegation());
            statement.setString(5, this.getCountry());
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM directions");
            ResultSet rs = statement.executeQuery();

            statement.close();
            connection.close();

            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(int key) {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE directions SET street = ?, ext_num = ?, int_num = ?, delegation = ?, country = ? WHERE pk_direction = ?");
            statement.setString(1, this.getStreet());
            statement.setString(2, String.valueOf(this.getExtNum()));
            statement.setString(3, String.valueOf(this.getIntNum()));
            statement.setString(4, this.getDelegation());
            statement.setString(5, this.getCountry());
            statement.setInt(6, key);
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
