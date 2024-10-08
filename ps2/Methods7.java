/*
 * Methods7.Java
 * 
 * Name and email: Kazi Hossain; kazhoss@bu.edu
 * 
 * Practice with static methods, part II
 * 
 */


public class Methods7 {

    /*
     * This static method takes a string and prints it diagonally on separate lines
     * 
     * - use a nested for loop
     * - the second for loop should have a space
     * 
     */
    public static void printDiag(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; i > j; j++) {
                System.out.print(" ");
            }
            System.out.println(word.charAt(i));
        }
    }

    /*
     * this static method takes two parameters, string s and int a, and returns a string
     * with the last n characters of s
     * 
     * - if n is larger than the length of the string, return the whole string
     * - use conditional excecution
     * 
     */
    public static String lastN(String s, int n) {
        if (s.length() < n) {
            return s;
        } else {
            return s.substring(s.length() - n);
        }
    }

    /*
     * this static method takes two strings, in this case string a and b,
     * and returns the string produces when you remove the first occurance
     * of the second string
     * 
     * - if there are no occurances, return the first string *- use conditional execution
     * - use indexOf to determine the position of the first occurance
     * 
     */
    public static String remSub(String a, String b) {
        int index = a.indexOf(b);
        if (index >= 0) {
            return a.substring(0, index) + a.substring(index + b.length());
        } else {
            return a;
        }
    }

    /*
     * This static method takes two strings, s1 and s2, and returns the string by 'interleaving'
     * the characters (weaving the characters alternately)
     * 
     * - if the one of the strings are longer, you should have the remaining letters go after
     * - use a result = "" and gradually build it using string concatenation
     * - use a for loop
     * 
     */
    public static String interleave(String s1, String s2) {
        String result = "";
        int s1Length = s1.length();
        int s2Length = s2.length();
        int minLength = 0;

        if (s1Length > s2Length) {
            minLength = s2Length;
        } else if (s1Length < s2Length) {
            minLength = s1Length;
        }

        for (int i = 0; i < minLength; i++) {
            result += s1.charAt(i);
            result += s2.charAt(i);
        }

        if (s1Length > minLength) {
            result += s1.substring(minLength);
        } else if (s2Length > minLength) {
            result += s2.substring(minLength);
        }

        return result;
    }


    public static void main(String[] args) {
        printDiag("method");
        System.out.println(lastN("programming", 5));
        System.out.println(lastN("programming", 1));
        System.out.println(lastN("programming", 15));
        System.out.println(remSub("ding-a-ling", "ing"));
        System.out.println(remSub("variable", "var"));
        
    }
}
