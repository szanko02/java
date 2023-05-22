package by.gsu.pms.practice2.inh.task2;

public class TransportExpensePurchase extends Purchase {
    public TransportExpensePurchase() {
        setTransportExpense(0.0);
    }
    
    public TransportExpensePurchase(
        Commodity commodity,
        int count,
        double transportExpense
    )
    {
        super(commodity, count);
        setTransportExpense(transportExpense);
    }
    
    private double transportExpense;
    
    public double getTransportExpense() {
        return transportExpense;
    }
    
    public void setTransportExpense(double value) {
        transportExpense = value;
    }
    
    @Override
    public double getCost() {
        return super.getCost() + getTransportExpense();
    }
    
    @Override
    public String toString() {
        return super.toString() + ";" + getTransportExpense();
    }
}
