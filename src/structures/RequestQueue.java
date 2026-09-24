package structures;

/**
 * Queue (FIFO) that stores student service requests in the order they arrive.
 * The request that arrived first is processed first.
 * Implemented manually using linked nodes (java.util.Queue is not used).
 *
 * @author AATHIF (23DA2-0725)
 */
public class RequestQueue {

    // Node of the queue
    private static class Node {
        String request;
        Node next;

        Node(String request) {
            this.request = request;
        }
    }

    private Node front;  // request that will be processed next
    private Node rear;   // request that arrived last
    private int size;

    /** Adds a new request at the rear of the queue. */
    public void enqueue(String request) {
        Node newNode = new Node(request);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Removes and returns the request at the front, or null if the queue is empty. */
    public String dequeue() {
        if (front == null) {
            return null;
        }
        String request = front.request;
        front = front.next;
        if (front == null) {
            rear = null;  // queue became empty
        }
        size--;
        return request;
    }

    /** Returns the front request without removing it, or null if empty. */
    public String peek() {
        return (front == null) ? null : front.request;
    }

    /** Prints all pending requests, in order of arrival. */
    public void displayAll() {
        if (front == null) {
            System.out.println("No pending service requests.");
            return;
        }
        int count = 1;
        Node current = front;
        while (current != null) {
            System.out.println(count + ". " + current.request);
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}