package models;

public class CustomerModel {
    protected String name;
    protected String lastname;
    protected String dni;
    protected String curp;

    public CustomerModel(String name, String lastname, String dni, String curp) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.curp = curp;
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

}
