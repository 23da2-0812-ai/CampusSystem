package structures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Graph representing the campus locations and the roads/connections
 * between them. Implemented using an adjacency list.
 *
 * @author SAMA (23DA2-0938)
 */
public class CampusGraph {

    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>();
    }

    /** Adds a new campus location (vertex). Returns false if it already exists. */
    public boolean addLocation(String name) {
        if (adjacencyList.containsKey(name)) {
            return false;
        }
        adjacencyList.put(name, new ArrayList<>());
        return true;
    }

    /** Removes a campus location and any connections referencing it. */
    public boolean removeLocation(String name) {
        if (!adjacencyList.containsKey(name)) {
            return false;
        }
        adjacencyList.remove(name);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(name);
        }
        return true;
    }

    /** Adds an undirected connection/road between two existing locations. */
    public boolean addConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            return false;
        }
        if (adjacencyList.get(loc1).contains(loc2)) {
            return false;
        }
        adjacencyList.get(loc1).add(loc2);
        adjacencyList.get(loc2).add(loc1);
        return true;
    }

    /** Removes the connection/road between two locations. */
    public boolean removeConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            return false;
        }
        boolean removed1 = adjacencyList.get(loc1).remove(loc2);
        boolean removed2 = adjacencyList.get(loc2).remove(loc1);
        return removed1 || removed2;
    }

    /** Displays every location and its connected neighbours. */
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " -> " + adjacencyList.get(location));
        }
    }

    /**
     * Performs a Breadth-First Search (BFS) traversal starting from the given location.
     * Uses a Queue (FIFO) to visit nearest neighbours first, level by level.
     *
     * @param start the starting location name
     * @return a list of locations in the order they were visited
     */
    public List<String> bfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) {
            return visitedOrder;
        }

        Set<String> visited = new java.util.HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitedOrder.add(current);

            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visitedOrder;
    }

    /**
     * Performs a Depth-First Search (DFS) traversal starting from the given location.
     * Uses an explicit Stack (iterative approach, avoids recursion stack overflow
     * on very large graphs) to go as deep as possible before backtracking.
     *
     * @param start the starting location name
     * @return a list of locations in the order they were visited
     */
    public List<String> dfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) {
            return visitedOrder;
        }

        Set<String> visited = new java.util.HashSet<>();
        java.util.Deque<String> stack = new java.util.ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                visitedOrder.add(current);

                List<String> neighbours = adjacencyList.get(current);
                // Push in reverse order so neighbours are visited in the same
                // natural (left-to-right) order they were added.
                for (int i = neighbours.size() - 1; i >= 0; i--) {
                    String neighbour = neighbours.get(i);
                    if (!visited.contains(neighbour)) {
                        stack.push(neighbour);
                    }
                }
            }
        }
        return visitedOrder;
    }

    /** Returns true if the given location exists in the graph. */
    public boolean hasLocation(String name) {
        return adjacencyList.containsKey(name);
    }

    /** Returns true if the graph has no locations. */
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}