package Task1;

import java.util.Random;
import java.util.Scanner;

public class Five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = 100;

        int min = 1;

        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;

        int userGuess;
        int attempts = 0; // Initialize the attempts counter

        while (true) {
            attempts++; // Increment the attempts counter

            System.out.println("Attempt #" + attempts);
            System.out.println("Guess the number between " + min + " and " + max + ": ");
            userGuess = scanner.nextInt();

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Your guess is too low. Try again.");
            } else {
                System.out.println("Your guess is too high. Try again.");
            }
        }

        scanner.close();
    }
}
