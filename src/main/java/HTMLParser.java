/**
 * Rowan Burgess c3079179
 *
 * Fetch and parse HTML found at the provided URL
 * Output title and paragraphs to the console and save to plaintextOutput.txt
 *
 * Also prints all links found on the target page to console
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class HTMLParser
  {
    static Scanner console = new Scanner (System.in);

    public static void main (String[] args) throws IOException
      {
        System.out.println ("Please enter target URL: ");
        String target = console.next ();
        System.out.println ("Working...");

        Document doc = Jsoup.connect (target).get ();  //connect to target URL and get Document
        String rawHTML = doc.toString ();              //store raw HTML returned to save

        String plaintextOutput = getPlaintext (doc);    // get the plaintext from the page
        Elements links = getLinks (doc);                //get (relevant) links from page

        System.out.println ("Number of links : " + links.size ());

        for (Element tempLink : links)     //printing out what was found for the moment
          {
            System.out.println ("Link: " + tempLink.attr ("abs:href")); // http://jsoup.org/cookbook/extracting-data/working-with-urls
          }

        writeFiles (rawHTML, plaintextOutput);
        
        orderNumber();
      }

    public static Elements getLinks (Document d)
      {
        Elements links = d.select ("a[href]");    //get all links from document

        // Processing required here to discard useless/rubbish links, and only keep the ones relevant/valuable to query

        return links;
      }

    public static String getPlaintext (Document d)
      {
        StringBuilder sb = new StringBuilder ();        // for building plaintext result string
        sb.append ("Title: " + d.title () + "\n\n");   //headings are cool
        Elements paragraphs = d.getElementsByTag ("p");

        for (Element temp : paragraphs)
          {
            String data = temp.text ();     //get the text from this element
            sb.append (data);              // add to result string
            sb.append ("\n\n");
          }

        return sb.toString ();
      }

    public static void writeFiles (String raw, String plain)
      {
        try
          {
            BufferedWriter outOne = new BufferedWriter (new FileWriter ("plaintextOutput.txt"));
            outOne.write (plain);
            outOne.close ();

            BufferedWriter outTwo = new BufferedWriter (new FileWriter ("rawHTML.txt"));
            outTwo.write (raw);
            outTwo.close ();
          }

        catch (IOException e)
          {
            e.printStackTrace ();
          }
      }
    
    public static void orderNumber()              //Method for adding words to an arraylist, ordering the words and counting the number of times a word occurs
    {
        File fileName = new File("plaintextOutput.txt");                        
        ArrayList<wordOccurences> wordList = new ArrayList<wordOccurences>();   //Creates an arraylist of wordOccurences objects
        int size = 0;                       //Number of words added to the arraylist
        boolean found = false;              //Boolean flag for when a word already exists in the arraylist              
        
        
        try
        {
            BufferedReader inOne = new BufferedReader(new FileReader(fileName));        //Scans in words in the plaintextOutput.txt file
            String line = null;                     //String formed from a line scanned in from the file
            String word = null;                     //Words from a line broken up from the string tokenizer
            System.out.println ("Working...");
            while ((line = inOne.readLine()) != null)
            {
              StringTokenizer tokens = new StringTokenizer(line, " ");
              while(tokens.hasMoreTokens())
              { 
                word = tokens.nextToken().toLowerCase();
                found = false;
                
                if(wordList.size() == 0)                //If statement for adding the first word to the arraylist
                {
                    wordList.add(new wordOccurences(word,1));
                    size++;
                }
                
                else
                {   
                    for(wordOccurences w : wordList)                       //Loop for searching the arraylist for a word and incrementing the number of occurences if a word is found
                    {                      
                        if(word.equals(w.getWord()))
                        {
                            w.setOccurences(w.getOccurences() + 1);
                            found = true;
                                
                        }
                     }
                          
                           
                 if(found == false)                                 //Adds a new word to the arraylist if it isn't found
                 {
                    wordList.add(new wordOccurences(word,1));
                    size++;
                 }         
                     
                }
              }
            }
        }
        
        catch (IOException e)
          {
            e.printStackTrace ();
          }
          
            
        Collections.sort(wordList);
        
        String formattedString = wordList.toString()
                                      .replace(",", "")  //remove the commas
                                      .replace("[", "")  //remove the left bracket
                                      .replace("]", ""); //remove the right bracket
        System.out.println("Word list size: " + size);    
        System.out.println(formattedString);
    }
  }