public class GameCharacter {
    private int health = 100;
    private int mana = 50;

    public void printStats() {
        System.out.println("💓 Здоровье: " + health + ", 🔮 Мана: " + mana);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("💥 Персонаж получил " + damage + " урона.");
    }
}

