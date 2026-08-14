package algs.sort;

public interface Sorter {
    int[] getItems();
    public abstract void sortAsc();
    public abstract void sortDesc();
    public default void print() {
        int[] items = getItems();
        System.out.print("[");
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]);
            if (i < items.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
