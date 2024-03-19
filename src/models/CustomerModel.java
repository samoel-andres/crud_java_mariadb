package models;

import java.math.BigDecimal;

public class CustomerModel {
    private String name;
    private String lastname;
    private String dni;
    private String curp;
    private BigDecimal pkContact;
    private BigDecimal pkDirection;

    public CustomerModel(String name, String lastname, String dni, String curp, BigDecimal pkContact,
            BigDecimal pkDirection) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.curp = curp;
        this.pkContact = pkContact;
        this.pkDirection = pkDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public BigDecimal getPkContact() {
        return pkContact;
    }

    public void setPkContact(BigDecimal pkContact) {
        this.pkContact = pkContact;
    }

    public BigDecimal getPkDirection() {
        return pkDirection;
    }

    public void setPkDirection(BigDecimal pkDirection) {
        this.pkDirection = pkDirection;
    }

}
