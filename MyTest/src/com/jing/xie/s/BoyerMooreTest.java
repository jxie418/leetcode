package com.jing.xie.s;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoyerMooreTest {

  @Test
  public void test() {
    String txt = "AABRAACADABRAACAADABRA";
    String pat = "AACAA";
    BoyerMoore boyerMoore = new BoyerMoore(pat);
    int t = boyerMoore.search(txt);
    System.out.println(t);
    assertEquals(12, t);
  }

}
