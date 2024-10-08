public class GenerateSums {
    public static String generateSums(int n) {
        String result = "";
        int sum = 0;
        String otherResult = "";

        for (int i = 1; i <= n; i++) {
            sum += i;
            
            if (n == 0) {
                return result;
            }

            if (i == 1) {
                otherResult += i;
                result += otherResult + "\n";
            } else if (i <= n) {
                otherResult += " + " + i;
                result += otherResult;
                if (i != n) {
                    result += "\n";
                }
            }
        }  
            result += " = " + sum;
            return result;
    }

    public static void main(String[] args) {
        System.out.println(generateSums(4));
        System.out.println(generateSums(6));
        System.out.println(generateSums(50));

    }
}
