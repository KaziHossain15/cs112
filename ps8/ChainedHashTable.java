/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Kazi Hossain
 *     email: kazhoss@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }

        table = new Node[size];
        numKeys = 0;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
    
        int index = h1(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
            numKeys++;
        } else {
            Node chain = table[index];
            while (chain.next != null) {
                if (chain.key.equals(key)) {
                    chain.values.insert(value);
                    return true;
                }
                chain = chain.next;
            }
            chain.next = new Node(key, value);
            numKeys++;
        }
        
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int index = h1(key);
        Node trav = table[index];
        while (trav != null) {
            if (trav.key.equals(key)) {
                return trav.values;
            }
            trav = trav.next;
        }

        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int index = h1(key);
        Node trav = table[index];
        Node prev = null;
        while (trav != null) {
            if (trav.key.equals(key)) {
                if (prev == null) {
                    table[index] = trav.next;
                } else {
                    prev.next = trav.next;
                }    
            }
            prev = trav;
            trav = trav.next;
        }
        return null;
    }
    
    
    /*** Add the other required methods here ***/
   
    // this method gets the number of keys in the hashtable
    public int getNumKeys() {
        return numKeys;
    }

    // this method get the load factor which is number of keys in a hashtable divided by
    // the length of the hashtable
    public double load() {
        return (double) numKeys / table.length;
    }

    // this method gets all the keys and returned to an array
    public Object[] getAllKeys() {
        LinkedList<Object> queue = new LinkedList<>();
        
        for (int i = 0; i < table.length; i++) {
            Node trav = table[i];
            while (trav != null) {
                queue.add(trav.key);
                trav = trav.next;
            }
        }
    
        Object[] keys = new Object[queue.size()];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = queue.pop(); 
        }
        return keys;
    }

    // this method resizes the hashtable
    public void resize(int size) {
        if (size < table.length) {
            throw new IllegalArgumentException();
        }

        if (size == table.length) {
            return;
        }

        ChainedHashTable resizedTable = new ChainedHashTable(size);

        for (int i = 0; i < table.length; i++) {
            Node trav = table[i];
            while (trav != null) {
                Object key = trav.key;
                Object val = trav.values.remove();
                resizedTable.insert(key, val);

                if (trav.values.isEmpty()) {
                    trav = trav.next;
                }
            }
        }

        table = resizedTable.table;
    }
    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
        // insert, serach, and remove methods test
        ChainedHashTable table = new ChainedHashTable(5);
        table.insert("howdy", 15);
        table.insert("goodbye", 10);
        System.out.println(table.insert("apple", 5));
        System.out.println(table);
        System.out.println(table.search("howdy"));
        System.out.println(table.search("goodbye"));
        System.out.println(table.search("apple"));
        System.out.println(table.remove("apple"));
        System.out.println(table);
        System.out.println(table.insert("apple", 5));
        System.out.println(table.remove("howdy"));
        System.out.println(table);

        System.out.println();
        
        // getNumKeys method test
        ChainedHashTable table2 = new ChainedHashTable(5);
        table2.insert("howdy", 15);
        table2.insert("goodbye", 10);
        table2.insert("apple", 5);
        System.out.println(table2.getNumKeys());
        table2.insert("howdy", 25);     // insert a duplicate
        System.out.println(table2.getNumKeys());

        System.out.println();

        // load method test
        ChainedHashTable table3 = new ChainedHashTable(5);
        table3.insert("howdy", 15);
        table3.insert("goodbye", 10);
        table3.insert("apple", 5);
        System.out.println(table3.load());
        table3.insert("pear", 6);
        System.out.println(table3.load());

        System.out.println();

        // getAllKeys method test
        ChainedHashTable table4 = new ChainedHashTable(5);
        table4.insert("howdy", 15);
        table4.insert("goodbye", 10);
        table4.insert("apple", 5);
        table4.insert("howdy", 25);    // insert a duplicate
        Object[] keys = table4.getAllKeys();
        System.out.println(Arrays.toString(keys));

        System.out.println();

        //resize method test
        ChainedHashTable table5 = new ChainedHashTable(5);
        table5.insert("howdy", 15);
        table5.insert("goodbye", 10);
        table5.insert("apple", 5);
        System.out.println(table5);
        table5.resize(7);
        System.out.println(table5);

    }
}
