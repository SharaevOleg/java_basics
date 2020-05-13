package Transactions.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private ReadWriteLock accountsLock = new ReentrantReadWriteLock();
    public final long MAX_AMOUNT_FOR_SIMPLE_TRANSACTION = 50000L;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws Exception
    {

        if(amount < 0)
            throw new Exception();

        if(amount == 0)
            return;

        Account[] bothAccounts = getAccounts(fromAccountNum , toAccountNum);
        Account fromAccount = bothAccounts[0];
        Account toAccount = bothAccounts[1];

        if(fromAccount == null || toAccount == null)
            throw new IllegalArgumentException();

        synchronized (toAccount) {
            if(toAccount.isLocked())
                throw new Exception();
        }

        boolean needRecedeMoney = false;
        try {

            synchronized (fromAccount) {
                if (fromAccount.isLocked())
                    throw new Exception();
                if(fromAccount.getMoney() < amount)
                    throw new Exception();
                fromAccount.addMoney(-amount);
                needRecedeMoney = true;
            }

            if(amount > MAX_AMOUNT_FOR_SIMPLE_TRANSACTION
                    && isFraud(fromAccountNum, toAccountNum, amount)) {
                synchronized (fromAccount) {
                    fromAccount.setLocked(true);
                    fromAccount.addMoney(amount);
                    needRecedeMoney = false;
                }
                synchronized (toAccount) {
                    toAccount.setLocked(true);
                }
                throw new Exception();
            }

            synchronized (toAccount) {
                toAccount.addMoney(amount);
                needRecedeMoney = false;
            }

        } finally {
            if(needRecedeMoney) {
                synchronized (fromAccount) {
                    fromAccount.addMoney(amount);
                    needRecedeMoney = false;
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = getAccounts(accountNum)[0];

        if(account == null)
            throw new IllegalArgumentException();

        synchronized (account) {
            return account.getMoney();
        }
    }
    private Account[] getAccounts(String... accountNums) {
        Account[] accounts = new Account[accountNums.length];
        accountsLock.readLock().lock();
        try {
            for(int i = 0; i < accountNums.length; i++)
                accounts[i] = this.accounts.get(accountNums[i]);
        } finally {
            accountsLock.readLock().unlock();
        }
        return accounts;
    }

    public void generateAccounts(int count, Random random) {
        int maxAmount = 1_000_000;
        accountsLock.writeLock().lock();
        try {
            while(count > 0) {
                String number = ("" + random.nextLong()).replace("-", "");
                if(accounts.containsKey(number)) {
                    continue;
                }
                accounts.put(number, new Account(random.nextInt(maxAmount), number));
                count -- ;
            }
        } finally {
            accountsLock.writeLock().unlock();
        }
    }

    public List<Transaction> generateTransactions(int count, Random random) {
        int maxAmount = 100_000;
        String[] accounts = null;
        List<Transaction> list = new ArrayList<>();
        accountsLock.readLock().lock();
        try {
            accounts = this.accounts.keySet().toArray(new String[this.accounts.size()]);
        } finally {
            accountsLock.readLock().unlock();
        }
        while(count > 0) {
            int i = random.nextInt(accounts.length);
            int j = random.nextInt(accounts.length);
            while(i == j) {
                j = random.nextInt(accounts.length);
            }
            long amount = random.nextInt(maxAmount);

            list.add(new Transaction(this, accounts[i], accounts[j], amount));
            count--;
        }
        return list;
    }



}
