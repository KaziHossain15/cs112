/*
 * Student First Name: Kazi
 * Student Last Name: Hossain
 * Student BU Number: U74696035
 * Purpose: creates an object to represent a Set of integers, each instance of the cass will represent one set and 
 * the integers that make up the set will be stored in an array of type int
 * To sum it up, it takes an array and makes it so that it can be manipulated
 */

 public class Set  {
    private static final int DEFAULT_SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int next;       // index to next available slot in the set array
    
    /*
     * Constructors
     */
    public Set() {
        // your code here
        this.set = new int[DEFAULT_SIZE];
        this.next = 0;
    }

    public Set(int[] arr) {
        this.set = new int[arr.length];
        this.next = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean exists = false; // variable to see if there are duplicates
            for (int j = 0; j < this.next; j++) { // for loop to test for duplicates
                if (this.set[j] == arr[i]) {
                    exists = true;
                    break;
                }
            }
            if (exists == false) { // 
                this.set[this.next] += arr[i]; 
                this.next++;
                if (this.next == this.set.length) {
                    grow();
                }  
            }
            
        }
    }

    /*
     * Implement remaining methods as specified by the problem set
     */

    // this method returns the exact copy of this set
    public Set clone() {
        Set copy = new Set();
        for (int i = 0; i < this.next; i++) {
            copy.insert(this.set[i]);
        }
        return copy;
    }

    // returns a string representation of the set
    public String toString() {
        String result = "[";
        for (int i = 0; i < this.next; i++) {
            result += this.set[i];
            if (i < this.next - 1) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }

    // returns the number of the elements in this set
    public int cardinality() {
        return this.next; // the next variable stores the next index to be filled but also amount of elements in the list so that is what you would return
    }

    // returns true if this set is empty and has no members and flase otherwise
    public boolean isEmpty() {
        return this.next == 0;
    }

    // returns true if n is in the set and false if it is not
    public boolean member(int n) {
        boolean isMember = false;
        for (int i = 0; i < this.next; i++) {
            if (this.set[i] == n) {
                return true;
            }
        }
        return isMember;
    }

    // returns true if this set is a subset of S and false if it is not
    // if all the numbers in the set is a subset of S
    public boolean subset(Set S) {
        for (int i = 0; i < this.next; i++) {
            if (S.member(this.set[i]) == false) {
                return false;
            }
        }
        return true;
    }

    // returns true if this set and S have the exactly the same members
    public boolean equal(Set S) {
        if (this.next != S.next) { // checks if they have the same amount of elements
            return false;
        }
        for (int i = 0; i < this.next; i++) {
            if (!S.member(this.set[i])) {
                return false;
            }
        }
        return true;
    }

    // does not return anything, it adds an integer k into the set if the integer is not a member of the set
    public void insert(int k) {
        if (!member(k)) {
            if (this.next == set.length) { // checks if the set is full, if it is, then it grows the set
                grow();
            }
            set[this.next] = k;
            this.next++;
        }
    }

    // does not return anything, but, if the integer k is a member of the set, then it gets removed
    public void delete(int k) {
        if (member(k)) {
            int index = 0;
            for (int i = 0; i < this.next; i++) { // checks the index of int k
                if (set[i] == k) {
                    index = i;
                    break;
                }
            }
            for (int i = index; i < this.next; i++) { // shifts the rest of the set
                set[i] = set[i + 1];
            }
            this.next -= 1;
        }
    }

    // this method resizes the array by growing it by the default size everytime it needs to grow
    // returns nothing
    public void grow() {
        int newLength = this.set.length + DEFAULT_SIZE;
        int[] grownSet = new int[newLength];
        for (int i = 0; i < this.next; i++) {
            grownSet[i] = this.set[i];
        }
        this.set = grownSet;
    }

    // returns a new set consisting of all of the elements of this set and combined
    // it returns without duplicates
    public Set union(Set S) {
        Set unionSet = new Set();
        for (int i = 0; i < this.next; i++) {
            unionSet.insert(this.set[i]);
        }
        for (int j = 0; j < S.next; j++) {
            if (!member(S.set[j])) {
                unionSet.insert(S.set[j]);
            }
        }
        return unionSet;
    }

    // returns a new set consisting of the elements that are common to both this set and S 
    // without duplicates
    public Set intersection(Set S) {
        Set intersectionSet = new Set();
        for (int i = 0; i < this.next; i++) {
            for (int j = 0; j < S.next; j++) {
                if (this.set[i] == S.set[j]) {
                    intersectionSet.insert(this.set[i]);
                }
            }
        }
        return intersectionSet;
    }

    // this method returns a new sset consisting of all the elements of this set that are not present in S
    public Set setdifference(Set S) {
        Set differenceSet = new Set();
        for (int i = 0; i < this.next; i++) {
            differenceSet.insert(this.set[i]);
        }
        for (int j = 0; j < S.next; j++) {
            if (member(S.set[j]) == true) {
                differenceSet.delete(S.set[j]);
            }
        }
        return differenceSet;
    }
 
}