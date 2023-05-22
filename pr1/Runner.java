package by.gsu.pms.practice1;

import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        String[] accountNames = {
            "Acc1",
            "Acc2",
            "Acc3",
            "Acc4",
            "Acc5",
            "Acc6",
            "Acc7",
        };
        Expense[] expenses = new Expense[7];
        Random random = new Random();
        for (int i = 0; i < expenses.length; i++) {
            if (i == 2) {
                continue;
            } else if (i == expenses.length - 1) {
                expenses[i] = new Expense();
            } else {
                expenses[i] =
                    new Expense(random.nextDouble() * 100.0,
                        accountNames[i],
                        random.nextDouble() * 500.0,
                        random.nextInt(32));
            }
        }
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] != null) {
                expenses[i].show();
            }
        }
        expenses[expenses.length - 1].setTransportationExpense(123.0);
        for (int i = 0; i < 2; i++) {
            if (expenses[i] != null) {
                System.out.println(expenses[i].getDayCount());
            }
        }
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] != null) {
                System.out.println(expenses[i].toString());
            }
        }
        double sum = 0.0;
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] != null) {
                sum += expenses[i].getTotal();
            }
        }
        System.out.println(sum);
        double max = 0.0;
        int maxi = -1;
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] != null) {
                double total = expenses[i].getTotal();
                if (total > max) {
                    max = total;
                    maxi = i;
                }
            }
        }
        if (maxi != -1) {
            System.out.println(expenses[maxi]);
        } else {
            System.out.println("No max");
        }
    }
}
