/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }

    /*
     * depth method counts the depth of the tree at where the key belongs
     */
    public int depth(int key) {
        if (root == null) {
            return -1;
        }

        Node currentNode = root; // current node: starts at the root
        int depth = 0; // depth counter

        while (currentNode != null) {
            if (key == currentNode.key) { // if key is the currentNode's key, it returns the depth
                return depth;
            } else if (key > currentNode.key) {
                currentNode = currentNode.right; // checks right subtree
                depth += 1; 
            } else {
                currentNode = currentNode.left; // checks left subtree
                depth += 1; 
            }
        }

        return -1; // if the key is not found, return -1
    }

    /*
     * countEvensInTree - private static method that takes a reference to a Node object and uses recursion
     * to find and return the number of even-valued keys in the tree
     * the parameter is the root of the tree or subtree
     */
    private static int countEvensInTree(Node root) {
        if (root == null) { // if the root is null, return 0
            return 0;
        } else {
            int countRest = countEvensInTree(root.left) + countEvensInTree(root.right); // recursive case
            if (root.key % 2 == 0) {
                return 1 + countRest;
            } else {
                return countRest;
            }
        }
    }

    /*
     * countEvens method - public method that serves as a wrapper
     */
    public int countEvens() {
        return countEvensInTree(root);
    }

    /*
     * deleteMax - non-static method that takes no parameters and that uses iteration to find
     * and delete the node containing the largest key in the tree
     * it should also return the value of the key node that was deleted
     */
    public int deleteMax() {
        if (root == null) { // check null
            return -1;
        }

        Node parentNode = null; // parent node
        Node currentNode = root; // current node

        while (currentNode.right != null) { // loop that iterates until null of max (rightmost leaf)
            parentNode = currentNode;
            currentNode = currentNode.right;
        }

        if (currentNode.left == null) { // checks if max node has a left child
            if (parentNode != null) { // If the maximum node is not the root
                parentNode.right = null;
            } else { /// If the maximum node is the root, set the root to null
                root = null;
            }
        } else { // // If the maximum node has a left child
            if (parentNode != null) {
                parentNode.right = currentNode.left;
            } else {
                root = currentNode.left;
            }
        }
        
        return currentNode.key; // return key value
    }
    
    public static void main(String[] args) {
        /*
         * Add at least two unit tests for each method that you write.
         * Test a variety of different cases. 
         * Follow the same format that we used in the previous problem.
         * We have given you some preliminary code for the first test below.
         */

         System.out.println("--- Testing depth ---");
         System.out.println();
         System.out.println("(0) Testing tree.depth(13) on tree from Problem 9, ...");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             System.out.println("Depth of key 13: " + tree.depth(13));
             System.out.println("Expected output: 2");
             int expected = 2;
             System.out.println(tree.depth(13) == expected);
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();    // include a blank line between tests
 
         // Put your other tests below. 
         
         System.out.println("(1) Testing tree.depth(37) on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            System.out.println("Depth of key 37: " + tree.depth(37));
            System.out.println("Expected output: 0");
            int expected = 0;
            System.out.println(tree.depth(37) == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();

        System.out.println("(2) Testing countEvens() on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.countEvens());

            int[] keys = {4, 1, 3, 6, 5, 2};
            tree.insertKeys(keys);
            System.out.println("tree with keys from 1 to 6: " + tree.countEvens());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();
        
        System.out.println("(3) Testing countEvens() on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.countEvens());

            int[] keys = {6,7,3,1,2,7,9};
            tree.insertKeys(keys);
            System.out.println("tree with key 6: " + tree.countEvens());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();

        System.out.println("(4) Testing countEvens() on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.countEvens());

            int[] keys = {9,5,6,3,2,1};
            tree.insertKeys(keys);
            System.out.println("tree with key 9: " + tree.countEvens());
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();

        System.out.println("(5) Testing deleMax() on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.deleteMax());
            System.out.println();

            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            tree.levelOrderPrint();
            System.out.println();

            System.out.println("first deletion: " + tree.deleteMax());
            tree.levelOrderPrint();
            System.out.println();

            System.out.println("second deletion: " + tree.deleteMax());
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();

        System.out.println("(6) Testing deleMax() on tree from Problem 9...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.deleteMax());
            System.out.println();

            int[] keys = {30, 32, 41, 13, 23, 60, 72};
            tree.insertKeys(keys);
            tree.levelOrderPrint();
            System.out.println();

            System.out.println("first deletion: " + tree.deleteMax());
            tree.levelOrderPrint();
            System.out.println();

            System.out.println("second deletion: " + tree.deleteMax());
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }       
        System.out.println();
    }

    
}
