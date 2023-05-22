package by.gsu.pms.practice2.inh.task3.iteration2;

import java.io.PrintStream;

public class Manager implements Descriptor, Employee {
    @Override
    public void printInfo(PrintStream stream) {
        stream.println("Manager(" + getPay() + ")");
    }
    
    @Override
    public double getPay() {
        return 200.0;
    }
}
