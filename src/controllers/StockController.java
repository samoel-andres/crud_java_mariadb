package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.StockModel;

public class StockController extends StockModel implements Key {

    public StockController(int units, String unitType, double unitsByUnitType, double totalUnits,
            double priceByUnitType, BigDecimal providerKey) {
        super(units, unitType, unitsByUnitType, totalUnits, priceByUnitType, providerKey);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO stock (units, unit_type, units_by_unit_type, total_units, price_by_unit_type, providers_pk_provider) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setInt(1, this.getUnits());
            statement.setString(2, this.getUnitType());
            statement.setDouble(3, this.getUnitsByUnitType());
            statement.setDouble(4, this.getTotalUnits());
            statement.setDouble(5, this.getPriceByUnitType());
            statement.setString(6, String.valueOf(this.getProviderKey()));
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
                    "SELECT stock.id_stock AS 'Stock ID', stock.units AS 'Units in stock', stock.unit_type AS 'Unit type', stock.units_by_unit_type AS 'Units by unit', stock.total_units AS 'Total units', stock.price_by_unit_type AS 'Price by unit', stock.providers_pk_provider AS 'Provider ID', providers.company_name AS 'Provider name' FROM stock INNER JOIN providers ON stock.providers_pk_provider = providers.pk_provider");
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
                    "UPDATE stock SET units = ?, unit_type = ?, units_by_unit_type = ?, total_units = ?, price_by_unit_type = ?, providers_pk_provider = ? WHERE id_stock = ?");
            statement.setString(1, String.valueOf(this.getUnits()));
            statement.setString(2, this.getUnitType());
            statement.setString(3, String.valueOf(this.getUnitsByUnitType()));
            statement.setString(4, String.valueOf(this.getTotalUnits()));
            statement.setString(5, String.valueOf(this.getPriceByUnitType()));
            statement.setString(6, String.valueOf(this.getProviderKey()));
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM stock WHERE id_stock = ?");
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
