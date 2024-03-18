package models;

public class SaleModel {
    protected int userKey;
    protected int customerKey;
    protected int couponKey;
    protected int productKey;
    protected int cantItems;
    protected double subtotal;
    protected String flag;

    public SaleModel(int userKey, int customerKey, int couponKey, int productKey, int cantItems, double subtotal,
            String flag) {
        this.userKey = userKey;
        this.customerKey = customerKey;
        this.couponKey = couponKey;
        this.productKey = productKey;
        this.cantItems = cantItems;
        this.subtotal = subtotal;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
