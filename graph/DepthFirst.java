package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirst implements GraphTraverser {

    @Override
    public List<String> traverse(Graph graph, String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        stack.push(start);
        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (!visited.add(node)) {
                continue;
            }
            order.add(node);

            // Pushed in reverse so the first neighbor is explored first
            List<String> neighbors = graph.neighbors(node);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                if (!visited.contains(neighbors.get(i))) {
                    stack.push(neighbors.get(i));
                }
            }
        }
        return order;
    }
}
