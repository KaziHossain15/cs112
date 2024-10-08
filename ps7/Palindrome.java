/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Kazi Hossain
 *     username:
 */
   
public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        object = object.toLowerCase(); // turns everything into lowercase

        ArrayStack<Character> stack = new ArrayStack<Character>(object.length());
        ArrayQueue<Character> queue = new ArrayQueue<Character>(object.length());

        for (int i = 0; i < object.length(); i++) { // inserts the character into the stack and queue
            char c = object.charAt(i);
            if (c >= 'a' && c <= 'z') { // using ASCII
                queue.insert(c);
                stack.push(c);
            }
        }

        if (stack.isEmpty() || queue.isEmpty()) { // if the string is empty, it is a palindrome
            return true; 
        }


        while (!stack.isEmpty() && !queue.isEmpty()) { // checks if the first character is equal to the last character
            char charLast = stack.pop();
            char charFirst = queue.remove();
            if (charFirst != charLast) {
                return false; 
            }
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */

        System.out.println("(1) Testing on \"I am him, mih ma I!\"");
        try {
            boolean results = isPal("I am him, mih ma I!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(2) Testing on \"that is my table\"");
        try {
            boolean results = isPal("that is my table");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(3) Testing on \"Habebah habebah!\"");
        try {
            boolean results = isPal("Habebah habebah!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(4) Testing on \"Mr. Owl ate my metal worm\"");
        try {
            boolean results = isPal("Mr. Owl ate my metal worm");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(5) Testing on \"Was it a car or a cat I saw?\"");
        try {
            boolean results = isPal("Was it a car or a cat I saw?");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();
    }
}