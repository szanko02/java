package by.gsu.pms.practice2.inh.task2;

import java.util.Arrays;
import java.util.Comparator;

public class Runner {
    public static void main(String[] args) {
        Commodity commodity = new Commodity("Commodity A", 300.0);
        Purchase[] purchases = {
            new PercentageDiscountPurchase(commodity, 1, 20.0),
            new PercentageDiscountPurchase(commodity, 5, 20.0),
            new PriceDiscountPurchase(commodity, 1, 100.0),
            new PriceDiscountPurchase(commodity, 5, 100.0),
            new TransportExpensePurchase(commodity, 1, 50.0),
            new TransportExpensePurchase(commodity, 5, 50.0),
        };
        
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
        
        Purchase min = null;
        double total = 0.0;
        Arrays.sort(purchases, Comparator.reverseOrder());
        
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
            if (min == null || min.compareTo(purchase) > 0) {
                min = purchase;
            }
            total += purchase.getCost();
        }
        
        System.out.println("Min: " + min);
        System.out.println("Total: " + total);
    }
}
