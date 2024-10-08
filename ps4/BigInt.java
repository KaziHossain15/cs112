/* 
 * BigInt.java
 * 
 * By: Kazi Hossain
 * email: kazhoss@bu.edu
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int MAX_SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int sigDigits;
    private boolean overflow;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        digits = new int[MAX_SIZE];
        sigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
	    overflow = false;
    }

    // custom constructor
    public BigInt(int[] arr) {
        if (arr == null || arr.length > MAX_SIZE) { // this part does not let the size of the array exceed the MAX_SIZE
            throw new IllegalArgumentException("Invalid input array!");
        }
        
        for (int i = 0; i < arr.length; i++) { // this part checks if each element of the array is a valid input
            if (validDigit(arr[i]) == false) {
                throw new IllegalArgumentException("Invalid input digit!");
            }
        }
        
        digits = new int[MAX_SIZE]; // new array
        int index = MAX_SIZE - arr.length; // for loop to copy and input the elements into the array
        for (int i = 0; i < arr.length; i++) {
            digits[index] = arr[i];
            index += 1;
        }
        
        int count = 0; // this loop counts the amount of sigDigits
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i];
            if (digit != 0) {
                break;
            }
            count += 1;
        }
        sigDigits = MAX_SIZE - count; // assigns the sigDigits
        
        if (sigDigits > MAX_SIZE) { // updates the overflow boolean variable if there is an overflow
            overflow = true;
        }
        
    }

    // integer n constructor
    public BigInt(int n) {
        if (n < 0) { // checks if the integer n is valid
            throw new IllegalArgumentException("Invalid integer n, must be non-negative!");
        }

        String integerResult = ""; // creates a string representation of the integer
        integerResult += n;
        int stringDigits = integerResult.length();

        digits = new int[MAX_SIZE];


        for (int i = MAX_SIZE - 1; i >= MAX_SIZE - stringDigits; i--) { // for loop to create an array
            digits[i] = n % 10;
            n = n / 10;
        }
        
        sigDigits = stringDigits; // updates the sigDigits variable

        if (sigDigits > MAX_SIZE) { // checks to update the overflow variable
            overflow = true;
        }

    }

    // This method should compare the called BigInt object to the parameter other and return
    // -1 if the object is less than other
    // 0 if object is equal to other
    // 1 if object is greater than other
    public int compareTo(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("The integer in other is not allowed.");
        }
        if (this.sigDigits > other.sigDigits) {
            return 1;
        } else if (this.sigDigits < other.sigDigits) {
            return -1;
        } else {
            for (int i = MAX_SIZE - this.sigDigits; i < MAX_SIZE; i++) {
                if (this.digits[i] < other.digits[i]) {
                    return -1;
                } else if (this.digits[i] > other.digits[i]) {
                    return 1;
                }
            }
            return 0;
        }
    }

    // this method should create and return a new BigInt object for the sum of the integers
    // represented by the called object and other
    public BigInt add(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add a null object!");
        }

        if (this.sigDigits + other.sigDigits > MAX_SIZE) { // updates overflow if overflow occurs
            overflow = true;
        }

        BigInt sum = new BigInt(); // creates new object to store the sum of this and other
        
        int extra = 0; // the extra carry over value when addition goes over 9 
        for (int i = MAX_SIZE - 1; i >= 0; i--) { // for loop to add and add to the new object
            int sumOfDigits = this.digits[i] + other.digits[i] + extra;
            if (sumOfDigits >= 10) {  
                sum.digits[i] = sumOfDigits % 10;
                extra = sumOfDigits / 10;
            } else {
                sum.digits[i] = sumOfDigits;
                extra = 0;
            }
        }  
        
        if (extra > 0) { // handles the overflow for the extra
            sum.sigDigits = MAX_SIZE; 
            sum.overflow = true;
        } else {
            int count = 0;
            for (int i = 0; i < MAX_SIZE; i++) {
                if (sum.digits[i] != 0) {
                    break;
                }
                count += 1;
            }
            sum.sigDigits = MAX_SIZE - count;
        }
        
        return sum;
    }

    // this method creates and returns a new BigInt object for the product of the integers representted by the called object and other
    public BigInt mul(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot multiply a null object!");
        }

        if (this.sigDigits + other.sigDigits > MAX_SIZE) {
            overflow = true;
        }

        BigInt mult = new BigInt();

        for (int i = MAX_SIZE - 1; i >= MAX_SIZE - this.sigDigits; i--) { // nested for loop for multiplication
            for (int j = MAX_SIZE - 1; j >= MAX_SIZE - other.sigDigits; j--) {
                int product = this.digits[i] * other.digits[j]; 
                int arrIndex = i + j - (MAX_SIZE - 1);
                if (arrIndex >= 0) {
                    int extra = product / 10;
                    mult.digits[arrIndex] += product % 10;
                    arrIndex -= 1;
                    if (extra > 0) {
                        mult.digits[arrIndex] += extra;
                    }
                }
            }
        }

        int count = 0; // significant digits counter
        for (int i = MAX_SIZE - 1; i >= MAX_SIZE - mult.sigDigits; i--) {
            if (mult.digits[i] == 0) {
                break;
            }
            count += 1;
        }

        mult.sigDigits = count; // sets the significant digits

        if (mult.sigDigits > MAX_SIZE) { // updates the overflow variable
            mult.overflow = true;
        }


        return mult;
    }

    // this method will return true if the BigInt object the method is called on represents an integer greater than or equal to the integer
    // represetned by the BigInt object passed to the method or false otherwise
    public boolean greater_than_or_equal(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare a null object!");
        }
    
        if (this.sigDigits > other.sigDigits) {
            return true;
        } else if (this.sigDigits < other.sigDigits) {
            return false;
        } else {
            for (int i = MAX_SIZE - this.sigDigits; i < MAX_SIZE; i++) {
                if (this.digits[i] < other.digits[i]) {
                    return false;
                } else if (this.digits[i] > other.digits[i]) {
                    return true;
                }
            }
            return true;
        }
    }

    // this method returns true if the object in other is greater than this object and false otherewise
    // same structure as greater_than_or_equal
    public boolean smaller_than_or_equal(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare a null object!");
        }
    
        if (this.sigDigits > other.sigDigits) {
            return false;
        } else if (this.sigDigits < other.sigDigits) {
            return true;
        } else {
            for (int i = MAX_SIZE - this.sigDigits; i < MAX_SIZE; i++) {
                if (this.digits[i] < other.digits[i]) {
                    return true;
                } else if (this.digits[i] > other.digits[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    // returns true or false if the BigInt object represents an overflow
    public boolean isOverFlow() {
        if (this.overflow == true) {
            return true;
        } else {
            return false;
        }
    }
    
    // helper method for the constructor above
    // this helper method checks to see if the digit is a valid digit to pass through into the array
    private boolean validDigit(int digit) {
        boolean isDigit = false;
        if (digit >= 0 && digit <= 9) {
            isDigit = true;
        }
        return isDigit;
    }

    // accessor method
    // returns the integer value of the number of sigDigits
    public int getSigDigits() {
        return sigDigits;
    } 

    // toString method
    public String toString() {
        String result = "";
        for (int i = MAX_SIZE - sigDigits; i < MAX_SIZE; i++) {
            result += digits[i];
        }
        if (result == "") {
            result += "0";
        }
        return result;
    }

    // returns a reference to the internal digits array of the object
    public int[] getDigits() {
        return digits;
    }

    
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();


        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */

	
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();
        
    }
}
