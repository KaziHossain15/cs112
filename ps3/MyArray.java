/* File: MyArrays
 *
 * Author:  Kazi Hossain 
 *
 * Purpose: To create a class that allows you to
 * manipulate an array of integers.
 */

 import java.util.*;                

 public class MyArray  {
 
     // the sentinel value used to indicate end of input, initialized to -999
     private static final int SENTINEL = -999;
     // the default size of the array if one is not specified, initialized to 20
     private static final int DEFAULT_SIZE = 20;
     // the lower bound of the range of integer elements, initialized to 10
     private static final int LOWER = 10;
     // the upper bound of the range of integer elements, initialized to 50
     private static final int UPPER = 50;
     // a data member to reference an array of integers
     private int[] arr;
     // a data member to represent the number of elements entered into the array
     private int numElements;
 
     // instance variables of the class
     private int sum;
     private int min;
     private int max;
     private double avg;

 // CONSTRUCTORS
     // Initializes a MyArray object using default members
     public MyArray() {
        this.arr = new int[DEFAULT_SIZE];
        this.numElements = 0;
        
     }

     // custom constructor that creates an array of integers based on the input argument passed to n
     public MyArray(int n) {
        this.arr = new int[n];
        this.numElements = 0;
        this.computeStatistics();
     }

     /* custom constructor that creates the object's based on the array of integers passed to the method, 
     * should create a new array of integers initialized from the array passed to the method
     */ 
    public MyArray(int[] arr) {
        this.arr = new int[arr.length]; // since it uses the object's array, it needs to use self (aka this)
        int position = 0;
        for (int i = 0; i < arr.length; i++) { // for loop to see if the integer in the array is in bounds
            
            if (validInput(arr[i]) == true) { // conditional statement to see if the integer is in bounds
                this.arr[position] = arr[i];
                position++;
            }
        }
        this.numElements = position;
        this.computeStatistics();
    }

    /* method that prompts the user to enter an integer and stores it in the object's array
     * the user can enter only the amount of how long the array is or less than that amount
     * user does not want to fill all the positions of the array, they can specify end of input
     * by entering the sentinel value
    */
    public void inputElements() {
        Scanner scan = new Scanner(System.in);// scanner for the input
        int arrLength = this.arr.length; 
        while(numElements < arrLength) {
            int intForArr = scan.nextInt(); // scanner for the next integer to be checked if it can enter the array
            if (intForArr == SENTINEL) {
                break;
            } else if (validInput(intForArr) == true) { // if conditional to check the bounds
                this.arr[this.numElements] = intForArr;
                this.numElements ++;
            } else {
                    throw new IllegalArgumentException("Invalid input. Enter an integer in bounds!");
            }
        }
        this.computeStatistics();
        scan.close();
    }

    /* class level method to determine if the integer passed to the method is within the bounds as specified by the 
     * class variables of the class, LOWER and UPPER
     * 
     * - boolean means return either true or false
    */
    public boolean validInput( int num ) {
        if (num <= UPPER && num >= LOWER) {
            return true;
        } else {
            return false;
        }
    }

    // method that creates and returns a string representing the contents of the array
    public String toString() {
        if (numElements == 0) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < numElements; i++) {
            result += this.arr[i];
            if (!(i == numElements - 1)) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    
    /* method that computes the expected statics of the array
     * ex. the min, max, sum, avg, and the middle of all the elements
     * they should be stored internally in the object in the corresponding data
     * 
     * - initialize the varibles, if there are no elements, set everything to zero
     * - use a for loop for min, max, and sum (once it passes through, add it to a sum variable)
     * - after the loop, calculate the average with numElements
     */
    public void computeStatistics() {
        if (numElements==0){
        this.min = 0;
        this.max = 0;
        this.sum = 0;
        this.avg = 0.0;

        }else{
        this.min = this.arr[0];
        this.max = this.arr[0];
        this.sum = 0;
    
        for (int i = 0; i < this.numElements; i++) {
            this.sum += this.arr[i];
            if (this.min > this.arr[i]) {
                this.min = this.arr[i];
            } else if (this.max < this.arr[i]) {
                this.max = this.arr[i];
            }
            
        }
        this.avg = (double) this.sum / this.numElements;
    }
    }

    // get methods (acessors)
    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public int getSum() {
        return this.sum;
    }

    public double getAvg() {
        return this.avg;
    }

    public int[] getArr() {
        return this.arr;
    }

    public int getNumElements() {
        return this.numElements;
    }

    public int getArrLength() {
        return this.arr.length;
    }

    /* method that returns the position of the last occurance of the number passed to the method
     *  
     * - use a for loop to check which index that it last occurs at
     * - create a variable that shows the last occurance that gets returned at the end, if there is no occurance, return -1
     */  
    public int lastIndex(int n) {
        int lastOccurance = -1;
        for (int i = 0; i < this.numElements; i++) {
            if (this.arr[i] == n) {
                lastOccurance = i;
            }
        }
        return lastOccurance; 
    }

    /* method that allows you to insert a speficied number at a specified position within the
     * currently filled portion of the array
     * 
     * - if conditional for position if it does not exist
     * - if conditional for if the array is full
     * - for loop for shifting postions
     */
    public boolean insert(int n, int position) {
        if (position < 0 || position > this.numElements) {
            return false;
        }
        if (this.numElements == this.arr.length) {
            return false;
        }
        for (int i = this.numElements - 1; i >= position; i--) {
            this.arr[i+1] = this.arr[i];
        }
        this.arr[position] = n;
        this.numElements += 1; // updates the amount of elements there are
        this.computeStatistics();
        return true;
    }
    
    /* method to remove the smallest element in the array
     * 
     * - returns the element that is removed
     * - if not elements are removed, then -1 should be returned
     * - need index variable and integer variable
     * - use for loop
     * - update the numElements variable
     */
    public int removeSmallest() {
        if (this.numElements == 0) {
            return -1;
        }
        int smallestIndex = 0;
        int removedInteger = this.arr[smallestIndex];
        for (int i = 1; i < this.numElements; i++) {
            if (removedInteger > this.arr[i]) {
                smallestIndex = i;
                removedInteger = this.arr[i];
            }
        }
        for (int i = smallestIndex; i < this.numElements - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }    
        this.numElements -= 1;
        this.computeStatistics();
        return removedInteger;
    }

    /* method to grow the physical array by some size n
     *
     * - if n is less than or equal to 0, return false
     * - need to create a new array and copy the elements to the new array
     * - after the new array is complete, assign it to the old array
     * - return true if it can grow
     */ 
    public boolean grow(int n) {
        if (n <= 0) {
            return false;
        }
        int[] grownArray = new int[this.arr.length + n];
        for (int i = 0; i < this.numElements; i++) {
            grownArray[i] = this.arr[i];
        }
        this.arr = grownArray;
        return true;    
    }

    /* method shrink, this method shrinks the array to be the same length as the number of elements
     *
     * - almost the same as the grow method
     */ 
    public boolean shrink() {
        if (this.arr.length == this.numElements) {
            return false;
        }
        int[] shorterArray = new int[this.numElements];
        for (int i = 0; i < this.numElements; i++) {
            shorterArray[i] = this.arr[i];
        }
        this.arr = shorterArray;
        return true;
    }

    /* thie method creates and returns s string which is a histogram
     * 
     * - for loop to print the number of asterisks
     */
    public String computeHistogram() {
        String result = "";
        for (int i = 0; i < this.numElements; i++) {
            int numAsterisks = this.arr[i];
                for (int j = 0; j < numAsterisks; j++) {
                    result += "*";
                }
                result += "\n";
        }
        return result;
    }

 
     public static void main(String [] args) {
 
         System.out.println("\nUnit Test for MyArray.\n");
 
     // Fill in your unit tests
        MyArray myArray = new MyArray(new int[]{30, 35, 40, 50});
     
        // test for inputElements()
        // MyArray testArray = new MyArray(7); 
        // System.out.print("Enter your integers: ");
        // testArray.inputElements();
        System.out.println(Arrays.toString(myArray.arr));

        // test for validInput( int num )
        // int testNum1 = 30;
        // int testNum2 = 5;
        // System.out.println(myArray.validInput(testNum1));
        // System.out.println(myArray.validInput(testNum2));

        // test computeStatistics()
        // testArray.computeStatistics();
        // System.out.println(testArray.getMin());
        // System.out.println(testArray.getMax());
        // System.out.println(testArray.getSum());
        // System.out.println(testArray.getAvg());
        // System.out.println(testArray.getMiddle());

        // // test for lastIndex(int n)
        // System.out.println(testArray.lastIndex(25));

        // // test for insert(int n, int position)
        // System.out.println(testArray.insert(26, 4));
        // System.out.println(Arrays.toString(testArray.arr));

        // // test for removeSmallest()
        // System.out.println(testArray.removeSmallest());
        // System.out.println(Arrays.toString(testArray.arr));

        // // test for grow(int n)
        // System.out.println(testArray.grow(3));
        // System.out.println(Arrays.toString(testArray.arr));

        // // test for shrink()
        // System.out.println(testArray.shrink());
        // System.out.println(Arrays.toString(testArray.arr));

        // // test for computeHistogram()
        // String histogram = testArray.computeHistogram();
        // System.out.println(histogram);
        // System.out.println(testArray.toString());

        int[] test = {30, 35, 40, 50};
        MyArray a1 = new MyArray(test);
        System.out.println(a1);
        System.out.println(a1.getMin() + " " + a1.getMax() + " " + a1.getSum() + " " + a1.getAvg());
        System.out.println(a1.getNumElements());
     }
 }