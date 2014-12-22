package com.jing.xie.leetcode.array;

public class Solution {
  /**
   * Find Minimum in Rotated Sorted Array II
   * @param num
   * @return
   */
  public static int findMin(int[] num) {
    if (num == null || num.length == 0) {
      return 0;
    }
    int L = 0;
    int R = num.length - 1;
    while (L < R && num[L] >= num[R]) {
      int mid = (L + R) >>> 1;
      if (num[mid] > num[R]) {
        L = mid + 1;
      } else if (num[mid] < num[L]) {
        R = mid;
      } else {
        L++;
      }
    }
    return num[L];
  }
}
