/* Cicular.java
 * 
 * This program's purpose is to find the area of the circle. This program asks for the diameter, 
 * and after the user inputs the diameter, it computes the area in sqaure feet and square yards.
 * 
 * completed by: Kazi Hossain; kazhoss@bu.edu
 * 
 */

import java.util.*;

public class Circular {
    public static void main(String[] args) {
        // create a scanner that reads the console
        Scanner scan = new Scanner(System.in);
        
        // input for the diameter
        System.out.print("Diameter of the circle in feet: ");
        // scans the input integer and plugs it into the variable diameter
        int diameter = scan.nextInt();

        // initializes the variable radius
        double radius = diameter / 2.0;
        // initializes the double variable area
        double area = Math.PI * radius * radius;

        // initializes the square yards and remaining square feet variables
        int squareYards = (int)area / 9;
        int remainderSquareFeet = (int)Math.round(area) % 9;

        // print function of the variables
        System.out.println("The area of the circle is approximately:");
        System.out.println(Math.round(area) + " " + "square feet");
        System.out.println(squareYards + " " + "square yards plus " + remainderSquareFeet + " " + "square feet");

        // closes the scanner
        scan.close();    
    }
}
