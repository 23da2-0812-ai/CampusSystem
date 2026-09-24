package structures;

import model.Student;

/**
 * Hash table that supports efficient student searching by Student ID.
 * Implemented manually using an array of buckets with chaining
 * (a linked list at each bucket handles collisions).
 *
 * @author SUJA (23DA2-0724)
 */
public class StudentHashTable {

    // Node for chaining (collision handling)
    private static class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node[] buckets;
    private int capacity;
    private int size;

    public StudentHashTable() {
        this.capacity = 16; // number of buckets, can grow later if needed
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    /** Converts a Student ID into a bucket index. */
    private int hash(String id) {
        int hashCode = id.hashCode();
        return Math.abs(hashCode) % capacity;
    }

    /** Inserts a student into the hash table. Returns false if ID already exists. */
    public boolean insert(Student student) {
        if (search(student.getId()) != null) {
            return false; // duplicate ID rejected
        }
        int index = hash(student.getId());
        Node newNode = new Node(student);
        newNode.next = buckets[index];
        buckets[index] = newNode; // insert at head of the chain
        size++;
        return true;
    }

    /** Searches for a student by ID. Returns null if not found. */
    public Student search(String id) {
        int index = hash(id);
        Node current = buckets[index];
        while (current != null) {
            if (current.student.getId().equals(id)) {
                return current.student;
            }
            current = current.next;
        }
        return null;
    }

    /** Removes a student by ID. Returns true if removed, false if not found. */
    public boolean remove(String id) {
        int index = hash(id);
        Node current = buckets[index];
        Node prev = null;
        while (current != null) {
            if (current.student.getId().equals(id)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}