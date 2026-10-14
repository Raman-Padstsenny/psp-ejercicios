import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of threads to create: ");
            if (!scanner.hasNextInt()) {
                System.err.println("Please enter a positive integer.");
                return;
            }

            int numberOfThreads = scanner.nextInt();
            if (numberOfThreads <= 0) {
                System.err.println("Please enter a positive integer.");
                return;
            }

            for (int i = 1; i <= numberOfThreads; i++) {
                Thread thread = new Thread(new PrimeTask(), "Thread " + i);
                thread.start();
            }
        }
    }
}
