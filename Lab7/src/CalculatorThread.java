public class CalculatorThread extends Thread {
    private final int start;
    private final int end;

    private boolean numbersPrinted;
    public CalculatorThread(int start, int end) {
        this.start = start;
        this.end = end;
        this.numbersPrinted = false;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (i % 3 == 1) {
                synchronized (System.out) {
                    System.out.print(i);
                    if (i != end) {
                        System.out.print(", ");
                    }
                }
            }
        }
        numbersPrinted = true;
    }
}