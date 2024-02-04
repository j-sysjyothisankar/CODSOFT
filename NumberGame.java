import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10;
        int rounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Game!");
        System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound);

        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int numberOfAttempts = 0;
            boolean hasGuessedCorrectly = false;

            while (!hasGuessedCorrectly && numberOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;
                totalAttempts++;

                if (userGuess == randomNumber) {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the number in " + numberOfAttempts + " attempts.");
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
            rounds++;
        }

        System.out.println("Thank you for playing!");
        System.out.println("Your total score is: " + (rounds - totalAttempts));
    }
}
