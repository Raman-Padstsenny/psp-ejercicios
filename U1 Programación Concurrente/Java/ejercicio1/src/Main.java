import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String implementation = args.length == 0 ? "runnable" : args[0];
        if (args.length > 1 || (!implementation.equals("runnable")
                && !implementation.equals("anonymous") && !implementation.equals("lambda"))) {
            throw new IllegalArgumentException("Usage: java Main [runnable|anonymous|lambda]");
        }

        int n1;
        int n2;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter n1: ");
            n1 = scanner.nextInt();
            System.out.print("Enter n2: ");
            n2 = scanner.nextInt();
        }

        Runnable task;
        switch (implementation) {
            case "anonymous":
                task = new Runnable() {
                    @Override
                    public void run() {
                        printNumbers(n1, n2);
                    }
                };
                break;
            case "lambda":
                task = () -> printNumbers(n1, n2);
                break;
            default:
                task = new NumberPrinter(n1, n2);
                break;
        }

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("The thread has been started");
    }

    private static void printNumbers(int n1, int n2) {
        int step = n1 <= n2 ? 1 : -1;
        for (long number = n1; ; number += step) {
            System.out.println(number);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 1001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (number == n2) {
                break;
            }
        }
    }

    private static class NumberPrinter implements Runnable {
        private final int n1;
        private final int n2;

        NumberPrinter(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        @Override
        public void run() {
            printNumbers(n1, n2);
        }
    }
}
