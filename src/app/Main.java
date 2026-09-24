package app;

import java.util.Scanner;
import model.Student;
import structures.StudentLinkedList;
import structures.ActionStack;
import structures.RequestQueue;
import structures.StudentBST;
import structures.StudentHashTable;
import structures.CampusGraph;

/**
 * University Student Record and Campus Route Management System
 * Menu-driven console application.
 */
public class Main {

    static StudentLinkedList list = new StudentLinkedList();
    static ActionStack actionStack = new ActionStack();
    static RequestQueue requestQueue = new RequestQueue();
    static StudentBST bst = new StudentBST();
    static StudentHashTable hashTable = new StudentHashTable();
    static CampusGraph graph = new CampusGraph();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: list.displayAll(); break;
                case 5: addServiceRequest(); break;
                case 6: processNextRequest(); break;
                case 7: actionStack.displayAll(); break;
                case 8: bst.displayInOrder(); break;
                case 9: searchByHashing(); break;
                case 10: addCampusLocation(); break;
                case 11: removeCampusLocation(); break;
                case 12: addCampusConnection(); break;
                case 13: removeCampusConnection(); break;
                case 14: graph.displayConnections(); break;
                case 15: traverseCampus(); break;
                case 16:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== University Student Record & Campus Route Management System =====");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
    }

    // ---------- Student record operations ----------

    private static void addStudent() {
        String id = readLine("Enter Student ID: ");
        String name = readLine("Enter Name: ");
        String programme = readLine("Enter Programme: ");
        double marks = readDouble("Enter Marks (0-100): ");

        try {
            Student student = new Student(id, name, programme, marks);
            boolean added = list.add(student);
            if (!added) {
                System.out.println("Error: A student with ID " + id + " already exists.");
                return;
            }
            bst.insert(student);
            hashTable.insert(student);
            actionStack.push("Added student " + id + " (" + name + ")");
            System.out.println("Student added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        String id = readLine("Enter Student ID to update: ");
        String name = readLine("Enter new Name: ");
        String programme = readLine("Enter new Programme: ");
        double marks = readDouble("Enter new Marks (0-100): ");

        try {
            boolean updated = list.update(id, name, programme, marks);
            if (updated) {
                actionStack.push("Updated student " + id);
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Error: No student found with ID " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        String id = readLine("Enter Student ID to delete: ");
        Student deleted = list.delete(id);
        if (deleted != null) {
            actionStack.push("Deleted student " + id);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Error: No student found with ID " + id);
        }
    }

    private static void searchByHashing() {
        String id = readLine("Enter Student ID to search: ");
        Student found = hashTable.search(id);
        if (found != null) {
            System.out.printf("ID: %-12s| Name: %-15s| Programme: %-6s| Marks: %.2f%n",
                    found.getId(), found.getName(), found.getProgramme(), found.getMarks());
        } else {
            System.out.println("No student found with ID " + id);
        }
    }

    // ---------- Queue operations ----------

    private static void addServiceRequest() {
        String request = readLine("Enter service request description: ");
        requestQueue.enqueue(request);
        System.out.println("Request added to queue.");
    }

    private static void processNextRequest() {
        String request = requestQueue.dequeue();
        if (request != null) {
            actionStack.push("Processed request: " + request);
            System.out.println("Processed: " + request);
        } else {
            System.out.println("No pending service requests.");
        }
    }

    // ---------- Graph operations ----------

    private static void addCampusLocation() {
        String name = readLine("Enter new campus location name: ");
        boolean added = graph.addLocation(name);
        System.out.println(added ? "Location added." : "Error: Location already exists.");
    }

    private static void removeCampusLocation() {
        String name = readLine("Enter campus location to remove: ");
        boolean removed = graph.removeLocation(name);
        System.out.println(removed ? "Location removed." : "Error: Location not found.");
    }

    private static void addCampusConnection() {
        String loc1 = readLine("Enter first location: ");
        String loc2 = readLine("Enter second location: ");
        boolean added = graph.addConnection(loc1, loc2);
        System.out.println(added ? "Connection added." : "Error: Could not add connection (check location names or duplicate).");
    }

    private static void removeCampusConnection() {
        String loc1 = readLine("Enter first location: ");
        String loc2 = readLine("Enter second location: ");
        boolean removed = graph.removeConnection(loc1, loc2);
        System.out.println(removed ? "Connection removed." : "Error: Connection not found.");
    }

    private static void traverseCampus() {
        String start = readLine("Enter starting location for BFS: ");
        if (!graph.hasLocation(start)) {
            System.out.println("Error: Location not found.");
            return;
        }
        System.out.println("BFS Traversal: " + graph.bfs(start));
    }

    // ---------- Input helper methods (handle invalid input) ----------

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}