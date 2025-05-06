import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class IntBooleanHack {
    public static void main(String[] args) throws Exception {
        System.out.println("🧪 До взлома:");
        IntSecrets.printSecrets();

        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        Field numberField = IntSecrets.class.getDeclaredField("NUMBER");
        numberField.setAccessible(true);
        Object base1 = unsafe.staticFieldBase(numberField);
        long offset1 = unsafe.staticFieldOffset(numberField);
        unsafe.putInt(base1, offset1, 999);

        Field flagField = IntSecrets.class.getDeclaredField("FLAG");
        flagField.setAccessible(true);
        Object base2 = unsafe.staticFieldBase(flagField);
        long offset2 = unsafe.staticFieldOffset(flagField);
        unsafe.putBoolean(base2, offset2, false);

        System.out.println("😈 После взлома:");
        IntSecrets.printSecrets();
    }
}

