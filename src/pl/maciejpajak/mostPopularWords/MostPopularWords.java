package pl.maciejpajak.mostPopularWords;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MostPopularWords {

    public static void main(String[] args) {
        
        // create list of websites to get words from
        List<String[]> popularWebsites = new ArrayList<String[]>();
        popularWebsites.add( new String[] {"http://www.wp.pl","div._3I9Ewz" } );
        popularWebsites.add( new String[] {"http://www.onet.pl", "span.title" } );
        popularWebsites.add( new String[] {"http://www.gazeta.pl", "span.title" } );
        popularWebsites.add( new String[] {"http://www.tvn24.pl", "h1" } );
        
        // export words from every site on the list
        for ( String[] website : popularWebsites ) {
            try {
                exportWordToFile(website[0], website[1], "popular_words.txt", 3);
                System.out.println("export from " + website[0] + " successful");
            } catch (IOException e) {
                System.out.println("error while exporting words from " + website + "popular_words.txt");
            }
        }
        
        // export 10 most popular words from file popular_words.txt to most_popular_words.txt
        Path path = Paths.get("popular_words.txt");
        try {
            Scanner scan = new Scanner(path);
            writeToFile(getMostPopular(scan, 10, true), "most_popular_words.txt");
            scan.close();
            System.out.println("most popular words found successfully");
        } catch (IOException e) {
            System.out.println("error: could not read the file");
            e.printStackTrace();
        }
        
        
     
    }
    
    /**
     * Saves each word from every html element selected by {@code cssQuery} to file. 
     * Ignores words shorter than {@code ignoreShorterThan}
     * @param websiteUrl {@code String} website URL
     * @param cssQuery {@code String}
     * @param fileName {@code String} output file name
     * @param ignoreShorterThan {@code int}
     * @throws IOException
     */
    private static void exportWordToFile(String websiteUrl, String cssQuery, String fileName, int ignoreShorterThan ) 
            throws IOException {
        
        FileWriter writer = new FileWriter(fileName, true);
        Connection connect = Jsoup.connect(websiteUrl);
        Document document = connect.get();
        Elements elements = document.select(cssQuery);
        
        StringTokenizer st;
        for ( Element elem : elements ) {
            st = new StringTokenizer( elem.text(), " ,.?!:;()-\"\'" );
            
            while ( st.hasMoreTokens() ) {
                String tmp = st.nextToken();
                if (tmp.length() > ignoreShorterThan) {
                    writer.append(tmp + "\n");
                }
            }
        }
        writer.close();
        
    }
    
    /**
     * Writes each element from {@code List<String> toWrite} to new line in file {@code fileName} 
     * (overwrites existing content)
     * @param toWrite {@code List<String>} list of elements to be saved to file
     * @param fileName {@code String} name of the output file
     */
    private static void writeToFile(List<String> toWrite, String fileName) {
        try {
            PrintWriter fp = new PrintWriter(fileName);
            for(String s : toWrite) {
                fp.append(s.toString() + "\n");
            }
            fp.close();
        } catch (FileNotFoundException e) {
            System.out.println("error writing to file: " + fileName);
        }
    }
    
    /** 
     * Returns {@code resultsNumber} elements which occur most often in the {@code scan}.
     * @param scan {@code Scanner} object to read {@code Strings} from
     * @param resultsNumber {@code int} number of most common elements
     * @param caseInsensitive {@code boolean} true to make the method case insensitive, false otherwise
     * @return {@code List<String>} 
     */
    private static List<String> getMostPopular(Scanner scan, int resultsNumber, boolean caseInsensitive) { 
        
        Map<String,Integer> words = new HashMap<String,Integer>();
        
        while ( scan.hasNext() ) {
            String tmp = scan.next().toLowerCase();
            if ( words.containsKey(tmp) ) {
                words.replace( tmp, words.get(tmp) + 1 );
            } else {
                words.put(tmp, 1);
            }
        }
        
        List<String> results = new ArrayList<String>();
        
        List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(words.entrySet());
        
        Collections.sort(list, new Comparator<Entry<String,Integer>>() {
            public int compare(Entry<String,Integer> o1, Entry<String,Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        
        for (int i = 1 ; i <= resultsNumber && i < list.size() ; i++) {
            results.add( list.get( list.size() - i ).getKey() );
        }

        return results;       
       
    }
} 

