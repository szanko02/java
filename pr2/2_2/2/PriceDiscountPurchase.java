package by.gsu.pms.practice2.inh.task2;

public class PriceDiscountPurchase extends Purchase {
    public PriceDiscountPurchase() {
        setDiscountedPrice(0.0);
    }
    
    public PriceDiscountPurchase(
        Commodity commodity,
        int count,
        double discountedPrice
    )
    {
        super(commodity, count);
        setDiscountedPrice(discountedPrice);
    }
    
    private double discountedPrice;
    
    public double getDiscountedPrice() {
        return discountedPrice;
    }
    
    public void setDiscountedPrice(double value) {
        discountedPrice = value;
    }
    
    @Override
    public double getCost() {
        return Math.max(super.getCost() - getDiscountedPrice(), 0.0);
    }
    
    @Override
    public String toString() {
        return super.toString() + ";" + getDiscountedPrice();
    }
}
