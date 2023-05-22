package by.gsu.pms.practice2.inh.task3.iteration2;

import java.io.PrintStream;

public class Worker implements Descriptor, Employee {
    @Override
    public void printInfo(PrintStream stream) {
        stream.println("Worker(" + getPay() + ")");
    }
    
    @Override
    public double getPay() {
        return 100.0;
    }
}
