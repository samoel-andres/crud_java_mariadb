package models;

public class DirectionModel {
    private String street;
    private int extNum;
    private int intNum;
    private String delegation;
    private String country;

    public DirectionModel(String street, int extNum, int intNum, String delegation, String country) {
        this.street = street;
        this.extNum = extNum;
        this.intNum = intNum;
        this.delegation = delegation;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getExtNum() {
        return extNum;
    }

    public void setExtNum(int extNum) {
        this.extNum = extNum;
    }

    public int getIntNum() {
        return intNum;
    }

    public void setIntNum(int intNum) {
        this.intNum = intNum;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
