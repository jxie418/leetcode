package com.jing.xie.s;

import static org.junit.Assert.*;

import org.junit.Test;

public class KMPTest {

  @Test
  public void test() {
    String txt = "AABRAACADABRAACAADABRA";
    String pat = "AACAA";
    KMP kmp = new KMP(pat);
    int t = kmp.search(txt);
    System.out.println(t);
    assertEquals(12, t);
  }

}
