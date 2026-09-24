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

    public boolean addLocation(String name) {
        if (adjacencyList.containsKey(name)) {
            return false;
        }
        adjacencyList.put(name, new ArrayList<>());
        return true;
    }

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

    public boolean removeConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            return false;
        }
        boolean removed1 = adjacencyList.get(loc1).remove(loc2);
        boolean removed2 = adjacencyList.get(loc2).remove(loc1);
        return removed1 || removed2;
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " -> " + adjacencyList.get(location));
        }
    }

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

    public boolean hasLocation(String name) {
        return adjacencyList.containsKey(name);
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}