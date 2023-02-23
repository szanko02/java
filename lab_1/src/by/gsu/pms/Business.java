package by.gsu.pms;

public class Business {
    private static final int DAILY_ALLOWANCE_RATE = 25000;
    private String account;
    private int transportation_expenses;
    private int days;

    public Business(){
        account = "sweety ";
        transportation_expenses = 0;
        days = 0;
    }
    public Business(String account, int transportation_expenses, int days) {
        this.account = account;
        this.transportation_expenses = transportation_expenses;
        this.days = days;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTransportation_expenses() {
        return transportation_expenses;
    }

    public void setTransportation_expenses(int transportation_expenses) {
        this.transportation_expenses = transportation_expenses;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    public int getTotal(){
        return transportation_expenses + DAILY_ALLOWANCE_RATE * days;
    }
    public void show(){
        System.out.println("rate = " + DAILY_ALLOWANCE_RATE + " account = " + account + " transport = " + transportation_expenses +
                " days = " + days + " total = " + getTotal());
    }

    @Override
    public String toString() {
        return DAILY_ALLOWANCE_RATE + ";" + account + ';' + transportation_expenses + ";" + days + ";" +  getTotal();
    }
}