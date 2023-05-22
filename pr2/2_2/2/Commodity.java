package by.gsu.pms.practice2.inh.task2;

public class Commodity {
    public Commodity() {
        setName("Unnamed commodity");
        setPrice(0.0);
    }
    
    public Commodity(String name, double price) {
        setName(name);
        setPrice(price);
    }
    
    private String name;
    private double price;
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setName(String value) {
        name = value;
    }
    
    public void setPrice(double value) {
        price = value;
    }
    
    @Override
    public String toString() {
        return getName() + ";" + getPrice();
    }
}
