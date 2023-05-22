package by.gsu.pms.practice2.inh.task3.iteration1;

public class Runner {
    public static void main(String[] args) {
        Employee[] employees = {
            new Worker(),
            new Manager(),
            new Director(),
        };
        
        for (Employee employee : employees) {
            System.out.println(employee.getPay());
        }
    }
}
