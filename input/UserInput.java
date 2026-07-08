package input;

import java.util.Scanner;

public class UserInput {
    final Scanner scanner;
    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public int getInputForSearchAlgs(String[] options) {
        // SHOWING OPTIONS FOR SEARCH ALGORITHMS
        System.out.println("Choose which algorithm you want to use:");
        for (String option : options) {
            System.out.println(option);
        }

        System.out.println("Input: ");
        
        // TAKING INPUT FOR SEARCH ALGORITHM CHOICE
        String choice = scanner.nextLine();
        int i = 0;
        while(choice.trim().isEmpty() || !choice.matches("\\d+")) {
            System.out.println("Please enter a valid choice (1-" + options.length + "):");
            choice = scanner.nextLine();
            i++;
            if(i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }
        return Integer.parseInt(choice) - 1;
    }

    public int[] getInputForArray() {
        String input = takeInput();
        int i = 0;
        while(input.trim().isEmpty()) {
            System.out.println("Input cannot be empty. Please enter the elements of the array (space-separated):");
            input = takeInput();
            i++;
            if(i > 2) {
                System.out.println("Too many invalid attempts. Exiting.");
                System.exit(1);
            }
        }
        

        // CONVERTING INPUT STRING TO INTEGER ARRAY
        String[] inputArr = input.split(" ");
        int[] numbers = new int[inputArr.length];
        for(int j = 0; j < numbers.length; j++){
            numbers[j] = Integer.parseInt(inputArr[j]);
        }
        return numbers;
        // return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
    }

    public  int getInputForKey() {
        System.out.println("Enter the key to search for:");
        int key = scanner.nextInt();
        return key;
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
}
