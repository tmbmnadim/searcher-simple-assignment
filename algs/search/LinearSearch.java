package algs.search;

public class LinearSearch implements Searcher {
    private int[] items;
    public LinearSearch(int[] items){
        this.items = items;
    }

    @Override
    public int search(int key) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == key) {
                System.out.println("Item found at index: " + i);
                return i;
            }
        }
        System.out.println("Item not found");
        return -1;
    }
}