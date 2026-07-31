
import algs.*;
import input.UserInput;
import stopwatch.Stopwatch;

class Main {

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        String[] algs = { "1. Search", "2. Sort" };

        int choice = 0;
        int algsChoice = ui.getInputForSearchAlgs(algs);

        if (algsChoice == 0) {
            choice = ui.getInputForSearchAlgs(SearchAlgorithm.getListStrings());

            SearchAlgorithm choosen = SearchAlgorithm.values()[choice];
            System.out.println("Your choice: " + choosen.getlabel());

            int[] items = ui.getInputForArray();
            Searcher searcher = choosen.create(items);

            int key = ui.getInputForKey();

            Stopwatch stopwatch = new Stopwatch();
            searcher.search(key);
            double time = stopwatch.elapsedTime();
            System.out.printf("Elapsed time: %.5f\n", time);
        } else if (algsChoice == 1) {
            choice = ui.getInputForSearchAlgs(SortAlgorithm.getListStrings());

            SortAlgorithm choosen = SortAlgorithm.values()[choice];
            System.out.println("Your choice: " + choosen.getlabel());

            int[] items = ui.getInputForArray();
            Sorter sorter = choosen.create(items);

            Stopwatch stopwatch = new Stopwatch();
            sorter.sort();
            double time = stopwatch.elapsedTime();
            System.out.printf("Elapsed time: %.5f\n", time);
        } else {
            System.out.println("Invalid choice");
        }

        ui.dispose();
    }
}
