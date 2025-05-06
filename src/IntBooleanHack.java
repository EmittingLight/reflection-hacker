import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class IntBooleanHack {
    public static void main(String[] args) throws Exception {
        IntSecrets.printSecrets("\uD83E\uDE93 До взлома:");

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Field numberField = IntSecrets.class.getDeclaredField("NUMBER");
        Field flagField = IntSecrets.class.getDeclaredField("FLAG");

        Object staticBaseNumber = unsafe.staticFieldBase(numberField);
        long offsetNumber = unsafe.staticFieldOffset(numberField);
        unsafe.putInt(staticBaseNumber, offsetNumber, 999);

        Object staticBaseFlag = unsafe.staticFieldBase(flagField);
        long offsetFlag = unsafe.staticFieldOffset(flagField);
        unsafe.putBoolean(staticBaseFlag, offsetFlag, false);

        IntSecrets.printSecrets("\uD83D\uDE08 После взлома:");
    }
}
