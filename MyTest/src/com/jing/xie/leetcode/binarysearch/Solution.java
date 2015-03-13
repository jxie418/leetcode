package com.jing.xie.leetcode.binarysearch;

/**
Find the First Bad Version
Find the first bad version.
 */
public class Solution {
  /**
   * First question
   * Sqrt(x);
   * @param x
   * @return
   */
  public int sqrt(int x) {
    long low = 0;
    long high = x;
    while (low <= high) {
      long mid = (low + high) >>> 1;
      if ((long) x == mid * mid) {
        return (int) mid;
      } else if ((long) x > mid * mid) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return (int) high;
  }
  /**
   * Search Insert Position
   * @param A
   * @param target
   * @return
   */
  public int searchInsert(int[] A, int target) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int low = 0 ;
    int high = A.length - 1;
    while(low <= high) {
       int mid = (low + high) >>> 1;
       if (A[mid] == target) {
         return mid;
       } else if (A[mid] < target) {
            low = mid + 1 ;
       } else {
         high = mid - 1;
       }
    }
    return low;
  }
  /**
   * Search in Rotated Sorted Array
   */
  public int search(int[] A, int target) {
    if (A == null || A.length == 0) {
      return -1;
    }
    int low = 0 ;
    int high = A.length -1;
    while(low <= high) {
      int mid = (low + high) >>> 1;
      if (A[mid] == target) {
        return mid;
      } else if (A[mid] >= A[low]) {
        if (target >= A[low] && target <= A[mid]) {
          high = mid -1;
        } else {
          low = mid + 1 ;
        }
      } else {
        if (target >= A[mid] && target <= A[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }
  /**
   * Search in Rotated Sorted Array II
   */
  public boolean search2(int[] A, int target) {
    if (A == null || A.length == 0) {
      return false;
    }
    int low = 0 ;
    int high = A.length -1;
    while(low <= high) {
      int mid = (low + high) >>> 1;
      if (A[mid] == target) {
        return true;
      } else if (A[mid] > A[low]) {
        if (target >= A[low] && target <= A[mid]) {
          high = mid -1;
        } else {
          low = mid + 1 ;
        }
      } else if (A[mid] < A[low]){
        if (target >= A[mid] && target <= A[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        } 
      } else {
        low ++;
      }
    }
    return false;
  }
  /***
   * Search for a Range
   * @param A
   * @param target
   * @return
   */
  public int[] searchRange(int[] A, int target) {
    int [] result = {-1, -1};
    int left = searchRangeLeft(A, target, 0, A.length -1);
    int right = searchRangeRight(A, target, 0, A.length-1);
    if (left <= right) {
      result[0] = left;
      result[1] = right;
    }
    return result;
  }

  public int searchRangeLeft(int[] A, int target, int low, int high) {
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (A[mid] == target) {
        return searchRangeLeft(A, target, low, mid - 1);
      } else if (A[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }
  
  public int searchRangeRight(int[] A, int target, int low, int high) {
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (A[mid] == target) {
        return searchRangeRight(A, target, mid+1, high);
      } else if (A[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return high;
  }
  /**
   * Search a 2D Matrix
   * @param matrix
   * @param target
   * @return
   */
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    int low =0 ;
    int high = row * col - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int r = mid / col;
      int c = mid % col ;
      if (matrix[r][c] == target) {
        return true;
      } else if (matrix[r][c] > target) {
        high = mid -1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }
  /***
   * Pow(x, n)
   * @param x
   * @param n
   * @return
   */
  public double pow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      x = 1/x;
      n = -n;
    }
    double value = pow(x, n >>> 1);
    return n % 2 == 0 ? value * value : value * value * x;
  }
  /**
   * Median of Two Sorted Arrays
   * @param A
   * @param B
   * @return
   */
  public double findMedianSortedArrays(int A[], int B[]) {
    int i = 0, j = 0, m = A.length + B.length;
    double pre = 0, last = 0;
    while (i + j <= m >>> 1) {
      pre = last;
      if (j >= B.length) {
        last = A[i];
        i++;
      } else if (i >= A.length) {
        last = B[j];
        j++;
      } else if (A[i] > B[j]) {
        last = B[j];
        j++;
      } else {
        last = A[i];
        i++;
      }
    }
    return m % 2 == 0 ? (pre + last) / 2.0 : last;
  }
  /**
   * Find Peak Element
   * @param num
   * @return
   */
  public int findPeakElement(int[] num) {
    if (num == null || num.length == 0) {
      return 0;
    }
    return findPeakElement(num, 0, num.length - 1);
  }
  public int findPeakElement(int []num, int low, int high) {
    int mid = (low + high) >>> 1;
    if ((mid == 0 || num[mid-1] < num[mid]) && (mid == num.length -1 || num[mid] > num[mid+1])) {
        return mid;
    } else if (mid > 0 && num[mid -1] > num[mid]) {
      return findPeakElement(num, low, mid-1);
    }  else {
      return findPeakElement(num, mid+1, high);
    }
  }
  /***
   * Find Minimum in Rotated Sorted Array
   * @param num
   * @return
   */
  public int findMin(int[] num) {
    int L = 0 ;
    int R = num.length -1;
    while(L< R && num[L]>=num[R]) {
      int M = (L+R) >>> 1;
      if (num[M]>num[R]) {
        L = M +1;
      } else if (num[M] < num[L]) {
        R = M;
      } else {
        L++;
      }
    }
    return num[L];
  }
  /**
   * Divide Two Integers
   * @param dividend
   * @param divisor
   * @return
   */
  public static int divide(int dividend, int divisor) {
    long p = dividend;
    long q = divisor;
    p = Math.abs(p);
    q = Math.abs(q);
    long res = 0;
    while (p >= q) {
      long count = 0;
      while (p >= (q << count)) {
        count++;
      }
      res += ((long)1 << (count - 1));
      p -= q << (count - 1);
    }
    res = ((divisor < 0) ^ (dividend < 0)) ? -res : res;
    if (res >= Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    if (res <= Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int) res;
  }
  
  public static boolean isNagetive(int d,  int p){
    return (d < 0) ^ (p < 0);
  }
}
