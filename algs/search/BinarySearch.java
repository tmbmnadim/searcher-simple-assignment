package algs.search;

public class BinarySearch implements Searcher {
    private int[] items;
    public BinarySearch(int[] items){
        this.items = items;
    }
    
    @Override
    public int search(int key) {
        int start = 0;
        int end = items.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(items[mid] == key){
                System.out.println("Item found at index: " + mid);
                return mid;
            } else if (key < items[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("Item not found");
        return -1;
    }
}
