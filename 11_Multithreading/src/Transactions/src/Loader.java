package Transactions.src;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Loader {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Bank bank = new Bank();
        AtomicInteger counter = new AtomicInteger(0);
        List<Future<?>> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int j = i * 2;
            counter.incrementAndGet();
            tasks.add(executorService.submit(() -> {
                Random random = new Random(j);
                bank.generateAccounts(10, random);
                counter.decrementAndGet();
            }));
            int k = j + 1;
            counter.incrementAndGet();
            tasks.add(executorService.submit(() -> {
                Random random = new Random(k);
                for (Transaction transaction : bank.generateTransactions(20, random)) {
                    counter.incrementAndGet();
                    executorService.submit(() -> {
                        try {
                            transaction.run();
                            synchronized (System.out) {
                                System.out.println("OK");
                            }
                        } catch (Exception e) {
                            synchronized (System.out) {
                                System.out.println(e.getClass().getSimpleName());
                            }
                        }
                        counter.decrementAndGet();
                    });
                }
                counter.decrementAndGet();
            }));
        }

        while (counter.get() > 0) {
            Thread.sleep(1000);
        }

        executorService.shutdown();

        System.out.println("END TEST");

    }
}

