package controllers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MariaDB;
import helpers.Key;
import models.CustomerModel;

public class CustomerController extends CustomerModel implements Key {

    public CustomerController(String name, String lastname, String dni, String curp, BigDecimal pkContact,
            BigDecimal pkDirection) {
        super(name, lastname, dni, curp, pkContact, pkDirection);
    }

    public BigDecimal create() {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO customers (name, lastname, dni, curp, contacts_pk_contact, directions_pk_direction) VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, this.getName());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getDni());
            statement.setString(4, this.getCurp());
            statement.setString(5, String.valueOf(this.getPkContact()));
            statement.setString(6, String.valueOf(this.getPkDirection()));
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

    public ResultSet read(String by, String value) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement("");
            ResultSet rs = null;

            if (by == "all") {
                statement = connection.prepareStatement(
                        "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact");

                rs = statement.executeQuery();
            } else if (by == "find") {
                statement = connection.prepareStatement(
                        "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact WHERE customers.name = ?");
                statement.setString(1, value);

                rs = statement.executeQuery();

                if (!rs.isBeforeFirst()) {
                    statement = connection.prepareStatement(
                            "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact WHERE customers.dni = ?");
                    statement.setString(1, value);

                    rs = statement.executeQuery();
                }

                if (!rs.isBeforeFirst()) {
                    statement = connection.prepareStatement(
                            "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact WHERE customers.curp = ?");
                    statement.setString(1, value);

                    rs = statement.executeQuery();
                }

                if (!rs.isBeforeFirst()) {
                    statement = connection.prepareStatement(
                            "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact WHERE contacts.email = ?");
                    statement.setString(1, value);

                    rs = statement.executeQuery();
                }
            } else if (by == "CID") {
                statement = connection.prepareStatement(
                        "SELECT customers.pk_customer AS 'Customer ID', customers.name AS 'Customer name', customers.lastname AS 'Customer lastname', customers.dni AS 'Customer DNI', customers.curp AS 'Customer CURP', customers.contacts_pk_contact AS 'Contact key', customers.directions_pk_direction AS 'Address key', directions.street AS 'Customer street', directions.ext_num AS 'Customer exterior number', directions.int_num AS 'Customer interior number', directions.delegation AS 'Customer delegation', directions.country AS 'Customer country', contacts.phone_number AS 'Customer phone', contacts.email AS 'Customer mail' FROM customers INNER JOIN directions ON customers.directions_pk_direction = directions.pk_direction INNER JOIN contacts ON customers.contacts_pk_contact = contacts.pk_contact WHERE customers.pk_customer = ?");
                statement.setString(1, value);

                rs = statement.executeQuery();
            }

            statement.close();
            connection.close();

            return rs;
        } catch (

        SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(BigDecimal key) {
        try {
            Connection connection = new MariaDB().connect();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE customers SET name = ?, lastname = ?, dni = ?, curp = ? WHERE pk_customer = ?");
            statement.setString(1, this.getName());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getDni());
            statement.setString(4, this.getCurp());
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE pk_customer = ?");
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
