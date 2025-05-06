public class BankAccount {
    private double balance = 1000.0;

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Недостаточно средств для снятия: " + amount);
        } else {
            balance -= amount;
            System.out.println("✅ Снято: " + amount);
        }
    }

    public void printBalance() {
        System.out.println("💰 Баланс: " + balance);
    }
}

