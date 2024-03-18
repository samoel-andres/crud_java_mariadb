package models;

public class ContactModel {
    protected int phoneNumber;
    protected String email;

    public ContactModel(int phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
