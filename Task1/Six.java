package Task1;

import java.util.Random;
import java.util.Scanner;

public class Six {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int max = 100;
        int min = 1;

        boolean playAgain = true;

        while (playAgain) {
            int attempts = 0;
            int randomNumber = random.nextInt(max - min + 1) + min;

            while (true) {
                attempts++;

                System.out.println("Attempt #" + attempts);
                System.out.println("Guess the number between " + min + " and " + max + ": ");
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            String playAgainChoice = scanner.next().toLowerCase();
            playAgain = playAgainChoice.equals("yes");
        }

        scanner.close();
    }
}
