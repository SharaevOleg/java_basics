package Transactions.src;

public class Transaction {

    private Bank bank;
    private String fromAccountNum;
    private String toAccountNum;
    private long amount;

    public Transaction(Bank bank, String fromAccountNum, String toAccountNum, long amount){
        this.bank = bank;
        this.fromAccountNum = fromAccountNum;
        this.toAccountNum = toAccountNum;
        this.amount = amount;
    }

    public void run() throws Exception
    {
        bank.transfer(fromAccountNum, toAccountNum, amount);
    }
}

