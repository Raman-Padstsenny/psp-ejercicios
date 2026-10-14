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

            Thread[] threads = new Thread[numberOfThreads];
            for (int i = 1; i <= numberOfThreads; i++) {
                Thread thread = new Thread(new PrimeTask(), "Thread " + i);
                threads[i - 1] = thread;
                thread.start();
            }

            boolean threadsRunning;
            do {
                threadsRunning = false;
                for (Thread thread : threads) {
                    Thread.State state = thread.getState();
                    System.out.println(thread.threadId() + " " + thread.getName() + " " + state);
                    if (state != Thread.State.TERMINATED) {
                        threadsRunning = true;
                    }
                }

                if (threadsRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Thread monitoring interrupted.");
                        return;
                    }
                }
            } while (threadsRunning);
        }
    }
}
