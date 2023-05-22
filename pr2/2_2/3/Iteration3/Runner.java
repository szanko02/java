package by.gsu.pms.practice2.inh.task3.iteration3;

public class Runner {
    public static void main(String[] args) {
        Employee[] employees = {
            new Locksmith(),
            new Turner(),
            new Manager(),
            new Director(),
        };
    
        for (Employee employee : employees) {
            //noinspection ConstantValue
            if (employee instanceof Descriptor) {
                ((Descriptor)employee).printInfo(System.out);
            } else {
                System.out.println("Unknown employee");
            }
        }
    }
}
