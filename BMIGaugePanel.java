import javax.swing.*;
import java.awt.*;

public class BMIGaugePanel extends JPanel {
    private double bmi = 25.1; // Example BMI value

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Anti-aliasing for smoother graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int centerX = getWidth() / 2;
        int centerY = getHeight() - 50;
        int radius = 100;
        
        // Draw Gauge Arc
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.GREEN);
        g2.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 180, 45); // Normal
        
        g2.setColor(Color.YELLOW);
        g2.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 225, 45); // Overweight
        
        g2.setColor(Color.RED);
        g2.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 270, 90); // Obesity
        
        // Draw BMI Pointer
        double angle = 180 + ((bmi - 18.5) / (40 - 18.5)) * 180;
        double radian = Math.toRadians(angle);
        int pointerX = centerX + (int) (radius * Math.cos(radian));
        int pointerY = centerY + (int) (radius * Math.sin(radian));
        
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        g2.drawLine(centerX, centerY, pointerX, pointerY);
        
        // Display BMI Value
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("BMI = " + bmi, centerX - 40, centerY + 40);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BMI Gauge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(new BMIGaugePanel());
        frame.setVisible(true);
    }
}

