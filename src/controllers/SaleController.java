package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import config.MariaDB;
import helpers.Key;
import models.SaleModel;

public class SaleController extends SaleModel implements Key {

    public SaleController(BigDecimal userKey, BigDecimal customerKey, BigDecimal couponKey, String listItems,
            double cantItems, double subtotal, String flag, String date) {
        super(userKey, customerKey, couponKey, listItems, cantItems, subtotal, flag, date);
    }

    public BigDecimal create() {
        try {
            Instant instant = Instant.now();
            Timestamp timestamp = Timestamp.from(instant);

            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO sales (users_pk_user, customers_pk_customer, coupons_pk_coupon, list_items, cant_items, subtotal, flag, date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, String.valueOf(this.getUserKey()));
            statement.setString(2, String.valueOf(this.getCustomerKey()));
            statement.setString(3, String.valueOf(this.getCouponKey()));
            statement.setString(4, this.getListItems());
            statement.setDouble(5, this.getCantItems());
            statement.setDouble(6, this.getSubtotal());
            statement.setString(7, this.getFlag());
            statement.setTimestamp(8, timestamp);
            statement.executeQuery();

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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sales");
            ResultSet rs = statement.executeQuery();

            statement.close();
            connection.close();

            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(BigDecimal key) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE sales SET users_pk_user = ?, customers_pk_customer = ?, coupons_pk_coupon = ?, list_items = ?, cant_items = ?, subtotal = ?, flag = ?, date = ? WHERE pk_sale = ?");
            statement.setString(1, String.valueOf(this.getUserKey()));
            statement.setString(2, String.valueOf(this.getCustomerKey()));
            statement.setString(3, String.valueOf(this.getCouponKey()));
            statement.setString(4, this.getListItems());
            statement.setString(5, String.valueOf(this.getCantItems()));
            statement.setString(6, String.valueOf(this.getSubtotal()));
            statement.setString(7, this.getFlag());
            statement.setString(8, this.getDate());
            statement.setString(9, String.valueOf(key));
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
