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
        while (choice.trim().isEmpty() || !choice.matches("\\d+")
                || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > options.length) {
            System.out.println("Please enter a valid choice (1-" + options.length + "):");
            choice = scanner.nextLine();
            i++;
            if (i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }
        return Integer.parseInt(choice) - 1;
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

    public void dispose() {
        scanner.close();
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
