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
        sort(true);
    }

    @Override
    public void sortDesc() {
        sort(false);
    }

    private void sort(boolean asc) {
        if (items.length < 2) {
            return;
        }
        int[] buffer = new int[items.length];
        mergeSort(buffer, 0, items.length - 1, asc);
    }

    private void mergeSort(int[] buffer, int start, int end, boolean asc) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(buffer, start, mid, asc);
        mergeSort(buffer, mid + 1, end, asc);
        merge(buffer, start, mid, end, asc);
    }

    private void merge(int[] buffer, int start, int mid, int end, boolean asc) {
        int left = start;
        int right = mid + 1;
        int k = start;

        while (left <= mid && right <= end) {
            // Taking from the left on ties keeps the sort stable
            boolean takeLeft = asc ? items[left] <= items[right] : items[left] >= items[right];
            if (takeLeft) {
                buffer[k++] = items[left++];
            } else {
                buffer[k++] = items[right++];
            }
        }
        while (left <= mid) {
            buffer[k++] = items[left++];
        }
        while (right <= end) {
            buffer[k++] = items[right++];
        }

        System.arraycopy(buffer, start, items, start, end - start + 1);
    }
}
