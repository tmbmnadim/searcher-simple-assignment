package algs.sort;

public class RadixSort implements Sorter {
    private static final int BITS = 8;
    private static final int BUCKETS = 1 << BITS;
    private static final int MASK = BUCKETS - 1;

    private int[] items;

    public RadixSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    // LSD radix sort on 8-bit digits (4 passes for a 32-bit int).
    // Flipping the sign bit makes negative numbers order correctly before positive ones.
    @Override
    public void sortAsc() {
        if (items.length < 2) {
            return;
        }

        int[] buffer = new int[items.length];
        for (int shift = 0; shift < Integer.SIZE; shift += BITS) {
            countingPass(buffer, shift);
        }
    }

    @Override
    public void sortDesc() {
        sortAsc();
        for (int i = 0, j = items.length - 1; i < j; i++, j--) {
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
    }

    // Stable counting sort on a single digit
    private void countingPass(int[] buffer, int shift) {
        int[] count = new int[BUCKETS + 1];

        for (int item : items) {
            count[digit(item, shift) + 1]++;
        }
        for (int d = 0; d < BUCKETS; d++) {
            count[d + 1] += count[d];
        }
        for (int item : items) {
            buffer[count[digit(item, shift)]++] = item;
        }

        System.arraycopy(buffer, 0, items, 0, items.length);
    }

    private int digit(int value, int shift) {
        return ((value ^ Integer.MIN_VALUE) >>> shift) & MASK;
    }
}
