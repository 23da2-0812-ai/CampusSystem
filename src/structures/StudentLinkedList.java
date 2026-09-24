package structures;

import model.Student;

/**
 * Singly linked list that stores student records.
 * Implemented manually (java.util.LinkedList is not used).
 *
 * @author ANEESIYA (23DA2-0812)
 */
public class StudentLinkedList {

    // Node of the linked list
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /** Adds a student at the end. Returns false if the ID already exists. */
    public boolean add(Student s) {
        if (search(s.getId()) != null) {
            return false;
        }
        Node newNode = new Node(s);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /** Searches by ID. Returns null if not found. */
    public Student search(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Updates name, programme and marks. Returns false if the ID is not found. */
    public boolean update(String id, String name, String programme, double marks) {
        Student s = search(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

    /** Deletes by ID. Returns the deleted student, or null if not found. */
    public Student delete(String id) {
        if (head == null) {
            return null;
        }
        if (head.data.getId().equals(id)) {
            Student removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getId().equals(id)) {
                Student removed = current.next.data;
                current.next = current.next.next;
                size--;
                return removed;
            }
            current = current.next;
        }
        return null;
    }

    /** Prints all students. */
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}