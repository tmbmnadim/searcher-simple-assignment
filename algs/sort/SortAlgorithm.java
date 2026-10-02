package algs.sort;
import java.util.function.Function;

public enum SortAlgorithm {
    BUBBLE("Bubble Sort", BubbleSort::new),
    INSERTION("Insertion Sort", InsertionSort::new),
    SELECTION("Selection Sort", SelectionSort::new),
    MERGE("Merge Sort", MergeSort::new),
    QUICK("Quick Sort", QuickSort::new),
    HEAP("Heap Sort", HeapSort::new),
    COUNTING("Counting Sort", CountingSort::new),
    RADIX("Radix Sort", RadixSort::new);

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

    public static String[] getLabels() {
        String[] algorithms = new String[values().length];
        for (int i = 0; i < algorithms.length; i++) {
            algorithms[i] = values()[i].getlabel();
        }
        return algorithms;
    }
}