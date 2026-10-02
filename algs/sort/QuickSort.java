package algs.sort;

public class QuickSort implements Sorter {
    private int[] items;

    public QuickSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        quickSort(0, items.length - 1, true);
    }

    @Override
    public void sortDesc() {
        quickSort(0, items.length - 1, false);
    }

    private void quickSort(int start, int end, boolean asc) {
        while (start < end) {
            int split = partition(start, end, asc);

            // Recurse into the smaller side and loop on the larger one,
            // so the recursion depth stays O(log n)
            if (split - start < end - split) {
                quickSort(start, split, asc);
                start = split + 1;
            } else {
                quickSort(split + 1, end, asc);
                end = split;
            }
        }
    }

    // Hoare partition: returns j so that [start..j] and [j+1..end] are sorted independently.
    // The middle pivot keeps sorted input fast and handles many equal values well.
    private int partition(int start, int end, boolean asc) {
        int pivot = items[start + (end - start) / 2];
        int i = start - 1;
        int j = end + 1;

        while (true) {
            do {
                i++;
            } while (before(items[i], pivot, asc));
            do {
                j--;
            } while (before(pivot, items[j], asc));

            if (i >= j) {
                return j;
            }

            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
    }

    private boolean before(int a, int b, boolean asc) {
        return asc ? a < b : a > b;
    }
}
