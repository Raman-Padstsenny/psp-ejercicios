import java.util.concurrent.ThreadLocalRandom;

public class PrimeTask implements Runnable {
    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int limit = random.nextInt(1, 101);
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": Displaying primes up to " + limit);
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                System.out.println(threadName + ": " + number);
                try {
                    Thread.sleep(random.nextLong(500, 1001));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
