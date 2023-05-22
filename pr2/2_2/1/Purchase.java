package by.gsu.pms.practice2.inh.task1;

public class Purchase {
    public Purchase() {
        setName("Unnamed commodity");
        setPrice(0.0);
        setCount(0);
    }
    
    public Purchase(String name, double price, int count) {
        setName(name);
        setPrice(price);
        setCount(count);
    }
    
    public static final String FILE_TAG = "PURCHASE";
    private String name;
    private double price;
    private int count;
    
    public static Purchase parse(String[] arguments) {
        return new Purchase(
            arguments[0],
            Double.parseDouble(arguments[1]),
            Integer.parseInt(arguments[2]));
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setName(String value) {
        name = value;
    }
    
    public void setPrice(double value) {
        price = value;
    }
    
    public void setCount(int value) {
        count = value;
    }
    
    public double getCost() {
        return getPrice() * getCount();
    }
    
    public boolean equals(Purchase other) {
        return getName().equals(other.getName())
            && getPrice() == other.getPrice();
    }
    
    @Override
    public String toString() {
        return getName()
            + ";"
            + getPrice()
            + ";"
            + getCount()
            + ";"
            + getCost();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Purchase other
            && equals(other);
    }
}
