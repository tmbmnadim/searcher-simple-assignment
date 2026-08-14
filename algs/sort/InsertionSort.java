package algs.sort;

public class InsertionSort implements Sorter {
    private int[] items;

    public InsertionSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        if (items.length == 0) {
            return;
        }

        for (int i = 1; i < items.length; i++) {
            int key = items[i];
            int j = i - 1;
            while (j >= 0 && items[j] > key) {
                items[j + 1] = items[j];
                j--;
            }
            items[j + 1] = key;

        }
    }

    @Override
    public void sortDesc() {
        if (items.length == 0) {
            return;
        }

        for (int i = 1; i < items.length; i++) {
            int key = items[i];
            int j = i - 1;
            while (j >= 0 && items[j] < key) {
                items[j + 1] = items[j];
                j--;
            }
            items[j + 1] = key;

        }
    }

}
