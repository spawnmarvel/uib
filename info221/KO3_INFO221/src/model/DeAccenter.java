package model;

import java.text.Normalizer;
import java.util.regex.Pattern;
/**
 * a class which has a method for removing signs include in a word or letter
 * @author 
 *
 */
public class DeAccenter {

  /**
   * method for removing signs that comes with a letter or word
   * @param word, the word to normalize
   * @return
   */
  public static String unAccent(String word) {
      
      String temp = Normalizer.normalize(word, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(temp).replaceAll("");
  }
}


