/**
 * Rowan Burgess c3079179
 *
 */

import java.io.*;
import java.util.*;

public class HTMLParser
  {
    static Scanner console = new Scanner (System.in);

    public static void main (String[] args) throws IOException
      {
        System.out.println ("Hello World!");
        Document a = new Document ();
        Document b = new Document ("test");
        String output;

        System.out.println (b.getContent ());

        BufferedReader br = new BufferedReader (new FileReader ("test.txt"));
        try
          {
            StringBuilder sb = new StringBuilder ();
            String line = br.readLine ();

            while (line != null)
              {
                sb.append (line);      //get the next input line
                sb.append ("\n");
                line = br.readLine ();
              }

            output = sb.toString ();
          } finally
          {
            br.close ();
          }

        System.out.println (output);
        String[] x = output.split ("\\s");    //use whitespace as delimiter in string

        for (int i = 0; i < x.length; i++)
          {
            if (x[i].startsWith ("<"))
              {
                System.out.println ("Have to do some processing...");
              } else
              {
                System.out.println ("Position " + i + " - Output = " + x[i]);
              }
          }

      }

  }