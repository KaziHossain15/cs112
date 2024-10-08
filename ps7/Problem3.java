import java.util.Stack;

public class Problem3 {
    public static void doubleAllStack(Stack<Object> stack, Object item) {
        Stack<Object> newStack = new Stack<Object>();

        // Process all items in the original stack
        while (!stack.isEmpty()) {
            Object current = stack.pop();
            // Check if the current item is equal to the specified item
            if (current.equals(item)) {
                // If it is, add two copies of the item to the new stack
                newStack.push(item);
                newStack.push(item);
            } else {
                // Otherwise, add the current item to the new stack
                newStack.push(current);
            }
        }

        // Refill the original stack from the new stack
        while (!newStack.isEmpty()) {
            stack.push(newStack.pop());
        }
    }

    

    public static void main(String[] args) {
        // Test case 1
        Stack<Object> stack1 = new Stack<>();
        stack1.push(10);
        stack1.push(2);
        stack1.push(7);
        stack1.push(2);
        stack1.push(5);
        Object item1 = 2;

        System.out.println("Original stack 1: " + stack1);
        Problem3.doubleAllStack(stack1, item1);
        System.out.println("Stack 1 after doubling all occurrences of " + item1 + ": " + stack1);

    }
}
