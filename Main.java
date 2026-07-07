import algs.*;
import input.UserInput;

class Main {
    public static void main(String[] args) {
        int[] items = UserInput.getInputForArray();
        int key  = UserInput.getInputForKey();
        
        Searcher ls = new LinearSearch(items);
        Searcher bs = new BinarySearch(items);
        
        ls.search(key);
        bs.search(key);
    }
}