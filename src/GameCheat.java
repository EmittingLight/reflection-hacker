import java.lang.reflect.Field;

public class GameCheat {
    public static void main(String[] args) throws Exception {
        GameCharacter hero = new GameCharacter();

        System.out.println("🎮 До читов:");
        hero.printStats();

        // Получаем доступ к приватным полям
        Field healthField = GameCharacter.class.getDeclaredField("health");
        Field manaField = GameCharacter.class.getDeclaredField("mana");

        healthField.setAccessible(true);
        manaField.setAccessible(true);

        // Применяем читы
        healthField.set(hero, 999);
        manaField.set(hero, 999);

        System.out.println("🛠️  После применения читов:");
        hero.printStats();

        // А теперь получим урон
        hero.takeDamage(100);

        System.out.println("🎮 После боя:");
        hero.printStats();
    }
}

