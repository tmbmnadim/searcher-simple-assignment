package algs;

public class LinearSearch extends Searcher {
    private int[] items;
    public LinearSearch(int[] items){
        this.items = items;
    }

    @Override
    public int search(int key) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == key) {
                System.out.println("Value in " + i + " index");
                return i;
            }
        }
        System.out.println("Value not found");
        return -1;
    }
}