package structures;

import model.Student;

/**
 * Binary Search Tree that organizes and searches student records by Student ID.
 *
 * @author SUJA (23DA2-0724)
 */
//Verified by Suja - BST implementation
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

    /** Deletes a student by ID. Returns false if the ID is not found. */
    public boolean delete(String id) {
        if (search(id) == null) {
            return false; // nothing to delete
        }
        root = deleteRec(root, id);
        return true;
    }

    private Node deleteRec(Node node, String id) {
        if (node == null) {
            return null;
        }
        int cmp = id.compareTo(node.student.getId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, id);
        } else {
            // Case 1 and 2: no child, or only one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children. Replace with the smallest node of the right subtree
            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = deleteRec(node.right, successor.student.getId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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