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

  public long kthPrimeNumber(int k) {
    if (k == 0)
      return 1;
    long[] res = new long[k + 1];
    res[0] = 1;
    int p3 = 0, p5 = 0, p7 = 0;
    for (int i = 1; i <= k; i++) {
      // find the minimum prime number.
      long val = Math.min(Math.min(res[p3] * 3, res[p5] * 5), res[p7] * 7);
      if (val / res[p3] == 3)
        p3++;
      if (val / res[p5] == 5)
        p5++;
      if (val / res[p7] == 7)
        p7++;
      res[i] = val;
    }
    return res[k];
  }
}
