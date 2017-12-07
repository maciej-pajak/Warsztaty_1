package task2.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LottoLists {

    public static void main(String[] args) {
        
        // TODO tests
        Scanner scan = new Scanner(System.in);
        List<Integer> choice = getIntListFromScanner(scan, 6, 1, 49);
        scan.close();
        List<Integer> draw = newDraw();
        
        System.out.println( "Your choice: " + Arrays.toString( choice.toArray() ) );
        System.out.println("System drew: " + Arrays.toString( draw.toArray()) );
        
        determineWin(countMatches(choice, draw), 3);
        
    }
    
    /**
     * determines if player has won and prints messages
     * (player wins if score >= min)
     * @param score - players score
     * @param min - minimal score to win
     */
    public static void determineWin(int score, int min) {
        if ( score >= min) {
            System.out.println("Congratulations, you have: " + score + " hits!");
        } else {
            System.out.println("Maybe next time...");
        }
    }
    
    /**
     * generates 6 random numbers from 1 to 49
     * @return - List<Integer> with 6 random integers
     */
    public static List<Integer> newDraw() {
        List<Integer> results = new ArrayList<Integer>();   // for results
        
        for (int i = 1 ; i <= 49 ; i++) {   // fill list with {1, 2, 3, ... 49}
            results.add(i);
        }         
        Collections.shuffle(results);       // shuffle (permute randomly)   
        results = results.subList(0, 5);    // extract first 6 elements
        Collections.sort(results);          // sort (for nice display later)
        
        return results;
    }
    
    /**
     * counts how many elements appear on both lists
     * @param list1
     * @param list2
     * @return int - number of mutual elements
     */
    public static int countMatches(List<Integer> list1, List<Integer> list2) {
        list1.retainAll(list2);         // from list1 delete all elements that are not present in list2
        return list1.size();
    }
    
    /**
     * asks user to input (length) numbers from (startValue) to (endValue) 
     * @param scan - Scanner object
     * @param length - number of integers to read form user
     * @param startValue - lowest acceptable number
     * @param endValue - highest acceptable number
     * @return List<Integer> with numbers chosen by user
     */
    public static List<Integer> getIntListFromScanner(Scanner scan, int length, int startValue, int endValue) {
        List<Integer> results = new ArrayList<Integer>();
        System.out.println("Choose 6 lucky numbers!");      // message for user
        while ( results.size() < length ) {             // loop until required number of integers read
            System.out.print((results.size() + 1) + ". number: ");
            if ( scan.hasNextInt() ) {          // if input is a number
                int tmp = scan.nextInt();
                if ( tmp >= startValue && tmp <= endValue ) {   // check if meets required conditions (from <startValutr to endValue>)
                    if ( results.contains(tmp) ) {              // check if the number has already been saved display error message
                        System.out.println("do not repeat values, try again");  
                    } else {                                    // if not - add to results
                        results.add(tmp);
                    }
                    
                } else {                                        // if number does not meet required conditions - display error msg
                    System.out.println(tmp + " is not an integer from " + startValue + " to " + endValue + " try again");
                }
     
            } else {                            // if input is not a number - display error message
                System.out.println(scan.next() + " is not an integer, try again");
            }
        }
        Collections.sort(results);              // sort results (for nice display)
        
        return results;
    }
    

}
