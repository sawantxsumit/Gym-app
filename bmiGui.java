
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class bmiGui extends JFrame{
  JTextField weightfield ,heightfield ;
  JLabel height_label , weight_label , resultLabel;
  JRadioButton male , female;
   JLabel lblNewLabel_1;
   JLabel lblNewLabel_2 , lblNewLabel_3;
   JTextField textField;
   private JTextArea txtrBmiIsA;
   private Image backgroundImage;

  public static double Calculate_BMI(double height, double weight) {
    double BMI = (weight) / (height * height)*100*100;
    return BMI;
}

public static String getCategory(double bmi_value)
{
   
    //calculating category

    if (bmi_value < 18.5) return "Underweight";
    else if (bmi_value < 24.9) return "Normal weight";
    else if (bmi_value < 29.9) return "Overweight";
    else return "Obese";

}

  public bmiGui()
  {
  	getContentPane().setBackground(Color.WHITE);
  	
  	backgroundImage = new ImageIcon(getClass().getResource("/gym_bg8.jpg")).getImage();
    
    setContentPane(new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    });
    
    setTitle("BMI calculator ");
    setSize(700, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);

    //adding components
    height_label= new JLabel("Height (cm) :");
    height_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
    getContentPane().add(height_label);
    height_label.setBounds(87, 200, 100, 30);
    heightfield = new JTextField();
    heightfield.setBounds(180 , 202 , 100 , 30);
    getContentPane().add(heightfield);
    
    weight_label= new JLabel("Weight (kg) :");
    weight_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
    getContentPane().add(weight_label);
    weight_label.setBounds(300, 200, 100, 30);
    weightfield = new JTextField();
    weightfield.setBounds(402 , 202 , 100 , 30);
    getContentPane().add(weightfield);

    JButton CalculateButton= new JButton("Calculate");
    CalculateButton.setForeground(Color.DARK_GRAY);
    CalculateButton.setBackground(Color.CYAN);
    CalculateButton.setBounds(87 , 385 ,100 ,46);
    getContentPane().add(CalculateButton);
    
    resultLabel = new JLabel("");
    //resultLabel.setForeground(new Color(255, 255, 255));
    resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    resultLabel.setBounds(87, 477, 438, 30);
    getContentPane().add(resultLabel);

    male= new JRadioButton("Male");
    male.setSize(114, 46);
    male.setLocation(166, 313);
    female= new JRadioButton("female");
    female.setSize(107, 46);
    female.setLocation(293, 313);
    ButtonGroup g1=new ButtonGroup();
    g1.add(male);
    g1.add(female);

    //add(g1);
    getContentPane().add(male);
    getContentPane().add(female);
    
    JLabel lblNewLabel = new JLabel("Gender");
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
    lblNewLabel.setBounds(87, 314, 70, 40);
    getContentPane().add(lblNewLabel);

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
    backButton.setBounds(270, 385, 150, 46);
    getContentPane().add(backButton, BorderLayout.SOUTH);

    
    lblNewLabel_1 = new JLabel("BMI CALCULATOR");
    lblNewLabel_1.setBackground(new Color(0, 255, 0));
    //lblNewLabel_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(143, 93, 382, 62);
    getContentPane().add(lblNewLabel_1);
    
    lblNewLabel_2 = new JLabel("Age :");
    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
    lblNewLabel_2.setBounds(87, 268, 62, 30);
    getContentPane().add(lblNewLabel_2);
    
    JTextArea textArea = new JTextArea();
    textArea.setBounds(87, 457, 5, 22);
    getContentPane().add(textArea);
    
    textField = new JTextField();
    textField.setBounds(180, 268, 100, 30);
    getContentPane().add(textField);
    textField.setColumns(10);
    
    lblNewLabel_3 = new JLabel("");
    //lblNewLabel_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
    lblNewLabel_3.setBounds(87, 490, 333, 46);
    getContentPane().add(lblNewLabel_3);
    
    txtrBmiIsA = new JTextArea();
    txtrBmiIsA.setFont(new Font("Tahoma", Font.PLAIN, 15));
    txtrBmiIsA.setText(" BMI is a measurement of a person's leanness or corpulence based on\r\n their height and weight, and is intended to quantify tissue mass.\r\n It is widely used as a general indicator of whether a person has a \r\n healthy body weight for their height. \r\n");
    txtrBmiIsA.setBounds(65, 570, 515, 82);
    getContentPane().add(txtrBmiIsA);

    //male.setBounds();

    CalculateButton.addActionListener( new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            CalculateBMI();
            
        }
    });

    setVisible(true);
    setLocationRelativeTo(null);
  }

  public void CalculateBMI()
  {
    try {
        double weight = Double.parseDouble(weightfield.getText());
        double height = Double.parseDouble(heightfield.getText());
        double bmi_val = Calculate_BMI(height, weight);
        //System.out.println("weight :"+weight+ "height :"+ height);
        String category = getCategory(bmi_val);
        resultLabel.setText("Your BMI is : " + bmi_val + " (" + category + ")");
        lblNewLabel_3.setText("Healthy BMI range: 18.5 kg/m2 - 25 kg/m2");
        
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  

  public static void main(String[] args) {
    new bmiGui();
  }
}
