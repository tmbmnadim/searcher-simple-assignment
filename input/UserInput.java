package input;

import java.util.Scanner;

public class UserInput {
    final Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public int getInputForChoices(String prompt, String[] options) {
        // SHOWING OPTIONS TO CHOOSE FROM
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        System.out.println("Type the number you want to select: ");

        // TAKING INPUT FOR SEARCH ALGORITHM CHOICE
        String choice = scanner.nextLine();
        int i = 0;
        while (!isValidChoice(choice, options.length)) {
            System.out.println("Please enter a valid choice (1-" + options.length + "):");
            choice = scanner.nextLine();
            i++;
            if (i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }
        return Integer.parseInt(choice.trim()) - 1;
    }

    public int[] getInputForArray() {
        String input = takeInput();
        int i = 0;
        while (input.trim().isEmpty()) {
            System.out.println("Input cannot be empty. Please enter the elements of the array (space-separated):");
            input = takeInput();
            i++;
            if (i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }

        // CONVERTING INPUT STRING TO INTEGER ARRAY
        String[] inputArr = input.split(" ");
        int[] numbers = new int[inputArr.length];
        for (int j = 0; j < numbers.length; j++) {
            numbers[j] = Integer.parseInt(inputArr[j]);
        }
        return numbers;
        // return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
    }

    public KeyResult getInputForKey() {
        System.out.println("Enter the key to search for ('m' for Main Menu, 'q' to Quit):");
        String line = scanner.nextLine().trim();
        if (line.equalsIgnoreCase("q") || line.equalsIgnoreCase("quit")) {
            return KeyResult.ofQuit();
        }
        if (line.equalsIgnoreCase("m") || line.equalsIgnoreCase("menu")) {
            return KeyResult.ofMainMenu();
        }
        try {
            return KeyResult.ofKey(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer, 'm' for Main Menu, or 'q' to Quit.");
            return getInputForKey();
        }
    }

    public boolean getInputForDirected() {
        System.out.println("Should the graph be directed? (y/N):");
        String line = scanner.nextLine().trim();
        return line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes");
    }

    public String[] getInputForNodes() {
        System.out.println("Enter the node names (space-separated):");
        String input = scanner.nextLine();
        int i = 0;
        while (input.trim().isEmpty()) {
            System.out.println("Input cannot be empty. Please enter the node names (space-separated):");
            input = scanner.nextLine();
            i++;
            if (i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }
        return input.trim().split("\\s+");
    }

    // Returns {from, to}, or null when the user is done
    public String[] getInputForEdge() {
        while (true) {
            System.out.println("Enter an edge as \"from to\" (blank or 'q' to stop):");
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.equalsIgnoreCase("q")) {
                return null;
            }
            String[] parts = line.split("\\s+");
            if (parts.length == 2) {
                return parts;
            }
            System.out.println("Please enter exactly two node names, e.g. A B");
        }
    }

    public String getInputForNode(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public void dispose() {
        scanner.close();
    }

    private boolean isValidChoice(String choice, int optionCount) {
        String trimmed = choice.trim();
        if (!trimmed.matches("\\d{1,9}")) {
            return false;
        }
        int number = Integer.parseInt(trimmed);
        return number >= 1 && number <= optionCount;
    }

    private String takeInput() {
        // TAKING INPUT FROM USER
        System.out.println("Enter the elements of the array (space-separated):");
        String input = scanner.nextLine();
        return input;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
