package algs.sort;

public class SelectionSort implements Sorter {
    private int[] items;

    public SelectionSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        for (int i = 0; i < items.length; i++) {
            int keyIdx = i;
            int j = i + 1;
            while (j < items.length) {
                if (items[j] < items[keyIdx]) {
                    keyIdx = j;
                }
                j++;
            }
            int temp = items[i];
            items[i] = items[keyIdx];
            items[keyIdx] = temp;
        }
    }

    @Override
    public void sortDesc() {
        for (int i = 0; i < items.length; i++) {
            int keyIdx = i;
            int j = i + 1;
            while (j < items.length) {
                if (items[j] > items[keyIdx]) {
                    keyIdx = j;
                }
                j++;
            }
            int temp = items[i];
            items[i] = items[keyIdx];
            items[keyIdx] = temp;
        }
    }

}
