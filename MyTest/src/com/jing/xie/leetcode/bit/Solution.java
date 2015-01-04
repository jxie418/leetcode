package com.jing.xie.leetcode.bit;

public class Solution {
  
  /**
   * Single Number
   * @param A
   * @return
   */
  public int singleNumber(int[] A) {
    int [] count = new int[32];
    int res = 0 ;
    for (int i = 0 ; i < 32; i ++) {
      for (int j = 0 ; j < A.length ; j++) {
        if (((A[j]>>i)&1)==1) {
          count[i] ++;
        }
      }
      res |= ((count[i]%3)<<i);
    }
    return res;
  }
  
  /***
   * Majority Element 
   * @param num
   * @return
   */
  public int majorityElement(int[] num) {
    int value = num[0];
    int count = 1;
    for (int i = 1; i < num.length; i++) {
      if (count == 0) {
        value = num[i];
        count = 1;
      } else if (value == num[i]) {
        count++;
      } else {
        count--;
      }
    }
    return value;
  }
}
