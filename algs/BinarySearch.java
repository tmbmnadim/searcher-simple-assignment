package algs;
import java.util.Scanner;

public class BinarySearch extends Searcher {
    private int[] items;
    public BinarySearch(int[] items){
        this.items = items;
    }
    
    @Override
    public int search(int key) {
        if(items.length < 1) {
            System.out.println("Cannot Search in an empty list");
            return -1;
        }
        if(items.length == 1) {
            System.out.println("Value in 0th index");
            return 0;
        }
        int start = 0;
        int end = items.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if(items[mid] == key){
                System.out.println("Value in " + mid + " index");
                return mid;
            } else if (key < items[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("Value not found");
        return -1;
    }
}