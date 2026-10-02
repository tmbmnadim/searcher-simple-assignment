package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BreadthFirst implements GraphTraverser {

    @Override
    public List<String> traverse(Graph graph, String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String node = queue.poll();
            order.add(node);
            for (String next : graph.neighbors(node)) {
                if (visited.add(next)) {
                    queue.add(next);
                }
            }
        }
        return order;
    }
}
