package Task1;

import java.util.Random;
import java.util.Scanner;

public class Four {
    public static void main(String[] args) {
        Random random = new Random();

        int min = 1;
        int max = 100;

        int randomNumber = random.nextInt(max - min + 1) + min;

        Scanner scanner = new Scanner(System.in);

        int userGuess;
        boolean correctGuess = false;

        while (!correctGuess) {
            System.out.println("Guess the number between " + min + " and " + max + ": ");
            userGuess = scanner.nextInt();

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                correctGuess = true;
            } else if (userGuess < randomNumber) {
                System.out.println("Your guess is too low. Try again.");
            } else {
                System.out.println("Your guess is too high. Try again.");
            }
        }

        scanner.close();
    }
}
