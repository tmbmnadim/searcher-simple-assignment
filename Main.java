
import algs.search.*;
import algs.sort.*;
import input.KeyResult;
import input.UserInput;
import stopwatch.Stopwatch;

class Main {

    private enum Nav {
        MAIN_MENU, QUIT
    }

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        String[] topMenu = { "Searching Algorithm", "Sorting Algorithm", "Quit" };

        while (true) {
            ui.clearScreen();
            int topChoice = ui.getInputForChoices("What kind of algorithm do you want to use?", topMenu);

            if (topChoice == 0) {
                if (runSearch(ui) == Nav.QUIT) {
                    break;
                }
            } else if (topChoice == 1) {
                if (runSort(ui) == Nav.QUIT) {
                    break;
                }
            } else {
                break;
            }
        }

        ui.dispose();
        System.out.println("Goodbye!");
    }

    private static Nav runSearch(UserInput ui) {
        String[] menu = withNavOptions(SearchAlgorithm.getLabels());
        int choice = ui.getInputForChoices("Select a Searching Algorithm from below:", menu);
        int algCount = SearchAlgorithm.values().length;

        if (choice == algCount) {
            return Nav.MAIN_MENU;
        }
        if (choice == algCount + 1) {
            return Nav.QUIT;
        }

        ui.clearScreen();
        SearchAlgorithm choosen = SearchAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        int[] items = ui.getInputForArray();
        Searcher searcher = choosen.create(items);

        while (true) {
            KeyResult result = ui.getInputForKey();
            if (result.quit()) {
                return Nav.QUIT;
            }
            if (result.mainMenu()) {
                return Nav.MAIN_MENU;
            }
            searcher.search(result.key());
        }
    }

    private static Nav runSort(UserInput ui) {
        String[] menu = withNavOptions(SortAlgorithm.getLabels());
        int choice = ui.getInputForChoices("Select a Sorting Algorithm from below:", menu);
        int algCount = SortAlgorithm.values().length;

        if (choice == algCount) {
            return Nav.MAIN_MENU;
        }
        if (choice == algCount + 1) {
            return Nav.QUIT;
        }

        ui.clearScreen();
        SortAlgorithm choosen = SortAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        int[] items = ui.getInputForArray();
        Sorter sorter = choosen.create(items);

        String[] directionMenu = { "Ascending", "Descending", "Main Menu", "Quit" };
        while (true) {
            int dirChoice = ui.getInputForChoices("Select a sorting order:", directionMenu);
            if (dirChoice == 0) {
                sorter.sortAsc();
                sorter.print();
            } else if (dirChoice == 1) {
                sorter.sortDesc();
                sorter.print();
            } else if (dirChoice == 2) {
                return Nav.MAIN_MENU;
            } else {
                return Nav.QUIT;
            }
        }
    }

    private static String[] withNavOptions(String[] options) {
        String[] extended = new String[options.length + 2];
        System.arraycopy(options, 0, extended, 0, options.length);
        extended[options.length] = "Main Menu";
        extended[options.length + 1] = "Quit";
        return extended;
    }
}
