package by.gsu.pms.practice2.inh.task3.iteration3;

import java.io.PrintStream;

public abstract class Worker implements Descriptor, Employee {
    @Override
    public void printInfo(PrintStream stream) {
        stream.println(getKind() + "(" + getPay() + ")");
    }
    
    @Override
    public abstract double getPay();
    
    public abstract String getKind();
}
