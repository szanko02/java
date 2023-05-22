package by.gsu.pms.practice2.inh.task2;

public class PercentageDiscountPurchase extends Purchase {
    public PercentageDiscountPurchase() {
        setDiscountedPercentage(0.0);
    }
    
    public PercentageDiscountPurchase(
        Commodity commodity,
        int count,
        double discountedPercentage
    )
    {
        super(commodity, count);
        setDiscountedPercentage(discountedPercentage);
    }
    
    private double discountedPercentage;
    
    public double getDiscountedPercentage() {
        return discountedPercentage;
    }
    
    public void setDiscountedPercentage(double value) {
        discountedPercentage = value;
    }
    
    @Override
    public double getCost() {
        double baseCost = super.getCost();
        return baseCost - baseCost * (getDiscountedPercentage() / 100.0);
    }
    
    @Override
    public String toString() {
        return super.toString() + ";" + getDiscountedPercentage();
    }
}
