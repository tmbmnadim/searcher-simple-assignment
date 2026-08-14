package algs.sort;
import java.util.function.Function;

public enum SortAlgorithm {
    BUBBLE("Bubble Sort", BubbleSort::new),
    INSERTION("Insertion Sort", InsertionSort::new),
    SELECTION("Selection Sort", SelectionSort::new);

    final String label;
    final Function<int[], Sorter> factory;
    private SortAlgorithm(String label, Function<int[], Sorter> factory) {
        this.label = label;
        this.factory = factory;
    }

    public int getId() {
        return ordinal() + 1;
    }

    public String getlabel() {
        return label;
    }

    public Sorter create(int[] items) {
        return factory.apply(items);
    }

    public static String[] getListStrings() {
        String[] algorithms = new String[values().length];
        for (int i = 0; i < algorithms.length; i++) {
            SortAlgorithm alg = values()[i];
            algorithms[i] = alg.getId() + ". " + alg.getlabel();
        }
        return algorithms;
    }
}