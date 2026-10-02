package algs.sort;

public class CountingSort implements Sorter {
    private int[] items;

    public CountingSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        items = countingSort(items, true);
    }

    @Override
    public void sortDesc() {
        items = countingSort(items, false);
    }

    private int[] countingSort(int[] arr, boolean ascending) {
        if (arr.length == 0) {
            return arr;
        }

        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        int[] counts = new int[max - min + 1];
        for (int value : arr) {
            counts[value - min]++;
        }

        int[] result = new int[arr.length];
        int idx = 0;
        if (ascending) {
            for (int i = 0; i < counts.length; i++) {
                for (int c = 0; c < counts[i]; c++) {
                    result[idx++] = i + min;
                }
            }
        } else {
            for (int i = counts.length - 1; i >= 0; i--) {
                for (int c = 0; c < counts[i]; c++) {
                    result[idx++] = i + min;
                }
            }
        }

        return result;
    }

}
