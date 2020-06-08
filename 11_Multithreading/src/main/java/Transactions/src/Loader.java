package Transactions.src;

import java.util.concurrent.*;

public class Loader {

    private final static int THREADS = 10, THREAD_COUNT = 100; //- колво потоков;  - колво акков в банке
    private final static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        System.out.println("Balance - " + bank.getTotalBalance());
        ExecutorService service = Executors.newFixedThreadPool(THREADS);
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            service.execute(() -> {
                try {
                    for (int j = 1; j < 100; j++) {
                        int amount = (int) (10000 + 45000 * Math.random());
                        bank.transfer(j, j + 1, amount);
                        if (j == 99) {
                            break;
                        }
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        service.shutdown();
        System.out
                .println((System.currentTimeMillis() - start));
        System.out.println("NEW balance - " + bank.getTotalBalance());
    }
}