package pl.maciejpajak.guessANumber;

import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * little game: computer generates random number, which user is trying to guess
     * @param args
     */
    public static void main(String[] args) {
        int rand = getRandomInt(101);
        int guess;
        
        Scanner scan = new Scanner(System.in);
        
        while ( (guess = getIntFromScanner(scan)) != rand ) {
            if (guess > rand) {
                System.out.println("Too much!");
            } else {
                System.out.println("Too little!");
            }
        }
        System.out.println("Correct number!");
        
    }    
    
    /**
     * returns random integer < limit
     * @param limit
     * @return
     */
    private static int getRandomInt(int limit) {
        Random r = new Random();
        return r.nextInt(limit);
    }
    
    /**
     * reads input until integer is received
     * @param scan - Scanner object
     * @return - integer
     */
    private static int getIntFromScanner(Scanner scan) {
        System.out.print("Guess a number: ");
        while ( !scan.hasNextInt() ) {
            System.out.print(scan.next() + " is not a valid integer, try again: ");
        }
        return scan.nextInt();
    }

}
