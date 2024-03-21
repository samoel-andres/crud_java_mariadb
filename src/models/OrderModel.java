package models;

import java.math.BigDecimal;

public class OrderModel {
    private BigDecimal userKey;
    private BigDecimal customerKey;
    private BigDecimal couponKey;
    private String listItems;
    private double cantItems;
    private double onAccount;
    private double subtotal;
    private String status;
    private String comments;
    private String flag;
    private String date;

    public OrderModel(BigDecimal userKey, BigDecimal customerKey, BigDecimal couponKey, String listItems,
            double cantItems, double onAccount, double subtotal, String status, String comments, String flag,
            String date) {
        this.userKey = userKey;
        this.customerKey = customerKey;
        this.couponKey = couponKey;
        this.listItems = listItems;
        this.cantItems = cantItems;
        this.onAccount = onAccount;
        this.subtotal = subtotal;
        this.status = status;
        this.comments = comments;
        this.flag = flag;
        this.date = date;
    }

    public BigDecimal getUserKey() {
        return userKey;
    }

    public void setUserKey(BigDecimal userKey) {
        this.userKey = userKey;
    }

    public BigDecimal getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(BigDecimal customerKey) {
        this.customerKey = customerKey;
    }

    public BigDecimal getCouponKey() {
        return couponKey;
    }

    public void setCouponKey(BigDecimal couponKey) {
        this.couponKey = couponKey;
    }

    public String getListItems() {
        return listItems;
    }

    public void setListItems(String listItems) {
        this.listItems = listItems;
    }

    public double getCantItems() {
        return cantItems;
    }

    public void setCantItems(double cantItems) {
        this.cantItems = cantItems;
    }

    public double getOnAccount() {
        return onAccount;
    }

    public void setOnAccount(double onAccount) {
        this.onAccount = onAccount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
