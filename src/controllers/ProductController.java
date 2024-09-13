package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.ProductModel;

public class ProductController extends ProductModel implements Key {

    public ProductController(String name, String size, double price, BigDecimal stockKey) {
        super(name, size, price, stockKey);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO products (name, size, price, stock_id_stock) VALUES(?, ?, ?, ?)");
            statement.setString(1, this.getName());
            statement.setString(2, this.getSize());
            statement.setDouble(3, this.getPrice());
            statement.setString(4, String.valueOf(this.getStockKey()));
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

    // modificando
    public ResultSet read(String by, String value) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement("");

            if (by == "product-name") {
                statement = connection.prepareStatement(
                        "SELECT products.pk_product AS 'Product ID', products.name AS 'Product name', products.size AS 'Product size', products.price AS 'Product price', stock.total_units AS 'In stock', products.stock_id_stock AS 'Stock ID', providers.company_name AS 'Provider name' FROM products INNER JOIN stock ON products.stock_id_stock = stock.id_stock INNER JOIN providers ON stock.providers_pk_provider = providers.pk_provider WHERE products.name = ?");
                statement.setString(1, value);
            } else if (by == "product-key") {
                statement = connection.prepareStatement(
                        "SELECT products.pk_product AS 'Product ID', products.name AS 'Product name', products.size AS 'Product size', products.price AS 'Product price', stock.total_units AS 'In stock', products.stock_id_stock AS 'Stock ID', providers.company_name AS 'Provider name' FROM products INNER JOIN stock ON products.stock_id_stock = stock.id_stock INNER JOIN providers ON stock.providers_pk_provider = providers.pk_provider WHERE products.pk_product = ?");
                statement.setString(1, value);
            } else if (by == "all") {
                statement = connection.prepareStatement(
                        "SELECT products.pk_product AS 'Product ID', products.name AS 'Product name', products.size AS 'Product size', products.price AS 'Product price', stock.total_units AS 'In stock', products.stock_id_stock AS 'Stock ID', providers.company_name AS 'Provider name' FROM products INNER JOIN stock ON products.stock_id_stock = stock.id_stock INNER JOIN providers ON stock.providers_pk_provider = providers.pk_provider");
            } else if (by == "PID") {
                statement = connection.prepareStatement(
                        "SELECT products.pk_product AS 'Product ID', products.name AS 'Product name', products.size AS 'Product size', products.price AS 'Product price', stock.total_units AS 'In stock', stock.providers_pk_provider AS 'PID', products.stock_id_stock AS 'Stock ID', providers.company_name AS 'Provider name' FROM products INNER JOIN stock ON products.stock_id_stock = stock.id_stock INNER JOIN providers ON stock.providers_pk_provider = providers.pk_provider WHERE stock.providers_pk_provider = ?");
                statement.setString(1, value);
            }

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
                    "UPDATE products SET name = ?, size = ?, price = ?, stock_id_stock = ? WHERE pk_product = ?");
            statement.setString(1, this.getName());
            statement.setString(2, this.getSize());
            statement.setString(3, String.valueOf(this.getPrice()));
            statement.setString(4, String.valueOf(this.getStockKey()));
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

    public boolean delete(BigDecimal key) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE pk_product = ?");
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
