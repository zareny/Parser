/**
 * Rowan Burgess c3079179
 *
 * Fetch and parse HTML found at the provided URL
 * Output title and paragraphs to the console and save to plaintextOutput.txt
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HTMLParser
  {
    static Scanner console = new Scanner (System.in);

    public static void main (String[] args) throws IOException
      {
        StringBuilder sb = new StringBuilder ();        // for building plaintext result string

        System.out.println ("Please enter target URL: ");
        String target = console.next ();
        System.out.println ("Working...");

        Document doc = Jsoup.connect (target).get ();  //connect to target URL
        String rawHTML = doc.toString ();              //store raw HTML returned

        sb.append ("Title: " + doc.title () + "\n\n");   //headings are cool

        Element content = doc.getElementById ("content");
        Elements paragraphs = content.getElementsByTag ("p"); //make list of all paragraph elements

        for (Element temp : paragraphs) // for every paragraph
          {
            String data = temp.text ();     //get the text from this element
            sb.append (data);              // add to result string
            sb.append ("\n\n");
          }

        String plaintextOutput = sb.toString ();   // build complete result string
        System.out.println (plaintextOutput);

        try
          {
            BufferedWriter outOne = new BufferedWriter (new FileWriter ("plaintextOutput.txt"));
            outOne.write (plaintextOutput);
            outOne.close ();

            BufferedWriter outTwo = new BufferedWriter (new FileWriter ("rawHTML.txt"));
            outTwo.write (rawHTML);
            outTwo.close ();
          } catch (IOException e)
          {
            e.printStackTrace ();
          }

      }

  }