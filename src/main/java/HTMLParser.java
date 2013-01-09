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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
  }