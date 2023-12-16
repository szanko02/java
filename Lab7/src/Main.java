import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число a: ");
        int a = scanner.nextInt();
        System.out.println("Введите количество потоков: ");
        int threadCount = scanner.nextInt();
        int numbersPerThread = a / threadCount;
        int remainingNumbers = a % threadCount;
        int start = 1;
        int end;
        for (int i = 0; i < threadCount; i++) {
            end = start + numbersPerThread - 1;
            if (i < remainingNumbers){
                end++;
            }
            CalculatorThread thread = new CalculatorThread(start, end);
            thread.start();
            start = end + 1;
        }
    }
        }
