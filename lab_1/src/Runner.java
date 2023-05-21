import by.gsu.pms.Business;


public class Runner {
    public static void main(String[] args) {
        int duration;
        int sum_total = 0;
        int max_total = 0;
        String max_total_account_name = "";
        Business[] anArray = new Business[7];
        anArray[0] = new Business("Anton Slutsky", 50000, 5);
        anArray[1] = new Business("Ivan Ivanov", 35000, 3);
        anArray[2] = null;
        anArray[3] = new Business("Stanislav Ivanov", 35000, 2);
        anArray[4] = new Business("Sergei Ivanov", 45000, 7);
        anArray[5] = new Business("Denis Ivanov", 55000, 4);
        anArray[6] = new Business();
        anArray[6].setTransportation_expenses(10);
        for (Business account : anArray) {
            if (account != null) {
                account.show();
            }
        }
        duration = anArray[0].getDays() + anArray[1].getDays();
        for (Business element: anArray) {
            System.out.println(element);
        }
        for (Business expense : anArray){
            if (expense != null) {
                sum_total += expense.getTotal();
            }
        }
        for (int i = 0; i < anArray.length; i++){
            if (anArray[i] != null) {
                if(anArray[i].getTotal() > max_total){
                    max_total = anArray[i].getTotal();
                    max_total_account_name = anArray[i].getAccount();
                }
            }
        }
        System.out.println("Duration = " + duration);
        System.out.println(sum_total);
        System.out.println(max_total_account_name);



    }

}
