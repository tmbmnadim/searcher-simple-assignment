package graph;

import java.util.List;

public interface GraphTraverser {
    // Returns the nodes reachable from start, in the order they are visited
    List<String> traverse(Graph graph, String start);
}
