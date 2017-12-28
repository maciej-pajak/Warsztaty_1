package pl.maciejpajak.rollDice;

import java.util.Random;
import java.util.Scanner;

public class Main {
    
    

    /**
     * main method - tests method diceRoll
     * @param args
     */
    public static void main(String[] args) {
        
        String[] test = {   "adfaf", "D2", "32D34+-1", "D1-2", "3D3D2+2+3" };
        
        
        for (int i = 0; i < test.length; i++) {
            try {
                System.out.print( test[i] + "\t");
                System.out.println(diceRoll(test[i]) );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }

        
    }
    
    /**
     * performs required number of rolls and calculates result
     * @param rollCode {@code String} with encoded information, ex. 2D20-3 (roll twice with 20-side dice, sum and subtract 3)
     * @return
     * @throws Exception when invalid code is passed as an argument
     */
    private static int diceRoll(String rollCode) throws Exception {
        
        if ( !verifyCode(rollCode) ) {
            throw new Exception("invalid code!");
        }
        
        int[] rollType = getRollType(rollCode);
        Random r = new Random();
        
        int result = 0;
        
        for (int i = 0 ; i < rollType[0] ; i++) {
            result += (r.nextInt(rollType[1]) + 1);
        }
        
        return (result + rollType[2]);
    }
    
    /**
     * returns parameters for diceRoll method
     * [0] - how many times dice should be rolled
     * [1] - dice type, upper limit of random integers
     * [2] - modifier (add or subtract from sum of results)
     * @param rollCode {@code String}
     * @return {@code int[]} with parameters
     */
    private static int[] getRollType(String rollCode) {
        int[] results = new int[3];     // array for results
        /*
         * [0] - how many times dice should be rolled
         * [1] - dice type, upper limit of random integers
         * [2] - modifier (add or subtract from sum of results)
         */
        
        Scanner scanner = new Scanner(rollCode);
        Scanner scan = scanner.useDelimiter("[D+-]");
        
        if ( rollCode.indexOf("D") > 0 ) {      // check if D is not on first position
            results[0] = scan.nextInt();        // if not scan first int to in [0]
        } else {                                
            results[0] = 1;                     // otherwise write 1
        }
        
        results[1] = scan.nextInt();            // scan second int and save in [1]
        
        if ( rollCode.contains("+") ) {         
            results[2] = scan.nextInt();        // save next int with +
        } else if ( rollCode.contains("-") ) {
            results [2] = -(scan.nextInt());    // save next int with -
        }        
        
        scan.close();
        scanner.close();
        
        return results;
    }
    
    /**
     * verifies if input {@code code} matches required pattern
     * @param code {code String}
     * @return true if matches, false otherwise
     */
    private static boolean verifyCode(String code) {       
        return code.matches("\\d*?([D]\\d+)([+-]\\d*)?"); // :o      
    }
}
