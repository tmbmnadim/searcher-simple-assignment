package algs.sort;

public class HeapSort implements Sorter {
    private int[] items;

    public HeapSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        sort(true);
    }

    @Override
    public void sortDesc() {
        sort(false);
    }

    // Ascending uses a max-heap, descending uses a min-heap.
    // The root is moved to the end each round, so the heap shrinks by one.
    private void sort(boolean asc) {
        int n = items.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(i, n, asc);
        }

        for (int end = n - 1; end > 0; end--) {
            swap(0, end);
            siftDown(0, end, asc);
        }
    }

    private void siftDown(int root, int size, boolean asc) {
        while (true) {
            int top = root;
            int left = 2 * root + 1;
            int right = left + 1;

            if (left < size && before(items[top], items[left], asc)) {
                top = left;
            }
            if (right < size && before(items[top], items[right], asc)) {
                top = right;
            }
            if (top == root) {
                return;
            }

            swap(root, top);
            root = top;
        }
    }

    private boolean before(int a, int b, boolean asc) {
        return asc ? a < b : a > b;
    }

    private void swap(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
