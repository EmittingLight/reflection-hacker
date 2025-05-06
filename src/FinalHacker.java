
import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class FinalHacker {
    public static void main(String[] args) throws Exception {
        // Получаем Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        // Получаем поля
        Field numberField = FinalSecrets.class.getDeclaredField("NUMBER");
        numberField.setAccessible(true);

        Field flagField = FinalSecrets.class.getDeclaredField("FLAG");
        flagField.setAccessible(true);

        // 🧪 До вмешательства
        System.out.println("🧪 До взлома:");
        System.out.println("🔢 Число: " + numberField.getInt(null));
        System.out.println("🔘 Флаг: " + flagField.getBoolean(null));

        // Взламываем через Unsafe
        Object baseInt = unsafe.staticFieldBase(numberField);
        long offsetInt = unsafe.staticFieldOffset(numberField);
        unsafe.putInt(baseInt, offsetInt, 999);

        Object baseBool = unsafe.staticFieldBase(flagField);
        long offsetBool = unsafe.staticFieldOffset(flagField);
        unsafe.putBoolean(baseBool, offsetBool, false);

        // 😈 После вмешательства
        System.out.println("\n😈 После взлома:");
        System.out.println("🔢 Число: " + numberField.getInt(null));
        System.out.println("🔘 Флаг: " + flagField.getBoolean(null));
    }
}
