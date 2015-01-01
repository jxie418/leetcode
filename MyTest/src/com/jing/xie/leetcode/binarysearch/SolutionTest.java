package com.jing.xie.leetcode.binarysearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {

  @Test
  public void test() {
    assertTrue(Solution.isNagetive(-1, 1));
  }

  @Test
  public void testDivide() {
    assertEquals(Integer.MAX_VALUE, Solution.divide(Integer.MIN_VALUE, -1));
  }
}
