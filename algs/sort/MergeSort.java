package algs.sort;

public class MergeSort implements Sorter {
    private int[] items;

    public MergeSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        items = mergeSort(items, true);
    }

    @Override
    public void sortDesc() {
        items = mergeSort(items, false);
    }

    private int[] mergeSort(int[] arr, boolean ascending) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] left = mergeSort(java.util.Arrays.copyOfRange(arr, 0, mid), ascending);
        int[] right = mergeSort(java.util.Arrays.copyOfRange(arr, mid, arr.length), ascending);

        return merge(left, right, ascending);
    }

    private int[] merge(int[] left, int[] right, boolean ascending) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            boolean takeLeft = ascending ? left[i] <= right[j] : left[i] >= right[j];
            if (takeLeft) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

}
