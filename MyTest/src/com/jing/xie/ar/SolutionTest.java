package com.jing.xie.ar;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {

  @Test
  public void testLCS() {
    String str1 = "BDCABA";
    String str2 = "ABCBDAB";
    System.out.println(Solution.longestCommoneSubsequence(str1, str2));
    assertEquals("BCBA", Solution.longestCommoneSubsequence(str1, str2));
  }
  @Test
  public void testLCS2() {
    String str1 = "abractyeyt";
    String str2 = "dgdsaeactyey";
    System.out.println(Solution.longestSubstring(str1, str2));
    assertEquals("actyey", Solution.longestSubstring(str1, str2));
  }
}
