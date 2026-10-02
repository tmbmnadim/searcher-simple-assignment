package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final boolean directed;
    // LinkedHashMap keeps nodes in the order they were entered
    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }

    // Returns false if the node already exists
    public boolean addNode(String node) {
        if (node == null || !node.matches("\\S+")) {
            throw new IllegalArgumentException("Node names cannot be empty or contain spaces");
        }
        if (adjacency.containsKey(node)) {
            return false;
        }
        adjacency.put(node, new ArrayList<>());
        return true;
    }

    public boolean hasNode(String node) {
        return adjacency.containsKey(node);
    }

    public void addEdge(String from, String to) {
        requireNode(from);
        requireNode(to);
        if (from.equals(to)) {
            throw new IllegalArgumentException("Self-loops are not allowed: " + from);
        }
        if (adjacency.get(from).contains(to)) {
            throw new IllegalArgumentException("Edge already exists: " + edgeLabel(from, to));
        }

        adjacency.get(from).add(to);
        if (!directed) {
            adjacency.get(to).add(from);
        }
    }

    public void removeEdge(String from, String to) {
        requireNode(from);
        requireNode(to);
        if (!adjacency.get(from).contains(to)) {
            throw new IllegalArgumentException("No such edge: " + edgeLabel(from, to));
        }

        adjacency.get(from).remove(to);
        if (!directed) {
            adjacency.get(to).remove(from);
        }
    }

    public List<String> neighbors(String node) {
        requireNode(node);
        return Collections.unmodifiableList(adjacency.get(node));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : adjacency.entrySet()) {
            if (sb.length() > 0) {
                sb.append(System.lineSeparator());
            }
            List<String> neighbors = entry.getValue();
            sb.append(entry.getKey()).append(" -> ");
            sb.append(neighbors.isEmpty() ? "(none)" : String.join(", ", neighbors));
        }
        return sb.toString();
    }

    private void requireNode(String node) {
        if (!adjacency.containsKey(node)) {
            throw new IllegalArgumentException("Unknown node: " + node);
        }
    }

    private String edgeLabel(String from, String to) {
        return from + (directed ? " -> " : " - ") + to;
    }
}
