package models;

public class ProviderModel {
    protected String companyName;
    protected String person;
    protected int contactKey;
    protected int directionKey;

    public ProviderModel(String companyName, String person, int contactKey, int directionKey) {
        this.companyName = companyName;
        this.person = person;
        this.contactKey = contactKey;
        this.directionKey = directionKey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getContactKey() {
        return contactKey;
    }

    public void setContactKey(int contactKey) {
        this.contactKey = contactKey;
    }

    public int getDirectionKey() {
        return directionKey;
    }

    public void setDirectionKey(int directionKey) {
        this.directionKey = directionKey;
    }

}
