
import algs.search.*;
import algs.sort.*;
import graph.*;
import input.UserInput;
import stopwatch.Stopwatch;

import java.util.List;

class Main {

    public static void main(String[] args) {
        UserInput ui = new UserInput();

        String[] algs = { "1. Search", "2. Sort", "3. Graph" };

        int algsChoice = ui.getInputForChoices(algs);

        if (algsChoice == 0) {
            runSearch(ui);
        } else if (algsChoice == 1) {
            runSort(ui);
        } else {
            runGraph(ui);
        }

        ui.dispose();
    }

    private static void runSearch(UserInput ui) {
        int choice = ui.getInputForChoices(SearchAlgorithm.getListStrings());

        SearchAlgorithm choosen = SearchAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        int[] items = ui.getInputForArray();
        Searcher searcher = choosen.create(items);

        Integer key = ui.getInputForKey();

        while (key != null) {
            searcher.search(key);

            key = ui.getInputForKey();
        }
    }

    private static void runSort(UserInput ui) {
        int choice = ui.getInputForChoices(SortAlgorithm.getListStrings());

        SortAlgorithm choosen = SortAlgorithm.values()[choice];
        System.out.println("Your choice: " + choosen.getlabel());

        int[] items = ui.getInputForArray();
        Sorter sorter = choosen.create(items);

        sorter.sortAsc();
        sorter.print();
    }

    private static void runGraph(UserInput ui) {
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

        String[] actions = { "1. Print graph", "2. Add node", "3. Add edge", "4. Remove edge",
                "5. Run algorithm", "6. Quit" };

        int action = ui.getInputForChoices("Choose an action:", actions);
        while (action != 5) {
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
            } else {
                runTraversal(ui, graph);
            }

            action = ui.getInputForChoices("Choose an action:", actions);
        }
    }

    private static void runTraversal(UserInput ui, Graph graph) {
        int choice = ui.getInputForChoices(GraphAlgorithm.getListStrings());

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
}
