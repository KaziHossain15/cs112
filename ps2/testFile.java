public class testFile {
    public static void main(String[] args){
        String str1 = "Write a string";
        String str2 = "about spring";

        System.out.println(str1.indexOf('g'));
        System.out.println(str1.replace('i', 'o'));
        System.out.println(str2.charAt(2) + str2.substring(str2.length()-1));
        System.out.println(str2.charAt(1) + str1.substring(10));
        System.out.println(str2.substring(0, 5) + str1.substring(5));
        System.out.println(str1.substring(0, 6).toLowerCase() + str2.substring(6).toUpperCase());
    }
}
