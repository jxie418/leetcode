package com.jing.xie.integer;

public class Solution {

  public static int add(int a, int b) {
    if (b == 0)
      return a;
    int sum = a ^ b;
    int carry = (a & b) << 1;
    return add(sum, carry);
  }

  public static int add2(int a, int b) {
    while (b != 0) {
      System.out.println(a + ", " + b);
      int carry = a & b;
      a = a ^ b;
      b = carry << 1;
    }
    return a;
  }

  public static int findMax(int x, int y) {
    int z = x - y;
    int i = (z >> 31) & 0x1;
    int max = x - i * z;
    return max;
  }

  public static void swap(int x, int y) {
    x = x + y;
    y = x - y;
    x = x - y;
  }
  public static void swap2(int x, int y) {
    x = x ^ y;
    y = x ^ y;
    x = x ^ y;
  }
}
