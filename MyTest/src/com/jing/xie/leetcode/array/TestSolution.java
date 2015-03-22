package com.jing.xie.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSolution {

  @Test
  public void testlongestIncreasingSubsequence() {
    int[] num = { 9, 3, 6, 2, 7 };
    int value = Solution.longestIncreasingSubsequence(num);
    assertEquals(value, 2);

  }
}
