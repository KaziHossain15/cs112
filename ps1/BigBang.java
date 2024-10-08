import java.util.Scanner;

public class BigBang {
   public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       System.out.print("Enter three numbers: ");
       int a = scan.nextInt();
       int b = scan.nextInt();
       int c = scan.nextInt();

       if (a < b) {
           if (b < c) {
               if (a < c) {
                   System.out.println("You won!");
               }
           } else if (a < c) {
               System.out.println("rock!");
           }
           System.out.println("scissors!");
       } else if (a < b && a < c) {
           System.out.println("rock!");
       } else if (!(b < c)) {
           System.out.println("paper!");
       }
       System.out.println("lizard!");
       if (!(a < b)) {
           System.out.println("spock");
       } else if (b < c) {
           System.out.println("You lost!");
           if (a == b || b == c || a == c) {
               System.out.println("You lost again!");
           } else {
              System.out.println("You won!");
           }
       }
       System.out.println("done");
   }
}

