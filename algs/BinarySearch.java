package algs;
import java.util.Scanner;

public class BinarySearch {
    private int[] items;
    public BinarySearch(int[] items){
        this.items = items;
    }
    
    public void search(int key) {
        if(items.length < 1) {
            System.out.println("Cannot Search in an empty list");
            return;
        }
        if(items.length == 1) {
            System.out.println("Value in 0th index");
            return;
        }
        int start = 0;
        int end = items.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            System.out.printf("START: %02d | MID: %02d | END: %02d | KEY: %02d\n", start, mid, end, key);
            if(items[mid] == key){
                System.out.println("Value in " + mid + " index");
                break;
            } else if (key < items[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}