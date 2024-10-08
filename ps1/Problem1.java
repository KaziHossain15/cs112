import java.util.*;
public class Problem1 {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      System.out.print("Enter an integer n: ");
      int n = console.nextInt();
      int sum = calculateSum(n);
      System.out.println("The sum of the numbers is: " + sum);
   }
   /*
    * This static method should take an integer n and return
    * the sum of all integers from 1 up to n, inclusive.
    */
    private static int calculateSum(int n) {
       int sum = 0;
       for (int i = 0; i <= n; i++) {
          sum+= i;
       }
       return sum;
    }
}
