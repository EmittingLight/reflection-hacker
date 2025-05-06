import java.lang.reflect.Field;

public class BankHacker {
    public static void main(String[] args) throws Exception {
        BankAccount account = new BankAccount();

        System.out.println("🏦 До взлома:");
        account.printBalance();

        account.withdraw(1200); // не должно сработать

        // Хакер в деле:
        Field balanceField = BankAccount.class.getDeclaredField("balance");
        balanceField.setAccessible(true);
        balanceField.set(account, 999999.99);

        System.out.println("\n💻 После читерства:");
        account.printBalance();

        account.withdraw(50000); // теперь спокойно снимает
    }
}

