import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeFinalHack {
    public static void main(String[] args) throws Exception {
        System.out.println("🧪 До вмешательства:");
        MagicConstants.printSecret();

        // Получаем Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        // Получаем поле
        Field secretField = MagicConstants.class.getDeclaredField("SECRET");
        secretField.setAccessible(true);

        // Получаем смещение
        Object staticFieldBase = unsafe.staticFieldBase(secretField);
        long offset = unsafe.staticFieldOffset(secretField);

        // Меняем значение
        unsafe.putObject(staticFieldBase, offset, "Взлом через Unsafe");

        System.out.println("😈 После вмешательства:");
        MagicConstants.printSecret();
    }
}
