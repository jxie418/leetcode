package com.jing.xie;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordSearchTest {

  @Test
  public void test() {
    WordSearch ws = new WordSearch();
    ws.addWord("rat");
    ws.addWord("cat");
    ws.addWord("bat");
    assertFalse(ws.search("dat"));
    assertTrue(ws.search("bat"));
    assertTrue(ws.search(".at"));
    assertTrue(ws.search("r.t"));
  }

}
