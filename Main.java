import algs.*;
import input.UserInput;
import stopwatch.Stopwatch;

class Main {
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        UserInput ui = new UserInput();

        int choice = ui.getInputForSearchAlgs();
        int[] items = ui.getInputForArray();
        int key  = ui.getInputForKey();
        Searcher searcher;

        if(choice == 1) {
            searcher = new LinearSearch(items);
            System.out.println("You chose Linear Search");
        } else if(choice == 2) {
            searcher = new BinarySearch(items);
            System.out.println("You chose Binary Search");
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }
        
        searcher.search(key);
        double time = stopwatch.elapsedTime();
        System.out.printf("Elapsed time: %.5f\n",time);

        ui.dispose();
    }
}