package models;

public class UserModel {
    private String name;
    private String lastname;
    private String dni;
    private String curp;
    private int customerKey;
    private int directionLey;

    public UserModel(String name, String lastname, String dni, String curp, int customerKey, int directionLey) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.curp = curp;
        this.customerKey = customerKey;
        this.directionLey = directionLey;
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

    public int getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(int customerKey) {
        this.customerKey = customerKey;
    }

    public int getDirectionLey() {
        return directionLey;
    }

    public void setDirectionLey(int directionLey) {
        this.directionLey = directionLey;
    }

}
