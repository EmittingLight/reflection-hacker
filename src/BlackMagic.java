import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlackMagic {
    public static void main(String[] args) throws Exception {
        System.out.println("🧪 До вмешательства:");
        MagicConstants.printSecret();

        // Достаём поле
        Field secretField = MagicConstants.class.getDeclaredField("SECRET");
        secretField.setAccessible(true);

        // Снимаем final
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(secretField, secretField.getModifiers() & ~Modifier.FINAL);

        // Меняем значение
        secretField.set(null, "Ха-ха, всё можно");

        System.out.println("😈 После вмешательства:");
        MagicConstants.printSecret();
    }
}

