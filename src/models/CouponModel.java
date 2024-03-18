package models;

public class CouponModel {
    private double minPurchase;
    private double maxPurchase;
    private String expires;
    private String status;
    private String couponType;
    private double award;

    public CouponModel(double minPurchase, double maxPurchase, String expires, String status, String couponType,
            double award) {
        this.minPurchase = minPurchase;
        this.maxPurchase = maxPurchase;
        this.expires = expires;
        this.status = status;
        this.couponType = couponType;
        this.award = award;
    }

    public double getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(double minPurchase) {
        this.minPurchase = minPurchase;
    }

    public double getMaxPurchase() {
        return maxPurchase;
    }

    public void setMaxPurchase(double maxPurchase) {
        this.maxPurchase = maxPurchase;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

}
