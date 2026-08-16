package algs.sort;

public class RadixSort implements Sorter {
    private int[] items;

    public RadixSort(int[] items) {
        this.items = items;
    }

    @Override
    public int[] getItems() {
        return items;
    }

    @Override
    public void sortAsc() {
        items = radixSort(items, true);
    }

    @Override
    public void sortDesc() {
        items = radixSort(items, false);
    }

    private int[] radixSort(int[] arr, boolean ascending) {
        if (arr.length == 0) {
            return arr;
        }

        int negativeCount = 0;
        for (int value : arr) {
            if (value < 0) {
                negativeCount++;
            }
        }

        int[] negatives = new int[negativeCount];
        int[] nonNegatives = new int[arr.length - negativeCount];
        int ni = 0, pi = 0;
        for (int value : arr) {
            if (value < 0) {
                negatives[ni++] = -value;
            } else {
                nonNegatives[pi++] = value;
            }
        }

        int[] sortedNegatives = sortNonNegative(negatives);
        int[] sortedNonNegatives = sortNonNegative(nonNegatives);

        int[] result = new int[arr.length];
        int idx = 0;
        if (ascending) {
            for (int i = sortedNegatives.length - 1; i >= 0; i--) {
                result[idx++] = -sortedNegatives[i];
            }
            for (int value : sortedNonNegatives) {
                result[idx++] = value;
            }
        } else {
            for (int i = sortedNonNegatives.length - 1; i >= 0; i--) {
                result[idx++] = sortedNonNegatives[i];
            }
            for (int value : sortedNegatives) {
                result[idx++] = -value;
            }
        }

        return result;
    }

    private int[] sortNonNegative(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }

        int[] current = arr.clone();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            current = countingSortByDigit(current, exp);
        }

        return current;
    }

    private int[] countingSortByDigit(int[] arr, int exp) {
        int[] result = new int[arr.length];
        int[] counts = new int[10];

        for (int value : arr) {
            counts[(value / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            result[--counts[digit]] = arr[i];
        }

        return result;
    }

}
