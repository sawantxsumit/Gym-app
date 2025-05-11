import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HomePage extends JFrame {

    private Image backgroundImage;

    public HomePage() {
        backgroundImage = new ImageIcon(getClass().getResource("/gym_bg18.jpg")).getImage();

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });

        getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Fitness Station");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(new Color(128, 255, 0));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(176, 189, 317, 84);

        JButton bmiButton = new JButton("BMI Calculator");
        JButton calorieButton = new JButton("Calorie Calculator");
        calorieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        JButton dietPlanButton = new JButton("Workout Plans");
        JButton dietChartButton = new JButton("Diet Chart");

        bmiButton.setBounds(259, 313, 139, 50);
        calorieButton.setBounds(259, 388, 141, 50);
        dietPlanButton.setBounds(261, 462, 139, 50);
        dietChartButton.setBounds(261, 536, 139, 50);

        getContentPane().add(bmiButton);
        getContentPane().add(calorieButton);
        getContentPane().add(dietChartButton);
        getContentPane().add(dietPlanButton);
        getContentPane().add(lblNewLabel_1);

        bmiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new bmiGui();
            }
        });

        calorieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new calorieCalculator();
            }
        });
        dietChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DietChart();
            }
        });
        
        dietPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new workout_planner();
            }
        });
//
//        JLabel lblNewLabel_2 = new JLabel("\" Discipline is stronger than motivation \"");
//        lblNewLabel_2.setBackground(new Color(255, 255, 255));
//        lblNewLabel_2.setForeground(new Color(0, 0, 0));
//        lblNewLabel_2.setFont(new Font("Serif", Font.ITALIC, 25));
//        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
//        lblNewLabel_2.setBounds(90, 286, 517, 66);
//        getContentPane().add(lblNewLabel_2);
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
