package week1.ex2;

class BankAccount {
    private double balance = 0;

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Simulating multiple threads
        Thread t1 = new Thread(() -> {
            account.deposit(1000);
            System.out.println("Deposited 1000. Balance: " + account.getBalance());
        });

        Thread t2 = new Thread(() -> {
            account.withdraw(500);
            System.out.println("Withdrew 500. Balance: " + account.getBalance());
        });

        t1.start();
        t2.start();
    }
}
