
import algs.search.*;
import algs.sort.*;
import graph.*;
import input.KeyResult;
import input.UserInput;
import stopwatch.Stopwatch;

import java.util.List;

class Main {

    private enum Nav {
        MAIN_MENU, QUIT
    }

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        String[] topMenu = { "Searching Algorithm", "Sorting Algorithm", "Graph", "Quit" };

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
            } else if (topChoice == 2) {
                if (runGraph(ui) == Nav.QUIT) {
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

    private static Nav runGraph(UserInput ui) {
        ui.clearScreen();
        Graph graph = new Graph(ui.getInputForDirected());

        for (String node : ui.getInputForNodes()) {
            addNode(graph, node);
        }

        String[] edge = ui.getInputForEdge();
        while (edge != null) {
            addEdge(graph, edge);
            edge = ui.getInputForEdge();
        }
        printGraph(graph);

        String[] actions = { "Print graph", "Add node", "Add edge", "Remove edge", "Run algorithm",
                "Main Menu", "Quit" };

        while (true) {
            int action = ui.getInputForChoices("Choose an action:", actions);

            if (action == 0) {
                printGraph(graph);
            } else if (action == 1) {
                addNode(graph, ui.getInputForNode("Enter the node name:"));
            } else if (action == 2) {
                edge = ui.getInputForEdge();
                if (edge != null) {
                    addEdge(graph, edge);
                }
            } else if (action == 3) {
                edge = ui.getInputForEdge();
                if (edge != null) {
                    removeEdge(graph, edge);
                }
            } else if (action == 4) {
                runTraversal(ui, graph);
            } else if (action == 5) {
                return Nav.MAIN_MENU;
            } else {
                return Nav.QUIT;
            }
        }
    }

    private static void runTraversal(UserInput ui, Graph graph) {
        String[] labels = GraphAlgorithm.getLabels();
        String[] menu = new String[labels.length + 1];
        System.arraycopy(labels, 0, menu, 0, labels.length);
        menu[labels.length] = "Back";

        int choice = ui.getInputForChoices("Select a Graph Algorithm from below:", menu);
        if (choice == labels.length) {
            return;
        }

        GraphAlgorithm choosen = GraphAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        String start = ui.getInputForNode("Enter the start node:");
        if (!graph.hasNode(start)) {
            System.out.println("Unknown node: " + start);
            return;
        }

        List<String> order = choosen.create().traverse(graph, start);
        System.out.println("Visit order: " + String.join(" -> ", order));
    }

    private static void printGraph(Graph graph) {
        System.out.println("Graph (" + (graph.isDirected() ? "directed" : "undirected") + "):");
        System.out.println(graph);
    }

    private static void addNode(Graph graph, String node) {
        try {
            if (!graph.addNode(node)) {
                System.out.println("Node already exists: " + node);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEdge(Graph graph, String[] edge) {
        try {
            graph.addEdge(edge[0], edge[1]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeEdge(Graph graph, String[] edge) {
        try {
            graph.removeEdge(edge[0], edge[1]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
