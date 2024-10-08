public class TracingMethodCalls {
    public static int compute(int x, int y) {
       System.out.println(x + " " + y);
       if (x*y > 0)
          if (y%2==0)
             return -y/2+1;
          else
             return -x/y;
       else if (x>0 && y>0)
          return -2*x;
       else
          return -y;
    }
 
    public static void main(String[] args) {
       int x = 8;
       int y = -6;
       System.out.println(x + " " + y);
       y = compute(x, y)/2;
       System.out.println(x + " " + y);
       x = 2*compute(y, x);
       System.out.println(x + " " + y);
       y = compute(x, x);
       System.out.println(x + " " + y);
    }

    public static String relationalOperator(int a, int b) {
      if (a > b) 
         return ">";
      else if (a < b)
         return "<";
      else 
         return "=";
    }
 }