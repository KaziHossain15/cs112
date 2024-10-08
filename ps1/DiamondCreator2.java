public class DiamondCreator2 {
    public static void diamond(int n) {
       for (int i = 0; i < n; i++) {  //0
          if (i <= n / 2) {
             for (int j = 1; j <= i; j++) { //
                System.out.print(j);
             }
          } else {
             for (int j = 1; j <= n - i; j++) {
                System.out.print(j);
             }
          }
          System.out.println();
       }
    }
 
    public static void main(String[] args) {
       diamond(10);
    }
 }
 