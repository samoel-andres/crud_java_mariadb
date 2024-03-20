package models;

import java.math.BigDecimal;

public class UserModel {
    private String name;
    private String lastname;
    private String dni;
    private String curp;
    private BigDecimal contactKey;
    private BigDecimal directionKey;

    public UserModel(String name, String lastname, String dni, String curp, BigDecimal contactKey,
            BigDecimal directionKey) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.curp = curp;
        this.contactKey = contactKey;
        this.directionKey = directionKey;
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

    public BigDecimal getContactKey() {
        return contactKey;
    }

    public void setContactKey(BigDecimal contactKey) {
        this.contactKey = contactKey;
    }

    public BigDecimal getDirectionKey() {
        return directionKey;
    }

    public void setDirectionKey(BigDecimal directionKey) {
        this.directionKey = directionKey;
    }

}
