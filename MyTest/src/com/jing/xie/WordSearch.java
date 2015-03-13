/**
 * 
 */
package com.jing.xie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jxie
 *
 */
public class WordSearch {

  private static final String S = ".";

  public WordSearch() {
    list = new ArrayList<String>();
  }
  private List<String> list;
  
  public    void addWord(String word) {
    list.add(word);
  }
  public  boolean search(String word) {
    if (!word.contains(S) && list.contains(word)) {
      return true;
    } else if (word.contains(S)) {
      char[] contents = word.toCharArray();
      for(int i = 0 ; i < contents.length; i++) {
        if (contents[i] == '.') {
          for (char c ='a'; c <='z'; c++) {
            contents[i] = c;
            String newWord = new String(contents);
            if (search(newWord)) {
              return true;
            }
          }
          return false;
        }
      }
    } 
    return false;
    
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
   

  }

}
