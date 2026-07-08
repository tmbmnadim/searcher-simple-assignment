
import algs.*;
import input.UserInput;
import stopwatch.Stopwatch;

class Main {

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        int choice = ui.getInputForSearchAlgs(SearchAlgorithm.getListStrings());

        SearchAlgorithm choosen = SearchAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        int[] items = ui.getInputForArray();
        Searcher searcher = choosen.create(items);

        int key = ui.getInputForKey();

        Stopwatch stopwatch = new Stopwatch();
        searcher.search(key);
        double time = stopwatch.elapsedTime();
        System.out.printf("Elapsed time: %.5f\n", time);

        ui.dispose();
    }
}
