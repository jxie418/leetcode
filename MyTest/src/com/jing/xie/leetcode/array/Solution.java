package com.jing.xie.leetcode.array;

import java.util.HashMap;

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

  public static int longestIncreasingSubsequence(int[] nums) {
    // write your code here
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.get(nums[i]) == null ? 1 : map.get(nums[i]) + 1);
    }
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        int value = nums[i];
        int left = value - 1;
        int right = value + 1;

        int count = map.get(value);
        while (map.containsKey(left)) {
          count += map.get(left);
          map.remove(left);
          left--;
        }
        while (map.containsKey(right)) {
          count += map.get(right);
          map.remove(right);
          right++;
        }
        max = Math.max(max, count);
      }
    }
    return max;
  }
}
