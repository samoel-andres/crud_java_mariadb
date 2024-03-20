package models;

import java.math.BigDecimal;

public class ProviderModel {
    private String companyName;
    private String person;
    private BigDecimal contactKey;
    private BigDecimal directionKey;

    public ProviderModel(String companyName, String person, BigDecimal contactKey, BigDecimal directionKey) {
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
