import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class workout_planner {
    private JFrame mainFrame;
    private JComboBox<String> levelBox, typeBox, bodyPartBox;
    private JTextArea workoutArea;
    private JButton startWorkoutButton, markCompleteButton, btnNewButton;
    private Map<String, String[]> strengthExercises;
    private String[] cardioExercises = {
            "Jump Rope", "Burpees", "Mountain Climbers", "Jump Squats", "High Knees", "Running"
    };

    public workout_planner() {
        mainFrame = new JFrame("Workout Planner");
        mainFrame.setSize(700, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Set Background Panel
        mainFrame.setContentPane(new BackgroundPanel());

        mainFrame.setLayout(null); // Keep the same layout

        JLabel titleLabel = new JLabel("Workout Planner", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBounds(200, 20, 300, 40);
        mainFrame.add(titleLabel);

        JLabel levelLabel = new JLabel("Select Level:");
        levelLabel.setBounds(50, 80, 150, 30);
        mainFrame.add(levelLabel);

        levelBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Advanced"});
        levelBox.setBounds(260, 80, 200, 30);
        mainFrame.add(levelBox);

        JLabel typeLabel = new JLabel("Workout Type:");
        typeLabel.setBounds(50, 130, 150, 30);
        mainFrame.add(typeLabel);

        typeBox = new JComboBox<>(new String[]{"Strength Training", "Weight Loss"});
        typeBox.setBounds(260, 130, 200, 30);
        mainFrame.add(typeBox);

        JLabel bodyPartLabel = new JLabel("Body Part (if Strength Training):");
        bodyPartLabel.setBounds(50, 180, 200, 30);
        mainFrame.add(bodyPartLabel);

        bodyPartBox = new JComboBox<>(new String[]{"Chest", "Back", "Legs", "Shoulders", "Arms", "Abs"});
        bodyPartBox.setBounds(260, 180, 200, 30);
        mainFrame.add(bodyPartBox);

        JButton showExercisesButton = new JButton("Show Exercises");
        showExercisesButton.setBounds(251, 238, 150, 40);
        mainFrame.add(showExercisesButton);

        workoutArea = new JTextArea();
        workoutArea.setBounds(50, 290, 600, 200);
        workoutArea.setEditable(false);
        mainFrame.add(workoutArea);

        startWorkoutButton = new JButton("Start Workout");
        startWorkoutButton.setBounds(173, 501, 150, 40);
        mainFrame.add(startWorkoutButton);

        markCompleteButton = new JButton("Mark Completed");
        markCompleteButton.setBounds(365, 501, 150, 40);
        mainFrame.add(markCompleteButton);

        btnNewButton = new JButton("Back to Home");
        btnNewButton.setBounds(281, 564, 123, 40);
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setForeground(Color.WHITE);
        mainFrame.add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            mainFrame.dispose();
            new HomePage();
        });

        showExercisesButton.addActionListener(e -> showExercises());
        startWorkoutButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Workout Started!"));
        markCompleteButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Workout Completed!"));

        initializeStrengthExercises();
        mainFrame.setVisible(true);
    }

    private void initializeStrengthExercises() {
        strengthExercises = new HashMap<>();
        strengthExercises.put("Chest", new String[]{"Bench Press", "Incline Press", "Chest Fly", "Push-ups", "Cable Crossovers", "Dips"});
        strengthExercises.put("Back", new String[]{"Pull-ups", "Deadlifts", "Bent-over Rows", "Lat Pulldown", "Seated Row", "Face Pulls"});
        strengthExercises.put("Legs", new String[]{"Squats", "Leg Press", "Lunges", "Leg Curls", "Calf Raises", "Step-ups"});
        strengthExercises.put("Shoulders", new String[]{"Overhead Press", "Lateral Raises", "Front Raises", "Face Pulls", "Shrugs", "Reverse Flys"});
        strengthExercises.put("Arms", new String[]{"Bicep Curls", "Tricep Dips", "Hammer Curls", "Skull Crushers", "Preacher Curls", "Rope Pushdowns"});
        strengthExercises.put("Abs", new String[]{"Crunches", "Hanging Leg Raises", "Planks", "Russian Twists", "Bicycle Crunches", "Toe Touches"});
    }

    private void showExercises() {
        String type = (String) typeBox.getSelectedItem();
        if (type.equals("Strength Training")) {
            String bodyPart = (String) bodyPartBox.getSelectedItem();
            workoutArea.setText("Workout Plan for " + bodyPart + "\n\n");
            for (String exercise : strengthExercises.get(bodyPart)) {
                workoutArea.append("- " + exercise + "\n");
            }
        } else {
            workoutArea.setText("Weight Loss Workout Plan\n\n");
            for (String exercise : cardioExercises) {
                workoutArea.append("- " + exercise + "\n");
            }
        }
    }

    // Background panel for setting an image as a background
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("/gym_bg.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new workout_planner();
    }
}
