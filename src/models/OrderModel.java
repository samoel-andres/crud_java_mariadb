package models;

public class OrderModel {
    private int userKey;
    private int customerKey;
    private int couponKey;
    private int productKey;
    private int cantItems;
    private double onAccount;
    private double subtotal;
    private String status;
    private String comments;
    private String flag;

    public OrderModel(int userKey, int customerKey, int couponKey, int productKey, int cantItems, double onAccount,
            double subtotal, String status, String comments, String flag) {
        this.userKey = userKey;
        this.customerKey = customerKey;
        this.couponKey = couponKey;
        this.productKey = productKey;
        this.cantItems = cantItems;
        this.onAccount = onAccount;
        this.subtotal = subtotal;
        this.status = status;
        this.comments = comments;
        this.flag = flag;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public int getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(int customerKey) {
        this.customerKey = customerKey;
    }

    public int getCouponKey() {
        return couponKey;
    }

    public void setCouponKey(int couponKey) {
        this.couponKey = couponKey;
    }

    public int getProductKey() {
        return productKey;
    }

    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }

    public int getCantItems() {
        return cantItems;
    }

    public void setCantItems(int cantItems) {
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

}
