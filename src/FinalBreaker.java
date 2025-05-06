import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

public class FinalBreaker {
    public static void main(String[] args) throws Throwable {
        System.out.println("🧪 До взлома:");
        MagicConstants.printSecret();

        // Получаем приватное поле
        Field field = MagicConstants.class.getDeclaredField("SECRET");
        field.setAccessible(true);

        // Получаем VarHandle для поля
        MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(MagicConstants.class, MethodHandles.lookup());
        VarHandle handle = lookup.findStaticVarHandle(MagicConstants.class, "SECRET", String.class);

        // Меняем значение final-поля
        handle.set("Взломано через VarHandle");

        System.out.println("😈 После взлома:");
        MagicConstants.printSecret();
    }
}

