package com.jing.xie.amazon;

import java.util.*;

public class Solution {
  public List<Integer> getXORList(List<Integer> a, List<Integer> b) {
    List<Integer> res = new ArrayList<Integer>();
    for (Integer i : a) {
      if (!b.contains(i)) {
        res.add(i);
      }
    }
    return res;
  }

  public List<Range> getRanges(int[] number) {
    List<Range> list = new ArrayList<Range>();
    int start = number[0];
    int end = number[0];
    for (int i = 1; i < number.length; i++) {
      if (number[i] - number[i - 1] >= 2) {
        Range r = new Range(start, end);
        list.add(r);
        start = number[i];
        end = number[i];
      } else {
        end = number[i];
      }
    }
    Range r = new Range(start, end);
    list.add(r);
    return list;
  }

  public boolean isValidParenthesis(String input) {
    if (input == null || input.length() == 0) {
      return false;
    }
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '(') {
        count++;
      } else if (input.charAt(i) == ')') {
        count--;
        if (count < 0) {
          return false;
        }
      }
    }
    return count == 0;
  }

  public void swap(int a, int b) {
    a = a + b;
    b = a - b;
    a = a - b;
  }

  /**
   * 17.3
   **/
  public int factorOfFive(int n) {
    int count = 0;
    while (n % 5 == 0) {
      ++count;
      n /= 5;
    }
    return count;
  }

  public int numberOfZero(int m) {
    int count = 0;
    for (int i = 2; i <= m; i++) {
      count += factorOfFive(i);
    }
    return count;
  }

  public int countFactZeros(int num) {
    int count = 0;
    if (num < 0) {
      return -1;
    }
    for (int i = 5; num / i > 0; i *= 5) {
      count += num / i;
    }
    return count;
  }

  /**
   * CC 17.5
   **/
  public static int flip(int bit) {
    return 1 ^ bit;
  }

  public static int sign(int a) {
    return flip((a >> 31) & 0x1);
  }

  public static int getMaxNaive(int a, int b) {
    int k = sign(a - b);
    int q = flip(k);
    return a * k + b * q;
  }

  public static int getMax(int a, int b) {
    int c = a - b;
    int sa = sign(a);
    int sb = sign(b);
    int sc = sign(c);
    int use_sign_of_a = sa ^ sb;
    int use_sign_of_c = flip(sa ^ sb);
    int k = use_sign_of_a * sa + use_sign_of_c * sc;
    int q = flip(k);
    return a * k + b * q;
  }

  //
  public String[] digits = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
  public String[] teens = { "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                  "Nineteen" };
  public String[] bigs = { "", "Thousand", "Million" };

  public String numToString(int number) {
    if (number == 0) {
      return "Zero";
    } else if (number < 0) {
      return "Negative " + numToString(-1 * number);
    }
    int count = 0;
    String str = "";
    while (number > 0) {
      if (number % 1000 != 0) {
        str = numToString100(number % 1000) + bigs[count] + " " + str;
      }
      number /= 1000;
      count++;
    }
    return str;
  }

  public String numToString100(int number) {
    String str = "";
    if (number >= 100) {
      str += digits[number / 100 - 1] + " Hundre ";
      number %= 100;
    }
    if (number >= 11 && number <= 19) {
      return str + teens[number - 11] + " ";
    } else if (number == 10 || number >= 20) {
      str += teens[number / 10 - 1] + " ";
      number %= 10;
    }
    if (number >= 1 && number <= 9) {
      str += digits[number - 1] + " ";
    }
    return str;
  }

  public int getMaxSum(int[] a) {
    int maxSum = Integer.MIN_VALUE;
    int sumSoFar = 0;
    for (int i = 0; i < a.length; i++) {
      sumSoFar = Math.max(a[i], sumSoFar + a[i]);
      maxSum = Math.max(sumSoFar, maxSum);
    }
    return maxSum;
  }

  public int getMaxSum2(int[] a) {
    int maxSum = 0;
    int sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
      if (maxSum < sum) {
        maxSum = sum;
      } else if (sum < 0) {
        sum = 0;
      }
    }
    return maxSum;
  }

  public static int add(int a, int b) {
    if (b == 0)
      return a;
    int sum = a ^ b;
    int carry = (a & b) << 1;
    return add(sum, carry);
  }

  public void moveZeroToEnd(int[] num) {
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num.length - i - 1; j++) {
        if (num[j] == 0) {
          num[j] = num[j + 1];
          num[j + 1] = 0;
        }
      }
    }
  }

  public void shuffleArrayInteratively(int[] cards) {
    Random random = new Random();
    for (int i = 0; i < cards.length; i++) {
      int k = random.nextInt(i);
      int tmp = cards[k];
      cards[k] = cards[i];
      cards[i] = tmp;
    }
  }
}

class Range {
  int start;
  int end;

  Range(int start, int end) {
    this.start = start;
    this.end = end;
  }

  Range() {
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public String toString() {
    if (start == end) {
      return "Range(" + start + ")";
    } else {
      return "Range(" + start + "," + end + ")";
    }
  }

}

class SuffixTree {
  SuffixTreeNode root = new SuffixTreeNode();

  public SuffixTree(String s) {
    for (int i = 0; i < s.length(); i++) {
      String suffix = s.substring(i);
      root.insertString(suffix, i);
    }
  }
}

class SuffixTreeNode {
  Map<Character, SuffixTreeNode> children = new HashMap<Character, SuffixTreeNode>();
  char value;
  List<Integer> indexes = new ArrayList<Integer>();

  public SuffixTreeNode() {
  }

  public void insertString(String s, int index) {
    indexes.add(index);
    if (s != null && s.length() > 0) {
      value = s.charAt(0);
      SuffixTreeNode child = null;
      if (children.containsKey(value)) {
        child = children.get(value);
      } else {
        child = new SuffixTreeNode();
        children.put(value, child);
      }
      String remainder = s.substring(1);
      child.insertString(remainder, index);
    }
  }

  public List<Integer> search(String s) {
    if (s == null || s.length() == 0) {
      return indexes;
    } else {
      char first = s.charAt(0);
      if (children.containsKey(first)) {
        String remainder = s.substring(1);
        return children.get(first).search(remainder);
      }
    }
    return null;
  }
}