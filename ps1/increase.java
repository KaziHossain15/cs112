public class increase {
    public static void increaseBy(int n, int f, int t) {
        int r = n;
        while (r < (n+f*t)) {
           System.out.println(r);
           r += f;
        }
     }
     public static void main(String[] args) {
      increase.increaseBy(5, 4, 3);
  }  
}
