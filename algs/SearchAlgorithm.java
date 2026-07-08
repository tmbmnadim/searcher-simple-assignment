package algs;
import java.util.function.Function;

public enum SearchAlgorithm {
    LINEAR("Linear Search", LinearSearch::new),
    BINARY("Binary Search", BinarySearch::new);

    final String label;
    final Function<int[], Searcher> factory;
    private SearchAlgorithm(String label, Function<int[], Searcher> factory) {
        this.label = label;
        this.factory = factory;
    }

    public int getId() {
        return ordinal() + 1;
    }

    public String getlabel() {
        return label;
    }

    public Searcher create(int[] items) {
        return factory.apply(items);
    }

    public static String[] getListStrings() {
        String[] algorithms = new String[values().length];
        for (int i = 0; i < algorithms.length; i++) {
            SearchAlgorithm alg = values()[i];
            algorithms[i] = alg.getId() + ". " + alg.getlabel();
        }
        return algorithms;
    }
}