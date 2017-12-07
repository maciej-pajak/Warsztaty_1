package task2.lotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LottoArrays {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] choice = getIntArrayFromScanner(scan, 6, 1, 49);              // step 1 get numbers from user 
        scan.close();
        System.out.println( "Your choice: " + Arrays.toString(choice) );
        int[] draw = newDraw(6);                                            // step 2 start lottery
        System.out.println("System drew: " + Arrays.toString(draw) );
        determineWin(commonElements(choice, draw));                         // step 3 check if player won
        
    }

    /**
     * determines if player won and prints message
     * @param hits - players hits
     */
    public static void determineWin(int hits) {

        final String[] winMessages = {  " hits!!  Congratulations, you won £1 000 000",    // 6 hits
                                        " hits! Congratulationsyou won £1000 ",            // 5 hits
                                        " hits. You won £100",                             // 4 hits
                                        " hits. You won £10"                               // 3 hits
                                        };
        if ( hits >= 3) {       // win
            System.out.println(hits + winMessages[6 - hits]);
        } else {                // loss
            System.out.println("Maybe next time...");
        }
    }

    /**
     * returns random integer < lessThan and >= greaterEqualThan
     * @param greaterEqualThan
     * @param lessThan
     * @param limit
     * @return random integer (int)
     */
    public static int getRandomInt(int greaterEqualThan, int lessThan) {
        Random r = new Random();
        return r.nextInt(lessThan - greaterEqualThan + 1) + greaterEqualThan;
    }
    
    /**
     * counts common elements in both tables
     * @param arr1
     * @param arr2
     * @return - number of common elements
     */
    public static int commonElements(int[] arr1, int arr2[]) { // FIXME not very elegant
        
        int counter = 0;
        
        for (int i = 0 ; i < arr1.length ; i++) {
            for (int j = 0 ; j < arr2.length ; j++) {
                if ( arr1[i] == arr2[j] ) {
                    counter++;
                    break;
                }
            }
        }
        return counter;    
    }
    
    /**
     * generates (count) of random integers
     * @return - table[count] of randomized integers
     */
    public static int[] newDraw(int count) {
        int[] results = new int[count];
        int i = 0;
        int tmp;
        
        while ( i < count) {
            tmp = getRandomInt(1, 50);
            if ( Arrays.binarySearch(results, tmp) < 0 ) {
                results[0] = tmp;
                Arrays.sort(results);
                i++;
            }
        }
        return results;
    }
    
    /**
     * asks user to input (length) numbers from (startValue) to (endValue) 
     * @param scan - Scanner object
     * @param length - number of integers to read form user
     * @param startValue - lowest acceptable number
     * @param endValue - highest acceptable number
     * @return int[] with numbers chosen by user
     */
    public static int[] getIntArrayFromScanner(Scanner scan, int length, int startValue, int endValue) {
        int[] results = new int[length];
        int counter = 0;
        
        while ( counter < length ) {
            System.out.print( (counter + 1) + ". number: " );
            if ( scan.hasNextInt() ) {
                int tmp = scan.nextInt();
                if ( tmp >= startValue && tmp <= endValue ) {
                    if ( isInArray(tmp, results) ) {
                        System.out.println("do not repeat values, try again");
                    } else {
                        results[counter++] = tmp;
                    }                 
                } else {
                    System.out.println(tmp + " is not an integer from " + startValue + " to " + endValue + " try again");
                }
     
            } else {
                System.out.println(scan.next() + " is not an integer, try again");
            }
        }
        Arrays.sort(results);  
        return results;
    }
    
    /**
     * check if values exists in array
     * @param value 
     * @param arr
     * @return true if exists, false - otherwise
     */
    public static boolean isInArray(int value, int[] arr) {
        int[] tmp = arr.clone();    // clone to preserver original array
        Arrays.sort(tmp);           // required by binarySearch method
        
        if ( Arrays.binarySearch(tmp, value) >= 0 ) {
            return true;
        } else {
            return false;
        }
    }
    

}
