import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calorieCalculator extends JFrame {
    private JTextField weightfield, heightfield, AgeText;
    private JLabel resultLabel;
    private JRadioButton male, female;
    private Image backgroundImage;

    public static double calculate_BMR(String gender, double weight, double height, int age) {
        if (gender.equalsIgnoreCase("male")) {
            return 88.36 + (13.4 * weight) + (4.8 * height) + (5.7 * age);
        } else if (gender.equalsIgnoreCase("female")) {
            return 447.6 + (9.2 * weight) + (3.1 * height) + (4.3 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender. Use 'male' or 'female'.");
        }
    }

    
    public calorieCalculator() {
    	
        backgroundImage = new ImageIcon(getClass().getResource("/gym_bg5.jpg")).getImage();
        
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setTitle("BMR Calculator");
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("BMR CALCULATOR");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 30));
        // lblNewLabel.setForeground()
        lblNewLabel.setBounds(166, 37, 340, 60);
        getContentPane().add(lblNewLabel);
        
        JLabel height_label = new JLabel("Height (cm) :");
        height_label.setForeground(new Color(255, 255, 255));
        height_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        height_label.setBounds(87, 108, 100, 30);
        getContentPane().add(height_label);
        
        heightfield = new JTextField();
        heightfield.setBounds(180, 110, 100, 30);
        getContentPane().add(heightfield);
        
        JLabel weight_label = new JLabel("Weight (kg) :");
        weight_label.setForeground(new Color(255, 255, 255));
        weight_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        weight_label.setBounds(290, 108, 100, 30);
        getContentPane().add(weight_label);
        
        weightfield = new JTextField();
        weightfield.setBounds(389, 110, 100, 30);
        getContentPane().add(weightfield);
        
        JLabel AgeLabel = new JLabel("Age :");
        AgeLabel.setForeground(new Color(255, 255, 255));
        AgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        AgeLabel.setBounds(87, 179, 62, 30);
        getContentPane().add(AgeLabel);
        
        AgeText = new JTextField();
        AgeText.setBounds(180, 181, 100, 30);
        getContentPane().add(AgeText);
        AgeText.setColumns(10);

        JLabel GenderLabel = new JLabel("Gender:");
        GenderLabel.setForeground(new Color(255, 255, 255));
        GenderLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        GenderLabel.setBounds(89, 245, 70, 40);
        getContentPane().add(GenderLabel);
        
        male = new JRadioButton("Male");
        male.setBounds(180, 247, 70, 40);
        female = new JRadioButton("Female");
        female.setBounds(253, 247, 107, 40);
        
        ButtonGroup g1 = new ButtonGroup();
        g1.add(male);
        g1.add(female);
        
        getContentPane().add(male);
        getContentPane().add(female);
        
        JButton CalculateButton = new JButton("Calculate");
        CalculateButton.setForeground(Color.DARK_GRAY);
        CalculateButton.setBackground(Color.CYAN);
        CalculateButton.setBounds(87, 314, 100, 46);
        getContentPane().add(CalculateButton);
        
        resultLabel = new JLabel("");
        resultLabel.setForeground(new Color(255, 255, 255));
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resultLabel.setBounds(30, 371, 527, 267);
        getContentPane().add(resultLabel);
        
        CalculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculateBMR();
            }
        });
        
      //adding a back to home button to go back to the main menu
        JButton backButton = new JButton("Back to Home");
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setForeground(new Color(0, 0, 0));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                new HomePage(); // Open main menu
            }
        });
        backButton.setBounds(240, 314, 150, 46);
        getContentPane().add(backButton, BorderLayout.SOUTH);
      
        
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void CalculateBMR() {
        try {
            double weight = Double.parseDouble(weightfield.getText());
            double height = Double.parseDouble(heightfield.getText());
            int age = Integer.parseInt(AgeText.getText());
            
            String gender;
            if (male.isSelected()) {
                gender = "male";
            } else if (female.isSelected()) {
                gender = "female";
            } else {
                JOptionPane.showMessageDialog(this, "Please select a gender!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double bmr_val = calculate_BMR(gender, weight, height, age);
            
            //resultLabel.setText("Your BMR is: " + bmr_val + " kcal/day\n"	);
            String resultText = "<html>Your BMR is: " + (int)bmr_val + " kcal/day<br><br>" +
            "<b>Daily calorie needs based on activity level:</b><br>" +
            "Sedentary (little or no exercise): " + (int)(bmr_val * 1.2) + " kcal/day<br>" +
            "Exercise 1-3 times/week: " + (int)(bmr_val * 1.3) + " kcal/day<br>" +
            "Exercise 4-5 times/week: " + (int)(bmr_val * 1.4) + " kcal/day<br>" +
            "Daily exercise or intense exercise 3-4 times/week: " + (int)(bmr_val * 1.5) + " kcal/day<br>" +
            "Intense exercise 6-7 times/week: " + (int)(bmr_val * 1.7) + " kcal/day<br>" +
            "Very intense exercise daily, or physical job: " + (int)(bmr_val * 1.9) + " kcal/day</html>";
            
            resultLabel.setText(resultText);
            
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new bmrGui();
    }
}


