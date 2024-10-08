/*
 * Methods6.java
 * 
 * Code added by: Kazi Hosain; kazhoss@bu.edu
 *
 * Practice with static methods, part I
 */

public class Methods6 {
    /*
     * 0) printVertical - takes a string s and prints the characters of 
     *    the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }

    /*
     * This printEveryOther method takes a string and prints every 
     * other letter in the string
     * - Would use an index to print (every even of i)
     * 
     */
    public static void printEveryOther(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i % 2 == 0){
                System.out.print(c);
            }
        }
    }

    /*
     * This method LongerLen takes two string and returns an integer
     * of the lenght of the longer string
     * 
     * - compares both strings and counts the length
     * 
     */
    public static int longerLen(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        if (aLength > bLength) {
            return aLength;
        } else if (aLength < bLength) {
            return bLength;
        } else {
            return bLength;
        }
    }


    /*
     * 
     * this method takes two parameters, a string and a char and returns
     * an integer number that is the index of the second time the char occurs
     * in the string
     * 
     * - use a counter and when the counter equals 2, return the index
     * - for loop
     * 
     */
    public static int secondIndex(String s, char c) {
        int counter = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                counter += 1;
                if (counter == 2){
                    return i;
                }    
            }
        } 
        return -1;
    }

    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");        
        printEveryOther("method");
        System.out.println(" "); // to separate the two print systems
        int len = longerLen("bye", "hello");
        System.out.println("the longer length is: " + len);
    }
}
