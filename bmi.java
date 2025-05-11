//package gym;

import java.util.Scanner;

public class bmi {

    public static double Calculate_BMI(double height, double weight) {
        double BMI = weight / (height * height);
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double  height;
        double weight;
        System.out.println("Enter height in meters ");
        height = sc.nextDouble();
        System.out.println("Enter weight in Kgs :");
        weight = sc.nextDouble();

        Double res= Calculate_BMI(height, weight);


        System.out.println("Your BMI is " + Calculate_BMI(height ,weight));
        System.out.println("Your category is :"+getCategory(res) );
    }

}
