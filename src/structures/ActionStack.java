package structures;

/**
 * Stack (LIFO) that stores recent actions performed in the system,
 * for example "Added student 23DA2-0812". The most recent action is on top.
 * Implemented manually using linked nodes (java.util.Stack is not used).
 *
 * @author AATHIF (23DA2-0725)
 */
public class ActionStack {

    // Node of the stack
    private static class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
        }
    }

    private Node top;
    private int size;

    /** Pushes a new action onto the top of the stack. */
    public void push(String action) {
        Node newNode = new Node(action);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Removes and returns the most recent action, or null if the stack is empty. */
    public String pop() {
        if (top == null) {
            return null;
        }
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    /** Returns the most recent action without removing it, or null if empty. */
    public String peek() {
        return (top == null) ? null : top.action;
    }

    /** Prints all actions, most recent first. */
    public void displayAll() {
        if (top == null) {
            System.out.println("No recent actions.");
            return;
        }
        int count = 1;
        Node current = top;
        while (current != null) {
            System.out.println(count + ". " + current.action);
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}