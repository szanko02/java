package by.gsu.pms.practice2.inh.task2;

public abstract class Purchase implements Comparable<Purchase> {
    public Purchase() {
        setCommodity(new Commodity());
        setCount(0);
    }
    
    public Purchase(Commodity commodity, int count) {
        setCommodity(commodity);
        setCount(count);
    }
    
    private Commodity commodity;
    private int count;
    
    public Commodity getCommodity() {
        return commodity;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCommodity(Commodity value) {
        commodity = value;
    }
    
    public void setCount(int value) {
        count = value;
    }
    
    public double getCost() {
        return getCommodity().getPrice() * getCount();
    }
    
    @Override
    public String toString() {
        return getCommodity() + ";" + getCount() + ";" + getCost();
    }
    
    @Override
    public int compareTo(Purchase other) {
        return Double.compare(getCost(), other.getCost());
    }
}
