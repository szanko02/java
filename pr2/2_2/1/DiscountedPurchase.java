package by.gsu.pms.practice2.inh.task1;

public class DiscountedPurchase extends Purchase {
    public DiscountedPurchase() {
        super();
        setDiscount(0.0);
    }
    
    public DiscountedPurchase(
        String name,
        double price,
        int count,
        double discount
    )
    {
        super(name, price, count);
        setDiscount(discount);
    }
    
    public static final String FILE_TAG = "DISCOUNTED_PURCHASE";
    private double discount;
    
    public static DiscountedPurchase parse(String[] arguments) {
        Purchase base = Purchase.parse(arguments);
        return new DiscountedPurchase(
            base.getName(),
            base.getPrice(),
            base.getCount(),
            Double.parseDouble(arguments[3]));
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double value) {
        discount = value;
    }
    
    public double getCostWithoutDiscount() {
        return super.getCost();
    }
    
    @Override
    public double getCost() {
        double cost = getCostWithoutDiscount();
        return cost - cost * (getDiscount() / 100.0);
    }
    
    @Override
    public String toString() {
        return super.toString() + ";" + getDiscount();
    }
}
