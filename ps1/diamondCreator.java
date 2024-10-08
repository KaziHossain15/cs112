public class diamondCreator {
   public static void diamond(int n) {
      for (int i = 0; i < n; i++) {
         if (i <= n / 2) {
            for (int j = 1; j <= i; j++) {
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
}
