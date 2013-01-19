/*
 * wordOccurences class to store individual words and the amount of times that they occur
 * and open the template in the editor.
 */

/**
 *
 * @author Steve Bartlett 3155051
 */
public class wordOccurences 

{
    private String word;
    private int occurences;
    
    public wordOccurences(String wd, int occ)               //Constructor with word and occurence paramaters
    {
        word = wd;
        occurences = occ;
    }
    
    public wordOccurences()                                //Default constructor
    {
        word = "";
        occurences = 0;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public void setWord(String wd)
    {
        word = wd;
    }
    
    public int getOccurences()
    {
        return occurences;
    }
    
    public void setOccurences(int occ)
    {
        occurences = occ;
    }
    
    public String toString()
    {
        return word + "\t" + occurences + "\n";
    }
}
