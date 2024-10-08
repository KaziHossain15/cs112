import java.util.Arrays;

public class Problem5 {
    
    /* union method
     * public method that takes two arrays of integers and uses an approach based on
     * merging to create and return a new array containing the union of the values in
     * the two arrays
     * should be sorted and no duplicates
    */
    public static int[] union(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException();
        }

        int[] unionArr = new int[a1.length + a2.length];
        
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);

        int i = 0;
        int j = 0;
        int k = 0;
        int prevIndex = k-1;

        while (i < a1.length && j < a2.length) {
            if (prevIndex == -1) {
                if (a1[i] == a2[j]) {
                    unionArr[k] = a1[i];
                    i++;
                    j++;
                    k++;
                    prevIndex++;
                } else if (a1[i] > a2[j]) {
                    unionArr[k] = a2[j];
                    j++;
                    k++;
                    prevIndex++;
                } else {
                    unionArr[k] = a1[i];
                    i++;
                    k++;
                    prevIndex++;
                }
            } else {
                if (a1[i] == a2[j]) {
                    if (a1[i] != unionArr[prevIndex]) {
                        unionArr[k] = a1[i];
                        i++;
                        j++;
                        k++;
                        prevIndex++;
                    } else {
                        i++;
                        j++;
                    }   
                } else if (a1[i] > a2[j]) {
                    if (a2[j] != unionArr[prevIndex]) {
                        unionArr[k] = a2[j];
                        j++;
                        k++;
                        prevIndex++;
                    } else {
                        j++;
                    }
                } else {
                    if (a1[i] != unionArr[prevIndex]) {
                        unionArr[k] = a1[i];
                        i++;
                        k++;
                        prevIndex++;
                    } else {
                        i++;
                    }
                }
            }
            
        }

        while (i < a1.length) {
            if (a1[i] != prevIndex) {
                unionArr[k++] = a1[i];
                prevIndex = a1[i];
            }
            i++;
        }
        while (j < a2.length) {
            if (a2[j] != prevIndex) {
                unionArr[k++] = a2[j];
                prevIndex = a2[j];
            }
            j++;
        }

        return unionArr;
    }

    public static void main(String[] args) {
        // union
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = union(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = union(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));
    }

}
