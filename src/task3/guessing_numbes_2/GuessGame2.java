package task3.guessing_numbes_2;

import java.util.Scanner;

public class GuessGame2 {

    // declare global variables
    private static boolean loop = true;
    private static int min = 0;
    private static int max = 1000;
    
    /**
     * main method of the game
     * @param args
     */
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Let's play a game!");
        System.out.println("Think of a number from 0 to 1000 and I will guess it in max 10 attempts.");
        System.out.println("Press Enter when you are ready.");
        scan.nextLine();
        
        while ( loop ) {    // game loop
            
            System.out.println("Im guessing: " + guess());  // guess
            System.out.println("What will you say (too much, too little, you guessed)? ");
            analyzeAnswer( scan.nextLine() );               // get and analyze answer
            
        }     
       scan.close(); 
    }
    
    /**
     * calculates new guess
     * @return {@code int} guess number
     */
    private static int guess() {
        return (int) ( (max - min) / 2 + min);
    }
    
    /**
     * analyzes user answer {@code ans} and performs required action
     * @param ans
     */
    private static void analyzeAnswer(String ans) {
        
        switch( ans ) {
        case "too little":
            min = guess();
            break;
        case "too much":
            max = guess();
            break;
        case "you guessed":
            loop = false;
            System.out.println("It was a pleasure to play with you!");
            break;
        default:
            System.out.println("This is not a valid answer.");
            break;
        }
    }
    
}
