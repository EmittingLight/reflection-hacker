import sun.misc.Unsafe;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;

public class FinalGuiHacker {
    private static JLabel numberLabel;
    private static JLabel flagLabel;
    private static JLabel stringLabel;
    private static JLabel doubleLabel;

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame frame = new JFrame("Final Field Hacker 🧙");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel displayPanel = new JPanel(new GridLayout(0, 1));
        numberLabel = new JLabel();
        flagLabel = new JLabel();
        stringLabel = new JLabel();
        doubleLabel = new JLabel();
        displayPanel.add(numberLabel);
        displayPanel.add(flagLabel);
        displayPanel.add(stringLabel);
        displayPanel.add(doubleLabel);

        JPanel hackPanel = new JPanel();
        JButton hackButton = new JButton("🔓 ВЗЛОМАТЬ FINAL");
        hackButton.setFont(hackButton.getFont().deriveFont(Font.BOLD, 16f));
        hackButton.setBackground(new Color(200, 230, 255));
        hackButton.setFocusPainted(false);

        hackButton.addActionListener(e -> {
            try {
                hackFinalFields();
                updateLabels("После взлома:");
                playSuccessSound();
                hackButton.setBackground(new Color(180, 255, 180));
                hackButton.setText("✅ ВЗЛОМ УДАЛСЯ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Ошибка при взломе: " + ex.getMessage());
            }
        });
        hackPanel.add(hackButton);

        tabbedPane.addTab("📊 Состояние", displayPanel);
        tabbedPane.addTab("💥 Взлом", hackPanel);

        frame.getContentPane().add(tabbedPane);

        updateLabels("До взлома:");
        frame.setVisible(true);
    }

    private static void updateLabels(String prefix) throws Exception {
        Field numberField = FinalSecrets.class.getDeclaredField("NUMBER");
        numberField.setAccessible(true);
        Field flagField = FinalSecrets.class.getDeclaredField("FLAG");
        flagField.setAccessible(true);
        Field stringField = FinalSecrets.class.getDeclaredField("TEXT");
        stringField.setAccessible(true);
        Field doubleField = FinalSecrets.class.getDeclaredField("DECIMAL");
        doubleField.setAccessible(true);

        int number = numberField.getInt(null);
        boolean flag = flagField.getBoolean(null);
        String text = (String) stringField.get(null);
        double dec = doubleField.getDouble(null);

        numberLabel.setText(prefix + "  🔢 Число: " + number);
        flagLabel.setText("               🔘 Флаг: " + flag);
        stringLabel.setText("               📄 Текст: " + text);
        doubleLabel.setText("               💧 Дробь: " + dec);
    }

    private static void hackFinalFields() throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Field numberField = FinalSecrets.class.getDeclaredField("NUMBER");
        numberField.setAccessible(true);
        Object baseInt = unsafe.staticFieldBase(numberField);
        long offsetInt = unsafe.staticFieldOffset(numberField);
        unsafe.putInt(baseInt, offsetInt, 999);

        Field flagField = FinalSecrets.class.getDeclaredField("FLAG");
        flagField.setAccessible(true);
        Object baseBool = unsafe.staticFieldBase(flagField);
        long offsetBool = unsafe.staticFieldOffset(flagField);
        unsafe.putBoolean(baseBool, offsetBool, false);

        Field stringField = FinalSecrets.class.getDeclaredField("TEXT");
        stringField.setAccessible(true);
        Object baseStr = unsafe.staticFieldBase(stringField);
        long offsetStr = unsafe.staticFieldOffset(stringField);
        unsafe.putObject(baseStr, offsetStr, "Взломано! 💣");

        Field doubleField = FinalSecrets.class.getDeclaredField("DECIMAL");
        doubleField.setAccessible(true);
        Object baseDbl = unsafe.staticFieldBase(doubleField);
        long offsetDbl = unsafe.staticFieldOffset(doubleField);
        unsafe.putDouble(baseDbl, offsetDbl, 13.37);
    }

    private static void playSuccessSound() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/mission-success-41211.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.err.println("Не удалось воспроизвести звук: " + e.getMessage());
        }
    }
}
