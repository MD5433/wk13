import java.util.concurrent.Semaphore;
//Mariah Dungey 04/21/2025
public class Main {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount(100);
        Semaphore sem = new Semaphore(1, true);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    try {
                        sem.acquire();
                        synchronized (account) {
                            if (account.getBalance() > 0) {
                                System.out.println(name + " tries to withdraw $10, balance: " +
                                        account.withdraw(10));
                            }
                        }
                        sem.release();
                    } catch (InterruptedException e) {}
                }
            }
        };

        //threads
        Thread thdHusband = new Thread(r);
        thdHusband.setName("Husband");
        Thread thdWife = new Thread(r);
        thdWife.setName("Wife");

        thdHusband.start();
        thdWife.start();
    }
}

class CheckingAccount {
    private int balance;

    public CheckingAccount(int initialBalance) {
        balance = initialBalance;
    }

    public int withdraw(int amount) {
        if (amount <= balance) {
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException ie) {}

            balance -= amount;
        }
        return balance;
    }

    public int getBalance() {
        return balance;
    }
}
