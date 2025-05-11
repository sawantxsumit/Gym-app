import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietChart {
    private JFrame mainFrame;
    private JTextField ageField, calorieField;
    private JComboBox<String> genderBox, goalBox;
    private Image backgroundImage;

    public DietChart() {
        mainFrame = new JFrame("Diet Chart");
        mainFrame.setSize(700, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Load background image
        backgroundImage = new ImageIcon(getClass().getResource("/gym_bg13.jpg")).getImage();

        // Create a custom panel that paints the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Set layout to manually position components

        // Adding components
        JLabel titleLabel = new JLabel("DIET CHART", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBounds(237, 29, 200, 40);
        backgroundPanel.add(titleLabel);

        JLabel label_1 = new JLabel("Age:");
        label_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_1.setBounds(50, 121, 100, 30);
        backgroundPanel.add(label_1);

        ageField = new JTextField();
        ageField.setBounds(287, 123, 150, 30);
        backgroundPanel.add(ageField);

        JLabel label_2 = new JLabel("Gender:");
        label_2.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_2.setBounds(50, 164, 100, 30);
        backgroundPanel.add(label_2);

        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderBox.setFont(new Font("Rockwell", Font.PLAIN, 11));
        genderBox.setBounds(287, 164, 150, 30);
        backgroundPanel.add(genderBox);

        JLabel lblDailyCalorieRequirement = new JLabel("Daily Calorie requirement :");
        lblDailyCalorieRequirement.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblDailyCalorieRequirement.setBounds(50, 218, 210, 30);
        backgroundPanel.add(lblDailyCalorieRequirement);

        calorieField = new JTextField();
        calorieField.setBounds(287, 220, 150, 30);
        backgroundPanel.add(calorieField);

        JLabel label_4 = new JLabel("Fitness Goal:");
        label_4.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_4.setBounds(50, 259, 100, 30);
        backgroundPanel.add(label_4);

        goalBox = new JComboBox<>(new String[]{"Weight Loss", "Maintain Weight", "Weight Gain"});
        goalBox.setFont(new Font("Rockwell", Font.PLAIN, 11));
        goalBox.setBounds(287, 261, 150, 30);
        backgroundPanel.add(goalBox);

        JButton generateButton = new JButton("Generate Diet Plan");
        generateButton.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        generateButton.setBounds(225, 339, 200, 40);
        backgroundPanel.add(generateButton);

        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        backButton.setBounds(225, 402, 200, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backgroundPanel.add(backButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateDietChart();
            }
        });
        backButton.addActionListener(e -> {
            if (mainFrame == null) {
                System.out.println("mainFrame is NULL!");
            } else {
                System.out.println("Closing DietChart window...");
                mainFrame.dispose();
            }
            new HomePage();
        });


        // Set the custom panel as the content pane
        mainFrame.setContentPane(backgroundPanel);

        mainFrame.setVisible(true);
    }

    private void generateDietChart() {
        String gender = (String) genderBox.getSelectedItem();
        String goal = (String) goalBox.getSelectedItem();
        int age, calories;

        try {
            age = Integer.parseInt(ageField.getText());
            calories = Integer.parseInt(calorieField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter valid age and calorie values!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[][] meals = {
                {"Oatmeal + Fruits", "Grilled Chicken Salad", "Boiled Eggs + Nuts", "Grilled Fish + Vegetables"},
                {"Scrambled Eggs + Whole Wheat Toast", "Quinoa + Veggies", "Protein Shake + Nuts", "Grilled Chicken + Salad"},
                {"Greek Yogurt + Berries", "Brown Rice + Chicken", "Fruit Salad", "Lentil Soup + Stir-fried Veggies"},
                {"Smoothie + Boiled Eggs", "Grilled Paneer + Rice", "Handful of Almonds", "Steamed Fish + Salad"},
                {"Oats Pancake", "Chickpea Salad", "Cottage Cheese + Fruits", "Veggie Soup + Whole Wheat Bread"},
                {"Boiled Eggs + Avocado", "Vegetable Stir Fry", "Handful of Walnuts", "Grilled Tofu + Rice"},
                {"Fruit Smoothie + Nuts", "Lentils + Brown Rice", "Greek Yogurt", "Roasted Chicken + Vegetables"}
        };

        StringBuilder dietPlan = new StringBuilder();
        dietPlan.append("Diet Plan for ").append(goal).append("\n\n");

        for (int i = 0; i < 7; i++) {
            dietPlan.append("Day ").append(i + 1).append(":\n")
                    .append("Breakfast: ").append(meals[i][0]).append("\n")
                    .append("Lunch: ").append(meals[i][1]).append("\n")
                    .append("Evening Snack: ").append(meals[i][2]).append("\n")
                    .append("Dinner: ").append(meals[i][3]).append("\n\n");
        }

        showDietChartWindow(dietPlan.toString());
    }

    private void showDietChartWindow(String dietChart) {
        JFrame dietFrame = new JFrame("Your Diet Plan");
        dietFrame.setSize(700, 700);
        dietFrame.getContentPane().setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(dietChart);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        dietFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> dietFrame.dispose());
        dietFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);

        dietFrame.setVisible(true);
        dietFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new DietChart();
    }
}
