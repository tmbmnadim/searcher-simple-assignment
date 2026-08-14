
import algs.search.*;
import algs.sort.*;
import input.UserInput;
import stopwatch.Stopwatch;

class Main {

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        String[] algs = { "1. Search", "2. Sort" };

        int choice = 0;
        int algsChoice = ui.getInputForChoices(algs);

        if (algsChoice == 0) {
            choice = ui.getInputForChoices(SearchAlgorithm.getListStrings());

            SearchAlgorithm choosen = SearchAlgorithm.values()[choice];
            System.out.println("Your choice: " + choosen.getlabel());

            int[] items = ui.getInputForArray();
            Searcher searcher = choosen.create(items);

            Integer key = ui.getInputForKey();

            while (key != null) {
                searcher.search(key);

                key = ui.getInputForKey();
            }
        } else if (algsChoice == 1) {
            choice = ui.getInputForChoices(SortAlgorithm.getListStrings());

            SortAlgorithm choosen = SortAlgorithm.values()[choice];
            System.out.println("Your choice: " + choosen.getlabel());

            int[] items = ui.getInputForArray();
            Sorter sorter = choosen.create(items);

            sorter.sortAsc();
            sorter.print();

            
        } else {
            System.out.println("Invalid choice");
        }

        ui.dispose();
    }
}
