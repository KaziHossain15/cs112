public class ArrayRecursion {
    
    // recursive version of the search method
    public static boolean search(Object item, Object[] arr, int start) {

        if (arr == null) {
           throw new IllegalArgumentException();
        } 
        
        if (start >= arr.length) {
            return false;
        } else {
            boolean rest = search(item, arr, start + 1);
            if (arr[start].equals(item)) {
                return true;
            } else {
                return rest;
            }
        }
     }

    // reverse array to string method that switches the front of an array to the back
    public static String reverseArrayToString(Object[] arr, int index ) {
        if (arr == null) {
            return "";
        } else if (arr.length == 0) {
            return "[]";
        } else {

            if (index == 0) {
                return "[" + reverseArrayToString(arr, index + 1) + arr[index] + "]";
            } else if (index < arr.length) {
                return reverseArrayToString(arr, index + 1) + arr[index] + ", ";
            } // + "" 
        }   
        return "";
    }

     public static void main(String[] args) {
        String[] arr = {"hi","hello","3","thwdn","fi3ubrfiur","fniwejrnfi3r"};
        System.out.println(search("hello", arr, 0));
        String[] arr2 = {"ijrnv","njirn","Hello","iejni","4","6"};
        System.out.println(search("Hello", arr2, 4));
        String a[] = { "abc", "def", "ghi", "klm", "nop", "qrs" };
        System.out.println(reverseArrayToString(a, 0));
     }
}
