import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JLabel dobLabel, resultLabel;
    private JTextField dobField;
    private JButton showButton, aboutButton;

    public Main() {
        setTitle("Знак Зодіаку");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        dobLabel = new JLabel("Введіть свій день народження (ММ/ДД):");
        dobField = new JTextField(10);
        showButton = new JButton("Показати знак Зодіаку");
        resultLabel = new JLabel("");
        aboutButton = new JButton("Про програму");

        add(dobLabel);
        add(dobField);
        add(showButton);
        add(resultLabel);
        add(aboutButton);

        showButton.addActionListener(this);
        aboutButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showButton) {
            String dob = dobField.getText();
            String zodiacSign = calculateZodiacSign(dob);
            if(zodiacSign == "")resultLabel.setText("Некоректний ввід, будь ласка, введіть свій день народження (ММ/ДД):");
            else resultLabel.setText("Ваш знак Зодіаку: " + zodiacSign);
        } else if (e.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "Знак Зодіаку\nВерсія 1.0\n\nАвтор: Макс Росул\nEmail: rosul.maksym@student.uzhnu.edu.ua", "Про програму", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String calculateZodiacSign(String dob) {
        // Розбиваємо рядок дати народження на складники
        String[] parts = dob.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        // Додаємо валідацію
        if(day>31)return "";
        if(month>12)return "";

        // Масив знаків Зодіаку у порядку місяців
        String[] signs = {"Козеріг", "Водолій", "Риби", "Овен", "Телець", "Близнюки",
                "Рак", "Лев", "Діва", "Терези", "Скорпіон", "Стрілець"};

        // Масив, що містить день, по якому змінюється знак Зодіаку
        int[] cutoffs = {20, 19, 20, 20, 21, 21, 22, 23, 23, 23, 22, 21};

        // Визначаємо знак Зодіаку
        if ((month == 1 && day <= cutoffs[0]) || (month == 12 && day >= cutoffs[11])) {
            return signs[0]; // Козеріг
        } else {
            for (int i = 1; i < cutoffs.length; i++) {
                if ((month == i + 1 && day <= cutoffs[i]) || (month == i && day >= cutoffs[i - 1])) {
                    return signs[i]; // інший знак Зодіаку
                }
            }
        }
        return ""; // Повертаємо порожню строку, якщо не вдалося визначити знак Зодіаку
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main app = new Main();
                app.setVisible(true);
            }
        });
    }
}
