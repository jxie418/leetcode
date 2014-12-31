package com.jing.xie.leetcode.math;

public class Solution {
  /**
   * Given an integer n, return the number of trailing zeroes in n!.
   * @param n
   * Just count how many 5 it will have.
   * @return
   */
  public int trailingZeroes(int n) {
    int res = 0;
    while (n > 0) {
      res += n / 5;
      n /= 5;
    }
    return res;
  }
}
