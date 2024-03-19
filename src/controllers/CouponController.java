package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.CouponModel;

public class CouponController extends CouponModel implements Key {
    private MariaDB mdb = new MariaDB();

    public CouponController(double minPurchase, double maxPurchase, String expires, String status, String couponType,
            double award, String coupon) {
        super(minPurchase, maxPurchase, expires, status, couponType, award, coupon);
    }

    public BigDecimal create() {
        try {
            Connection connection = mdb.connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO coupons (minimum_purchase, maximum_purchase, expires, status, coupon_type, award, coupon) VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setDouble(1, this.getMinPurchase());
            statement.setDouble(2, this.getMaxPurchase());
            statement.setString(3, this.getExpires());
            statement.setString(4, this.getStatus());
            statement.setString(5, this.getCouponType());
            statement.setDouble(6, this.getAward());
            statement.setString(7, this.getCoupon());
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM coupons");
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
            PreparedStatement statement = connection
                    .prepareStatement(
                            "UPDATE coupons SET minimum_purchase = ?, maximum_purchase = ?, expires = ?, statuss = ?, coupon_type = ?, award = ?, coupon = ? WHERE pk_coupon = ?");
            statement.setString(1, String.valueOf(this.getMinPurchase()));
            statement.setString(2, String.valueOf(this.getMaxPurchase()));
            statement.setString(3, this.getExpires());
            statement.setString(4, this.getStatus());
            statement.setString(5, this.getCouponType());
            statement.setString(6, String.valueOf(this.getAward()));
            statement.setString(7, this.getCoupon());
            statement.setInt(8, key);
            statement.execute();

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
