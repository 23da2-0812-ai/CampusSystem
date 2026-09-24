# University Student Record and Campus Route Management System

**Module:** CIT300 — Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1 (Week 10)
**Coverage:** Linear Data Structures, Trees, Hashing, and Graphs
**Contribution:** 10% of the final module grade

---

## Table of Contents

1. Project Overview
2. Group Members
3. Individual Contributions
4. Data Structures Used
5. Project Structure
6. Features
7. Menu Options
8. How to Run
9. Testing

---

## Project Overview

This project is a Java console application that manages university student records and represents connections between campus locations. It demonstrates the practical use of linked lists, stacks, queues, trees, hashing, and graphs as required by the CIT300 module.

---

## Group Members

| # | Name          | Student ID  | Responsibility                                                              |
|---|---------------|-------------|------------------------------------------------------------------------------|
| 1 | ABF. Aneesiya | 23DA2-0812  | Linked list implementation and student-record management                    |
| 2 | AM. Aathif    | 23DA2-0725  | Stack and queue implementation and related operations                       |
| 3 | IM. Suja      | 23DA2-0724  | BST implementation and hashing/search functionality                         |
| 4 | IA. Sama      | 23DA2-0938  | Graph implementation, campus locations, connections, and BFS/DFS traversal  |

All members contributed to integration, validation, testing, debugging, and documentation.

---

## Individual Contributions

### 1. ABF. Aneesiya — 23DA2-0812
Implemented StudentLinkedList.java, covering add, search, update, delete, and display operations for student records using a manually implemented singly linked list. Verified duplicate-ID rejection and correct traversal order.

### 2. AM. Aathif — 23DA2-0725
Implemented ActionStack.java (a LIFO stack for recent actions and undo history) and RequestQueue.java (a FIFO queue for service requests). Verified push/pop and enqueue/dequeue behaviour and correct ordering.

### 3. IM. Suja — 23DA2-0724
Implemented StudentBST.java (a Binary Search Tree for organizing and searching students by ID) and StudentHashTable.java (a hash table with chaining for efficient ID lookup). Verified sorted in-order traversal and correct collision handling.

### 4. IA. Sama — 23DA2-0938
Implemented CampusGraph.java, an adjacency-list graph representing campus locations and roads, including add/remove operations for locations and connections, and both Breadth-First Search (BFS) and Depth-First Search (DFS) traversal methods. BFS uses a queue for level-by-level traversal, while DFS uses an explicit stack (iterative approach) to traverse as deep as possible before backtracking.

---

## Data Structures Used

| Data Structure | Class | Purpose |
|-----------------|-------|---------|
| Linked List | StudentLinkedList | Store and manage student records |
| Stack | ActionStack | Track recent actions / undo history |
| Queue | RequestQueue | Process service requests in arrival order |
| Binary Search Tree | StudentBST | Organize and search students by ID |
| Hash Table (chaining) | StudentHashTable | Fast student ID lookup |
| Graph (adjacency list) | CampusGraph | Represent campus locations and roads; BFS/DFS traversal |

---

## Project Structure

CampusSystem/
  src/
    app/
      Main.java                  - Menu-driven console interface
    model/
      Student.java                - Student record model with validation
    structures/
      StudentLinkedList.java      - Requirement 2, Linked list
      ActionStack.java            - Requirement 3, Stack
      RequestQueue.java           - Requirement 4, Queue
      StudentBST.java             - Requirement 5, BST
      StudentHashTable.java       - Requirement 6, Hashing
      CampusGraph.java            - Requirement 7-11, Graph (BFS + DFS)
  README.md

---

## Features

- Add, update, delete, search, and display student records
- Student records stored and managed using a manually implemented linked list
- Recent actions tracked using a stack (add / update / delete / process request)
- Student service requests processed in arrival order using a queue
- Student records organized and searched by ID using a Binary Search Tree
- Efficient student ID search using a manually implemented hash table (chaining)
- Campus locations and roads modelled as a graph (adjacency list)
- Add and remove campus locations and connections
- Display of the full campus connection network
- Breadth-First Search (BFS) traversal of campus locations
- Depth-First Search (DFS) traversal of campus locations
- Menu-driven console interface with 17 options
- Input validation for invalid menu choices, non-numeric input, duplicate IDs/locations, missing records, invalid marks (must be between 0 and 100), and unavailable connections

---

## Menu Options

1.  Add Student Record
2.  Update Student Record
3.  Delete Student Record
4.  Display All Records using Linked List
5.  Add Service Request to Queue
6.  Process Next Service Request
7.  Display Recent Actions using Stack
8.  Display Students using BST
9.  Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS
16. Traverse Campus Locations using DFS
17. Exit

---

## How to Run

1. Open the project in Eclipse (or any Java IDE).
2. Run app/Main.java as a Java Application.
3. Use the on-screen menu (options 1-17) to interact with the system.
4. Select option 17 to exit the application.

---

## Testing

Both BFS and DFS were tested on the same campus graph starting from the location "Library", to confirm each traversal produces the correct, expected order:

- DFS Traversal (Option 16): Library, Canteen, Auditorium, Admin Block. DFS goes as deep as possible before backtracking, which matches the expected output.
- BFS Traversal (Option 15): Library, Canteen, Admin Block, Auditorium. BFS visits Library's direct neighbours first before moving further out into the network, which matches the expected output.

The following cases were also tested and handled correctly:

- Adding a student with a duplicate ID is rejected with an error message.
- Searching for a Student ID that does not exist returns a "not found" message.
- Deleting a record that does not exist is handled without crashing.
- Entering an invalid or non-existent location name for BFS/DFS returns a clear error message without crashing the program.
- Adding a connection between two locations that do not exist is rejected.
- Marks outside the range 0-100 are rejected during input validation.
- Non-numeric input at menu prompts is handled without crashing.

---

CIT300 - Data Structures and Algorithms
Sri Lanka Technology Campus