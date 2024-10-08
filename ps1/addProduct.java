public class addProduct {
    public static void oddProduct(int n) {
        int product = 1;
        for (int x = 1; x < n; x++) { 
           if (x % 2 == 1)
               product *= x;
        }
        System.out.println(product);
     }    
}
