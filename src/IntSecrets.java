public class IntSecrets {
    public static int NUMBER = 42;
    public static boolean FLAG = true;

    public static void printSecrets(String prefix) {
        System.out.println(prefix);
        System.out.println("\uD83D\uDD22 Число: " + NUMBER);
        System.out.println("\uD83D\uDD18 Флаг: " + FLAG);
    }
}


