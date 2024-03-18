package models;

public class StockModel {
    protected int units;
    protected String unitType;
    protected double unitsByUnitType;
    protected double totalUnits;
    protected double priceByUnitType;
    protected int providerKey;

    public StockModel(int units, String unitType, double unitsByUnitType, double totalUnits, double priceByUnitType,
            int providerKey) {
        this.units = units;
        this.unitType = unitType;
        this.unitsByUnitType = unitsByUnitType;
        this.totalUnits = totalUnits;
        this.priceByUnitType = priceByUnitType;
        this.providerKey = providerKey;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public double getUnitsByUnitType() {
        return unitsByUnitType;
    }

    public void setUnitsByUnitType(double unitsByUnitType) {
        this.unitsByUnitType = unitsByUnitType;
    }

    public double getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(double totalUnits) {
        this.totalUnits = totalUnits;
    }

    public double getPriceByUnitType() {
        return priceByUnitType;
    }

    public void setPriceByUnitType(double priceByUnitType) {
        this.priceByUnitType = priceByUnitType;
    }

    public int getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(int providerKey) {
        this.providerKey = providerKey;
    }

}
