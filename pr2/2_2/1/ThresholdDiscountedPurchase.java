package by.gsu.pms.practice2.inh.task1;

public class ThresholdDiscountedPurchase extends DiscountedPurchase {
    public ThresholdDiscountedPurchase() {
        super();
        setThreshold(0);
    }
    
    public ThresholdDiscountedPurchase(
        String name,
        double price,
        int count,
        double discount,
        int threshold
    )
    {
        super(name, price, count, discount);
        setThreshold(threshold);
    }
    
    public static final String FILE_TAG = "THRESHOLD_DISCOUNTED_PURCHASE";
    private int threshold;
    
    public static ThresholdDiscountedPurchase parse(String[] arguments) {
        DiscountedPurchase base = DiscountedPurchase.parse(arguments);
        return new ThresholdDiscountedPurchase(
            base.getName(),
            base.getPrice(),
            base.getCount(),
            base.getDiscount(),
            Integer.parseInt(arguments[4]));
    }
    
    public int getThreshold() {
        return threshold;
    }
    
    public void setThreshold(int value) {
        threshold = value;
    }
    
    @Override
    public double getCost() {
        if (getCount() > threshold) {
            return super.getCost();
        } else {
            return super.getCostWithoutDiscount();
        }
    }
}
