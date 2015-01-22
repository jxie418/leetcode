package com.jing.xie.leetcode.sortedarray;

public class Solution {
  public int removeDuplicates(int[] A) {
    if (A == null) {
      return 0;
    }
    if (A.length <= 1) {
      return A.length;
    }
    int i = 1, j = 1;
    while (j < A.length) {
      if (A[j] == A[i - 1]) {
        j++;
      } else {
        A[i] = A[j];
        i++;
        j++;
      }
    }
    return i;
  }
  
  public int removeDuplicates2(int[] A) {
    if (A == null) {
      return 0;
    }
    if (A.length <=2) {
      return A.length;
    }
    int pre= 1, cur = 2;
    int res = A.length;
    while (cur < A.length) {
      if (A[pre -1] == A[pre]  && A[pre] == A[cur]) {
        cur++;
        res--;
      }else {
        pre++;
        A[pre] = A[cur++];
      }
    }
    return res;
  }
  /***
   * 
   * @param A
   * @param m
   * @param B
   * @param n
   */
  public void merge(int A[], int m, int B[], int n) {
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (A[i] > B[j]) {
        A[k--] = A[i--];
      } else {
        A[k--] = B[j--];
      }
    }
    while (j >= 0) {
      A[k--] = B[j--];
    }
  }
}
