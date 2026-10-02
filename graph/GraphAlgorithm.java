package graph;
import java.util.function.Supplier;

public enum GraphAlgorithm {
    BFS("Breadth-First Search", BreadthFirst::new),
    DFS("Depth-First Search", DepthFirst::new);

    final String label;
    final Supplier<GraphTraverser> factory;
    private GraphAlgorithm(String label, Supplier<GraphTraverser> factory) {
        this.label = label;
        this.factory = factory;
    }

    public int getId() {
        return ordinal() + 1;
    }

    public String getlabel() {
        return label;
    }

    public GraphTraverser create() {
        return factory.get();
    }

    public static String[] getListStrings() {
        String[] algorithms = new String[values().length];
        for (int i = 0; i < algorithms.length; i++) {
            GraphAlgorithm alg = values()[i];
            algorithms[i] = alg.getId() + ". " + alg.getlabel();
        }
        return algorithms;
    }
}
