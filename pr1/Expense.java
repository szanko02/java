package by.gsu.pms.practice1;

public class Expense {
    public Expense() {
        this.allowanceRate = 0.0;
        this.accountName = "Unknown";
        this.transportationExpense = 0.0;
        this.dayCount = 0;
    }
    
    public Expense(
        double allowanceRate,
        String accountName,
        double transportationExpense,
        int dayCount
    )
    {
        this.allowanceRate = allowanceRate;
        this.accountName = accountName;
        this.transportationExpense = transportationExpense;
        this.dayCount = dayCount;
    }
    
    private final double allowanceRate;
    private String accountName;
    private double transportationExpense;
    private int dayCount;
    
    public double getAllowanceRate() {
        return allowanceRate;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public double getTransportationExpense() {
        return transportationExpense;
    }
    
    public int getDayCount() {
        return dayCount;
    }
    
    public void setAccountName(String value) {
        accountName = value;
    }
    
    public void setTransportationExpense(double value) {
        transportationExpense = value;
    }
    
    public void setDayCount(int value) {
        dayCount = value;
    }
    
    public double getTotal() {
        return transportationExpense + allowanceRate * dayCount;
    }
    
    public void show() {
        System.out.println("rate = " + allowanceRate);
        System.out.println("account = " + accountName);
        System.out.println("transport = " + transportationExpense);
        System.out.println("days = " + dayCount);
        System.out.println("total = " + getTotal());
    }
    
    @Override
    public String toString() {
        return String.format(
            "%f;%s;%f;%d;%f",
            allowanceRate,
            accountName,
            transportationExpense,
            dayCount,
            getTotal());
    }
}
