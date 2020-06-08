package Transactions.src;

import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;


public class Test extends TestCase {

    private Bank bank;
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private Account a1, a2, a3, a4, a5;

    @Override
    @After
    public void setUp() {

        bank = new Bank();
        a1 = new Account(50000, 10);
        a2 = new Account(50000, 20);
        a3 = new Account(50000, 30);
        a4 = new Account(60000, 40);
        a5 = new Account(60000, 50);
        accounts.put(1, a1);
        accounts.put(2, a2);
        accounts.put(3, a3);
        accounts.put(4, a4);
        accounts.put(5, a5);

        bank.setAccounts(accounts);

    }

    @Before
    public void clearBank() {
        bank = null;
    }

    public void testTransferBlock() throws InterruptedException {
        long balance = a1.getBalance();
        a1.setIsBlocked(true);
        bank.transfer(1, 2, 1000);
        bank.transfer(1, 2, 1000);
        bank.transfer(1, 2, 1000);
        long actualA1 = bank.getBalance(1);

        assertEquals(balance, actualA1);
    }

    public void testIfFraud() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                try {
                    bank.transfer(4, 5, 51000);
                    bank.transfer(5, 4, 51000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();
        }
        boolean actualFrom = a4.isBlocked();
        boolean actualTo = a5.isBlocked();

        assertTrue(actualFrom);
        assertTrue(actualTo);
    }

    public void testDoubleWithdrawal() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Thread t = new Thread(() -> {
                try {
                    bank.transfer(1, 2, 9999);
                    Thread.sleep(100);
                    bank.transfer(2, 3, 8888);
                    Thread.sleep(100);
                    bank.transfer(3, 4, 7777);
                    Thread.sleep(100);
                    bank.transfer(4, 5, 6666);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();

            long expected = a1.getBalance();
            long actual = 10;
            assertEquals(expected,actual);
        }
    }

}
