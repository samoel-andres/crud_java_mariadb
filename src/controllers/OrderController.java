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
import models.OrderModel;

public class OrderController extends OrderModel implements Key {

    public OrderController(BigDecimal userKey, BigDecimal customerKey, BigDecimal couponKey, String listItems,
            double cantItems, double onAccount, double subtotal, String status, String comments, String flag,
            String date) {
        super(userKey, customerKey, couponKey, listItems, cantItems, onAccount, subtotal, status, comments, flag, date);
    }

    public BigDecimal create() {
        try {
            Instant instant = Instant.now();
            Timestamp timestamp = Timestamp.from(instant);

            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO orders (users_pk_user, customers_pk_customer, coupons_pk_coupon, list_items, cant_items, subtotal, on_account, status, comments, flag, date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, String.valueOf(this.getUserKey()));
            statement.setString(2, String.valueOf(this.getCustomerKey()));
            statement.setString(3, String.valueOf(this.getCouponKey()));
            statement.setString(4, this.getListItems());
            statement.setDouble(5, this.getCantItems());
            statement.setDouble(6, this.getSubtotal());
            statement.setDouble(7, this.getOnAccount());
            statement.setString(8, this.getStatus());
            statement.setString(9, this.getComments());
            statement.setString(10, this.getFlag());
            statement.setTimestamp(11, timestamp);
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
                    "SELECT orders.pk_order AS 'Order ID', orders.users_pk_user AS 'User ID', orders.customers_pk_customer AS 'Customer ID', orders.coupons_pk_coupon AS 'Coupon', orders.list_items AS 'List items', orders.cant_items AS 'Number of items', orders.subtotal AS 'Purchase amount', orders.on_account AS 'On account', orders.`status` AS 'Order status', orders.comments AS 'Order comments', orders.date AS 'Order date', coupons.coupon AS 'Coupon', coupons.coupon_type AS 'Coupon type', coupons.award AS 'Award', customers.name AS 'Customer name', users.name AS 'User name' FROM orders INNER JOIN customers ON orders.customers_pk_customer = customers.pk_customer INNER JOIN users ON orders.users_pk_user = users.pk_user INNER JOIN coupons ON orders.coupons_pk_coupon = coupons.pk_coupon");
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
                    "UPDATE orders SET users_pk_user = ?, customers_pk_customer = ?, coupons_pk_coupon = ?, list_items = ?, cant_items = ?, subtotal  = ?, on_account = ?, status = ?, comments = ?, flag = ?, date = ? WHERE pk_order = ?");
            statement.setString(1, String.valueOf(this.getUserKey()));
            statement.setString(2, String.valueOf(this.getCustomerKey()));
            statement.setString(3, String.valueOf(this.getCouponKey()));
            statement.setString(4, String.valueOf(this.getListItems()));
            statement.setString(5, String.valueOf(this.getCantItems()));
            statement.setString(6, String.valueOf(this.getOnAccount()));
            statement.setString(7, String.valueOf(this.getSubtotal()));
            statement.setString(8, String.valueOf(this.getStatus()));
            statement.setString(9, String.valueOf(this.getComments()));
            statement.setString(10, String.valueOf(this.getFlag()));
            statement.setString(11, this.getDate());
            statement.setString(12, String.valueOf(key));
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE pk_order = ?");
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
