package Task1;

import java.util.Random;
import java.util.Scanner;

public class Third {
    public static void main(String[] args) {
        Random random = new Random();

        int min = 1;
        int max = 100;

        int randomNumber = random.nextInt(max - min + 1) + min;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Guess the number between " + min + " and " + max + ": ");
        int userGuess = scanner.nextInt();

        if (userGuess == randomNumber) {
            System.out.println("Congratulations! You guessed the correct number.");
        } else if (userGuess < randomNumber) {
            System.out.println("Your guess is too low. The correct number was " + randomNumber);
        } else {
            System.out.println("Your guess is too high. The correct number was " + randomNumber);
        }

        scanner.close();
    }
}
