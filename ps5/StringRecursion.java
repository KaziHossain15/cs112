public class StringRecursion {

    // this method reverse prints a string
    public static void printReverse(String str) {
        if (str.length() == 0 || str == null) {
            System.out.print("");
        } else if (str.length() == 1) {
            System.out.print(str);
        } else {
            printReverse(str.substring(1));
            System.out.print(str.charAt(0));
        }
    }

    // this method should take a string and use recursion to return a string that has no excess spaces
    public static String trim(String str) {
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            return "";
        } else {
            String rest = trim(str.substring(1,str.length() - 1));
            if (str.charAt(0) == ' ' && str.charAt(str.length() - 1) == ' ') {
                return rest;
            } else if (str.charAt(0) == ' ') {
                return rest;
            } else if (str.charAt(str.length() - 1) == ' ') {
                return str.charAt(0) + rest;
            } else {
                return str.charAt(0) + rest + str.charAt(str.length() - 1);
            }
        }
    }

    // this method uses recursion to find and return the index of the first occurrence of the character in the string
    // if no occurrence, return -1
    public static int find(char ch, String str) {
        if (str.length() == 0 || str == null) {
            return -1;
        } else {
            if (str.charAt(0) == ch) {
                return 0;
            } else {
                int rest = find(ch,str.substring(1));
                if (rest == -1) {
                    return -1;
                } else {
                    return 1 + rest;
                }
            }
        }
    }

    // uses recursion to return the string that is formed by weaving the characters from each string together
    public static String weave(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        if (str2.length() == 0 && str1.length() == 0) {
            return "";
        } else if (str2.length() == 0 && !(str1.length() == 0)) {
            return str1;
        } else if (str1.length() == 0 && !(str2.length() == 0)) {
            return str2;
        } else {
            String rest = weave(str1.substring(1), str2.substring(1));
            return str1.substring(0,1) + str2.substring(0,1) + rest;
        }
    }

    // this method uses recursion to find and return the index of the first occurance of the character given in the string
    // returns -1 if there is no occurance
    public static int indexOf(char ch, String str) {
        if (str.length() == 0 || str == null) {
            return -1;
        } else {
            if (str.charAt(0) == ch) {
                return 0;
            } else {
                int rest = find(ch,str.substring(1));
                if (rest == -1) {
                    return -1;
                } else {
                    return 1 + rest;
                }
            }
        }
    }



    // main method
    public static void main(String[] args) {
        printReverse("Terriers");
        System.out.println();
        System.out.println(trim(" hello world    "));
        System.out.println(trim("recursion  "));
        System.out.println(find('b', "Rabbit"));
        System.out.println( weave("aaaa", "bbbb") );
        System.out.println( weave("hello", "world") );
        System.out.println( indexOf('b', "Rabbit") ); 
        System.out.println( indexOf('P', "Rabbit") );
    }
}
