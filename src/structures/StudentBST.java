package structures;

import model.Student;

/**
 * Binary Search Tree that organizes and searches student records by Student ID.
 *
 * @author SUJA (23DA2-0724)
 */
public class StudentBST {

    // Node of the tree
    private static class Node {
        Student student;
        Node left, right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    /** Inserts a student into the tree, ordered by Student ID. Returns false if ID already exists. */
    public boolean insert(Student student) {
        if (search(student.getId()) != null) {
            return false; // duplicate ID, don't insert
        }
        root = insertRec(root, student);
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        if (student.getId().compareTo(node.student.getId()) < 0) {
            node.left = insertRec(node.left, student);
        } else {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    /** Searches for a student by ID. Returns null if not found. */
    public Student search(String id) {
        return searchRec(root, id);
    }

    private Student searchRec(Node node, String id) {
        if (node == null) {
            return null;
        }
        int cmp = id.compareTo(node.student.getId());
        if (cmp == 0) {
            return node.student;
        }
        return cmp < 0 ? searchRec(node.left, id) : searchRec(node.right, id);
    }

    /** Prints all students in sorted order of Student ID (in-order traversal). */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records.");
            return;
        }
        displayRec(root);
    }

    private void displayRec(Node node) {
        if (node == null) return;
        displayRec(node.left);
        System.out.printf("ID: %-12s| Name: %-15s| Programme: %-6s| Marks: %.2f%n",
                node.student.getId(), node.student.getName(),
                node.student.getProgramme(), node.student.getMarks());
        displayRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}